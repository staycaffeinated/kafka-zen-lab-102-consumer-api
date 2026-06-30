package zen.lab.consumer.adapters.edge.inbound.messages.topology;

/*
 * This class enumerates the Topics and State Stores used by the application.
 * If you prefer another style of tracking your Topics and State Stores, you can
 * delete this class and replace it with your own.  The example topics and stores
 * given below are simply examples.
 *
 * The structure of this class is inspired by some example code from Confluent.
 */

@SuppressWarnings({"java:S2386" // false positive; ALL_TOPICS cannot be `protected`
})
public class Schema {

    public static final String[] ALL_TOPICS = {Topics.CLICK_EVENT_DLQ, Topics.CLICK_EVENT_RECEIVED};

    // These are the example topics. Change these to match your application.
    public static class Topics {
        public static final String CLICK_EVENT_RECEIVED = "kafka-zen.lab-101.traffic-simulator.click-event.received";
        public static final String CLICK_EVENT_DLQ = "kafka-zen.lab-101.traffic-simulator.click-event.received.dlq";

        private Topics() {
            // sealed
        }
    }

    private Schema() {
        // sealed
    }
}
