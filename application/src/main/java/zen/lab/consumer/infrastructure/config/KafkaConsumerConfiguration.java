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
import org.springframework.kafka.core.ProducerFactory;
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
     * Dedicated producer factory for DLQ publishing. Uses ByteArraySerializer so the
     * original payload bytes are written verbatim to the DLQ topic without re-serialization.
     */
    @Bean
    ProducerFactory<String, byte[]> dlqProducerFactory() {
        return new DefaultKafkaProducerFactory<>(Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                        environment.getRequiredProperty("spring.kafka.bootstrap-servers"),
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class));
    }

    /**
     * Routes failed messages to the DLQ after 3 retries with 1s backoff.
     * Spring Boot auto-wires this bean into the listener container factory.
     */
    @Bean
    DefaultErrorHandler kafkaErrorHandler(ProducerFactory<String, byte[]> dlqProducerFactory) {
        var kafkaTemplate = new KafkaTemplate<>(dlqProducerFactory);
        var recoverer = new DeadLetterPublishingRecoverer(
                kafkaTemplate, (record, ex) -> new TopicPartition(Schema.Topics.CLICK_EVENT_DLQ, -1));
        return new DefaultErrorHandler(recoverer, new FixedBackOff(1000L, 3));
    }
}
