package zen.lab.consumer.adapters.port.inbound.messages;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.Instant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.Acknowledgment;
import zen.lab.consumer.adapters.port.inbound.messages.model.ClickEventMessage;
import zen.lab.consumer.adapters.port.inbound.messages.model.ProductViewedEventMessage;
import zen.lab.consumer.application.port.inbound.MessageConsumerUseCase;
import zen.lab.consumer.domain.events.ProductViewedEvent;

@ExtendWith(MockitoExtension.class)
class ClickEventMessageConsumerTest {

    @Mock
    MessageConsumerUseCase messageConsumerUseCase;

    @Mock
    ClickEventMessageMapper clickEventMessageMapper;

    @Mock
    Acknowledgment acknowledgment;

    @InjectMocks
    ClickEventMessageConsumer consumer;

    private static final ClickEventMessage A_MESSAGE = ProductViewedEventMessage.builder()
            .eventId("evt-1")
            .eventType("product.viewed")
            .build();

    private static final ProductViewedEvent A_DOMAIN_EVENT = new ProductViewedEvent(
            "evt-1", "product.viewed", Instant.EPOCH, "usr-1", "sess-1", null, null, null);

    private static ConsumerRecord<String, ClickEventMessage> aRecord(ClickEventMessage value) {
        return new ConsumerRecord<>(Schema.Topics.CLICK_EVENT_RECEIVED, 0, 0L, "key", value);
    }

    @Nested
    class HappyPath {

        @Test
        void consume_delegatesToMapperUseCaseAndAcknowledges() {
            when(clickEventMessageMapper.toDomain(A_MESSAGE)).thenReturn(A_DOMAIN_EVENT);

            consumer.consume(aRecord(A_MESSAGE), acknowledgment);

            verify(clickEventMessageMapper).toDomain(A_MESSAGE);
            verify(messageConsumerUseCase).process(A_DOMAIN_EVENT);
            verify(acknowledgment).acknowledge();
            verifyNoMoreInteractions(messageConsumerUseCase, acknowledgment);
        }
    }

    @Nested
    class FailurePath {

        @Test
        void consume_doesNotAcknowledgeWhenUseCaseThrows() {
            when(clickEventMessageMapper.toDomain(A_MESSAGE)).thenReturn(A_DOMAIN_EVENT);
            doThrow(new RuntimeException("processing failed"))
                    .when(messageConsumerUseCase)
                    .process(A_DOMAIN_EVENT);

            var consumerRecord = aRecord(A_MESSAGE);
            assertThatThrownBy(() -> consumer.consume(consumerRecord, acknowledgment))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("processing failed");

            verifyNoInteractions(acknowledgment);
        }

        @Test
        void consume_doesNotAcknowledgeWhenMapperThrows() {
            doThrow(new RuntimeException("deserialization failed"))
                    .when(clickEventMessageMapper)
                    .toDomain(A_MESSAGE);

            var consumerRecord = aRecord(A_MESSAGE);
            assertThatThrownBy(() -> consumer.consume(consumerRecord, acknowledgment))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("deserialization failed");

            verifyNoInteractions(messageConsumerUseCase, acknowledgment);
        }
    }
}
