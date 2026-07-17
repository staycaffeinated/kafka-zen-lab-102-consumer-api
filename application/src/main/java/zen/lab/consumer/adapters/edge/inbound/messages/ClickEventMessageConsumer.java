package zen.lab.consumer.adapters.edge.inbound.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import zen.lab.consumer.adapters.edge.inbound.messages.model.ClickEventMessage;
import zen.lab.consumer.infrastructure.kafka.Schema;
import zen.lab.consumer.application.port.in.MessageConsumerUseCase;

@Component
@RequiredArgsConstructor
public class ClickEventMessageConsumer {

    private final MessageConsumerUseCase messageConsumerUseCase;
    private final ClickEventMessageMapper clickEventMessageMapper;

    @KafkaListener(topics = Schema.Topics.CLICK_EVENT_RECEIVED)
    public void consume(ClickEventMessage message, Acknowledgment acknowledgment) {
        messageConsumerUseCase.process(clickEventMessageMapper.toDomain(message));
        acknowledgment.acknowledge();
    }
}
