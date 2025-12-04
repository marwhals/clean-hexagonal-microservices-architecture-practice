package core.domain;

import core.domain.entity.Order;
import core.domain.entity.Restaurant;
import core.domain.event.OrderCancelledEvent;
import core.domain.event.OrderCreatedEvent;
import core.domain.event.OrderPaidEvent;
import core.domain.event.publisher.DomainEventPublisher;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order,
                                               Restaurant restaurant);

    OrderPaidEvent payOrder(Order order, DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher);

    void approveOrder(Order order);


    OrderCancelledEvent cancelOrderPayment(Order order,
                                           List<String> failureMessages,
                                           DomainEventPublisher<OrderCancelledEvent>
                                                   orderCancelledEventDomainEventPublisher);

    void cancelOrder(Order order, List<String> failureMessages);

}
