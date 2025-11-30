package service.domain.ports.output.message.publisher.payment;

import core.domain.event.OrderCreatedEvent;
import core.domain.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
