package zen.lab.consumer.adapters.edge.inbound.messages.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public final class ProductViewedEventMessage extends ClickEventMessage {

    private ProductMessage product;
    private ContextMessage context;
}
