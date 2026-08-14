/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.config;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.util.backoff.ExponentialBackOff;
import zen.lab.consumer.adapters.edge.inbound.messages.Schema;

/**
 * Kafka consumer error handling. Base consumer settings auto-configured from
 * {@code spring.kafka.consumer.*} properties in application.yml.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {

    private final Environment environment;

    @Value("${spring.kafka.listener.backoff.initial-interval:1000}")
    private long initialInterval;

    @Value("${spring.kafka.listener.backoff.multiplier:2.0}")
    private double multiplier;

    @Value("${spring.kafka.listener.backoff.max-interval:30000}")
    private long maxInterval;

    @Value("${spring.kafka.listener.backoff.max-elapsed-time:32000}")
    private long maxElapsedTime;

    /**
     * Routes failed messages to the DLQ after up to 5 retries with exponential backoff.
     * Non-retryable exceptions bypass retries and route directly to the DLQ.
     * Spring Boot auto-wires this bean into the listener container factory.
     *
     * <p>The DLQ producer factory is not exposed as a bean to avoid shadowing Spring Boot's
     * autoconfigured {@code ProducerFactory<Object,Object>}, which would prevent
     * {@code KafkaTemplate} auto-configuration.
     */
    @Bean
    DefaultErrorHandler kafkaErrorHandler() {
        var dlqProducerFactory = new DefaultKafkaProducerFactory<String, byte[]>(Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                        environment.getRequiredProperty("spring.kafka.bootstrap-servers"),
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class));
        var handler = getDefaultErrorHandler(dlqProducerFactory);
        handler.addNotRetryableExceptions(
                DeserializationException.class, SerializationException.class, IllegalArgumentException.class);
        handler.setRetryListeners((record, ex, deliveryAttempt) -> log.warn(
                "Retry attempt {} for topic={} partition={} offset={}",
                deliveryAttempt,
                record.topic(),
                record.partition(),
                record.offset(),
                ex));
        return handler;
    }

    private @NonNull DefaultErrorHandler getDefaultErrorHandler(
            DefaultKafkaProducerFactory<String, byte[]> dlqProducerFactory) {
        var kafkaTemplate = new KafkaTemplate<>(dlqProducerFactory);
        var recoverer = new DeadLetterPublishingRecoverer(
                kafkaTemplate, (consumerRecord, ex) -> new TopicPartition(Schema.Topics.CLICK_EVENT_DLQ, -1));

        var backOff = new ExponentialBackOff();
        backOff.setInitialInterval(initialInterval);
        backOff.setMultiplier(multiplier);
        backOff.setMaxInterval(maxInterval);
        backOff.setMaxElapsedTime(maxElapsedTime);

        return new DefaultErrorHandler(recoverer, backOff);
    }
}
