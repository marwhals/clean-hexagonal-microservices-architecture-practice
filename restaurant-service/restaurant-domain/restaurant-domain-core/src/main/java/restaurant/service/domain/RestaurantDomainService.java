package restaurant.service.domain;

import core.domain.event.publisher.DomainEventPublisher;
import restaurant.service.domain.entity.Restaurant;
import restaurant.service.domain.event.OrderApprovalEvent;
import restaurant.service.domain.event.OrderApprovedEvent;
import restaurant.service.domain.event.OrderRejectedEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant,
                                     List<String> failureMessages,
                                     DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
                                     DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher);

}
