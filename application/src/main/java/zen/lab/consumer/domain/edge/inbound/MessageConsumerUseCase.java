package zen.lab.consumer.domain.edge.inbound;

public interface MessageConsumerUseCase {

    void receiveMessage(String message);
}
