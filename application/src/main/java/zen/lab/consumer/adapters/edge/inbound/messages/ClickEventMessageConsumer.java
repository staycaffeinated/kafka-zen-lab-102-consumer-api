package zen.lab.consumer.adapters.edge.inbound.messages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import zen.lab.consumer.adapters.edge.inbound.messages.topology.Schema;
import zen.lab.consumer.domain.events.ClickEvent;

@Component
@Slf4j
public class ClickEventMessageConsumer {

    @KafkaListener(topics = Schema.Topics.CLICK_EVENT_RECEIVED)
    public void consume(ClickEvent event) {
        log.info(">>> Received event: {}", event);
    }
}
