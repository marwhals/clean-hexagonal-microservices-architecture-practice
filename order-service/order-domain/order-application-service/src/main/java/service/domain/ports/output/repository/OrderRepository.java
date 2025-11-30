package service.domain.ports.output.repository;

import core.domain.entity.Order;
import core.domain.valueobject.OrderId;
import core.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);

}
