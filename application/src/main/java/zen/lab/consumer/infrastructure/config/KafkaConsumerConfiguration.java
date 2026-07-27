/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.config;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;
import zen.lab.consumer.adapters.edge.inbound.messages.Schema;

/**
 * Kafka consumer error handling. Base consumer settings auto-configured from
 * {@code spring.kafka.consumer.*} properties in application.yml.
 */
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {

    private final Environment environment;

    /**
     * Routes failed messages to the DLQ after 3 retries with 1s backoff.
     * Spring Boot auto-wires this bean into the listener container factory.
     *
     * <p>The DLQ producer factory is not exposed as a bean to avoid shadowing Spring Boot's
     * auto-configured {@code ProducerFactory<Object,Object>}, which would prevent
     * {@code KafkaTemplate} auto-configuration.
     */
    @Bean
    DefaultErrorHandler kafkaErrorHandler() {
        var dlqProducerFactory = new DefaultKafkaProducerFactory<String, byte[]>(Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                        environment.getRequiredProperty("spring.kafka.bootstrap-servers"),
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class));
        var kafkaTemplate = new KafkaTemplate<>(dlqProducerFactory);
        var recoverer = new DeadLetterPublishingRecoverer(
                kafkaTemplate, (record, ex) -> new TopicPartition(Schema.Topics.CLICK_EVENT_DLQ, -1));
        return new DefaultErrorHandler(recoverer, new FixedBackOff(1000L, 3));
    }
}
