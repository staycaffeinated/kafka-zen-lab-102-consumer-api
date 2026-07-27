/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.adapters.edge.inbound.messages;

/**
 * Central registry of Kafka topic names used by this application.
 *
 * <p>All topic name constants are grouped under the nested {@link Topics} class.
 * The {@link #ALL_TOPICS} array is provided as a convenience for operations that
 * must enumerate every topic (e.g., {@code KafkaTopicBootstrapper}).
 *
 * <p>This class is not instantiable.
 */
@SuppressWarnings({"java:S2386" // false positive; ALL_TOPICS cannot be `protected`
})
public class Schema {

    /**
     * Array of all topic names managed by this application.
     * Used by {@code KafkaTopicBootstrapper} to create topics on startup.
     */
    public static final String[] ALL_TOPICS = {Topics.CLICK_EVENT_DLQ, Topics.CLICK_EVENT_RECEIVED};

    /**
     * Kafka topic name constants.
     */
    public static class Topics {

        /**
         * Primary inbound topic. Messages are produced by
         * {@code kafka-zen-lab-101-traffic-simulator}.
         */
        public static final String CLICK_EVENT_RECEIVED = "kafka-zen.lab-101.traffic-simulator.click-event.received";

        /**
         * Dead-letter queue. Failed messages are routed here after exhausting
         * the retry back-off configured in {@code KafkaConsumerConfiguration}.
         */
        public static final String CLICK_EVENT_DLQ = "kafka-zen.lab-101.traffic-simulator.click-event.received.dlq";

        private Topics() {}
    }

    private Schema() {}
}
