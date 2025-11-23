package service.domain.ports.output.message.publisher.restaurantapproval;

import core.domain.event.OrderPaidEvent;
import core.domain.event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
