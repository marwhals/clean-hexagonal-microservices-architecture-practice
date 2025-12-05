package payment.service.domain.ports.output.message.publisher;

import payment.service.domain.outbox.model.OrderOutboxMessage;
import system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface RestaurantApprovalResponseMessagePublisher {

    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
