/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.adapters.port.inbound.messages;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * Creates standard Kafka topics on application startup for local development environments.
 *
 * <p>Topic auto-creation is acceptable for local development. Production environments
 * should provision topics through Kubernetes, Terraform, or equivalent infrastructure tooling
 * rather than relying on this bootstrapper.
 *
 * <p>This bean is excluded from the {@code test} profile (via {@code @Profile("!test")}) to
 * prevent integration test suites from attempting a real broker connection.
 */
@Configuration
@EnableKafka
@Profile("!test")
@Slf4j
public class KafkaTopicBootstrapper {

    private final KafkaAdmin kafkaAdmin;

    /**
     * Creates a {@code KafkaTopicBootstrapper} backed by the given {@link KafkaAdmin}.
     *
     * @param kafkaAdmin the Spring Kafka admin client; autoconfigured from
     *                   {@code spring.kafka.admin.*} properties
     */
    public KafkaTopicBootstrapper(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
    }

    /**
     * Creates all topics listed in {@link Schema#ALL_TOPICS} if they do not already exist.
     *
     * <p>Invoked once after the Spring application context has fully started
     * ({@link ApplicationReadyEvent}). A {@code TopicExistsException} is treated as a
     * no-op and logged at INFO level. Other failures are logged at ERROR level and do not
     * prevent the application from starting, allowing the Kafka listener to start and retry
     *  the connection independently.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void createTopics() {
        try (AdminClient client = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            List<NewTopic> topics =
                    Arrays.stream(Schema.ALL_TOPICS).map(this::createTopic).toList();
            client.createTopics(topics).all().get(30, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof TopicExistsException) {
                log.info("Topics already exist");
            } else {
                log.error("Failed to create Kafka topics", e);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Topic creation interrupted", e);
        } catch (TimeoutException e) {
            log.error("Timed out waiting for topic creation", e);
        }
    }

    /**
     * Builds a {@link NewTopic} descriptor using broker-default partition and replica counts.
     *
     * @param topicName the name of the topic to create
     * @return a {@code NewTopic} suitable for submission to the Kafka admin client
     */
    private NewTopic createTopic(String topicName) {
        return TopicBuilder.name(topicName).build();
    }
}
