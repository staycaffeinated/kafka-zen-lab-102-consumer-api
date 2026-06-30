package zen.lab.consumer.adapters.edge.inbound.messages.model;

import java.io.Serializable;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public final class ProductAddedToCartEventMessage extends ClickEventMessage {

    private String productId;
    private Map<String, Serializable> metadata;
}
