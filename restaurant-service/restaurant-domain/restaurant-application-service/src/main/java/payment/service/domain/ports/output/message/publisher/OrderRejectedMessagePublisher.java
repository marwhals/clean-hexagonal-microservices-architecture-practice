package payment.service.domain.ports.output.message.publisher;

import core.domain.event.publisher.DomainEventPublisher;
import restaurant.service.domain.event.OrderApprovedEvent;

public interface OrderRejectedMessagePublisher extends DomainEventPublisher<OrderApprovedEvent> {
}
