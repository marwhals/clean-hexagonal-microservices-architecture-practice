package restaurant.service.domain.ports.output.message.publisher;

import restaurant.service.domain.outbox.model.OrderOutboxMessage;
import system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface PaymentResponseMessagePublisher {
    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
