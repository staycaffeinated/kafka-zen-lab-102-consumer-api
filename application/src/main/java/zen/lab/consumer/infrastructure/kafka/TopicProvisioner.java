/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.kafka;

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
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

/*
 * Creates standard topics on startup. Auto-creating topics is acceptable for local development.
 * Production environments should use Kubernetes, Terraform, or equivalent tooling.
 */
@Configuration
@EnableKafka
@Slf4j
public class TopicProvisioner {

    private final KafkaAdmin kafkaAdmin;

    public TopicProvisioner(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
    }

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

    /*
     * The brokers' default number of partitions and replicas will be used
     * if those values are not explicitly set here.
     */
    private NewTopic createTopic(String topicName) {
        return TopicBuilder.name(topicName).build();
    }
}
