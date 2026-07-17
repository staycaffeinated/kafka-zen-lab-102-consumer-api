/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.kafka;

/*
 * Enumerates the Kafka topics used by the application.
 */
@SuppressWarnings({"java:S2386" // false positive; ALL_TOPICS cannot be `protected`
})
public class Schema {

    public static final String[] ALL_TOPICS = {Topics.CLICK_EVENT_DLQ, Topics.CLICK_EVENT_RECEIVED};

    public static class Topics {
        public static final String CLICK_EVENT_RECEIVED = "kafka-zen.lab-101.traffic-simulator.click-event.received";
        public static final String CLICK_EVENT_DLQ = "kafka-zen.lab-101.traffic-simulator.click-event.received.dlq";

        private Topics() {}
    }

    private Schema() {}
}
