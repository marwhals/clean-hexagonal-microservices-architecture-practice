package service.domain.ports.output.message.publisher.payment;

import core.domain.event.OrderCancelledEvent;
import core.domain.event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
