package zen.lab.consumer.domain.events;

import java.io.Serializable;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public final class ProductAddedToCartEvent extends ClickEvent {

    private String productId;
    private Map<String, Serializable> metadata;
}
