package zen.lab.consumer.adapters.port.inbound.messages;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import zen.lab.consumer.adapters.port.inbound.messages.model.ClickEventMessage;
import zen.lab.consumer.application.port.inbound.MessageConsumerUseCase;

/**
 * Inbound Kafka adapter that listens on the primary click-event topic.
 *
 * <p>This class bridges the Kafka infrastructure and the application use case:
 * <ol>
 *   <li>Receives a {@code ConsumerRecord} containing a {@link ClickEventMessage} deserialized
 *       by Spring Kafka's {@code JsonDeserializer} (subtype resolved via {@code @JsonSubTypes}).</li>
 *   <li>Maps the message to a domain {@link zen.lab.consumer.domain.events.ClickEvent} via
 *       {@link ClickEventMessageMapper}.</li>
 *   <li>Passes the domain event to the {@link MessageConsumerUseCase} inbound port.</li>
 *   <li>Acknowledges the Kafka offset only after successful processing.</li>
 * </ol>
 *
 * <p>Error handling (retries and DLQ routing) is managed externally by the
 * {@code DefaultErrorHandler} bean registered in {@code KafkaConsumerConfiguration}.
 */
@Component
@RequiredArgsConstructor
public class ClickEventMessageConsumer {

    private final MessageConsumerUseCase messageConsumerUseCase;
    private final ClickEventMessageMapper clickEventMessageMapper;

    /**
     * Receives a message from the click-event topic, maps it to a domain event,
     * and delegates processing to the use case.
     *
     * <p>The Kafka offset is committed only after {@code process} returns normally.
     * If an exception propagates out of this method, the {@code DefaultErrorHandler}
     * will retry the message (up to 3 times, 1 s apart) before sending it to the DLQ.
     *
     * @param consumerRecord  the raw Kafka record; value is a deserialized {@link ClickEventMessage}
     * @param acknowledgment  manual acknowledgment handle; must be called to commit the offset
     */
    @KafkaListener(topics = Schema.Topics.CLICK_EVENT_RECEIVED)
    public void consume(ConsumerRecord<String, ClickEventMessage> consumerRecord, Acknowledgment acknowledgment) {
        messageConsumerUseCase.process(clickEventMessageMapper.toDomain(consumerRecord.value()));
        acknowledgment.acknowledge();
    }
}
