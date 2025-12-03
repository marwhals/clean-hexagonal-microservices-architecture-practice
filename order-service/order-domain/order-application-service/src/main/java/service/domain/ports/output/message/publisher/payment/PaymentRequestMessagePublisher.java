package service.domain.ports.output.message.publisher.payment;

import service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);

}
