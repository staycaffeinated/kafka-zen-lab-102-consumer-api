package zen.lab.consumer.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import zen.lab.consumer.domain.model.Context;
import zen.lab.consumer.domain.model.Product;

@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public final class ProductViewedEvent extends ClickEvent {
    private Product product;
    private Context context;
}
