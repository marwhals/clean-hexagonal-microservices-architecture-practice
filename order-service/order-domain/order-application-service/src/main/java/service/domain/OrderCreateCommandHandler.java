package service.domain;

import core.domain.OrderDomainService;
import core.domain.entity.Customer;
import core.domain.entity.Order;
import core.domain.entity.Restaurant;
import core.domain.event.OrderCreatedEvent;
import core.domain.excecption.OrderDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.domain.dto.create.CreateOrderCommand;
import service.domain.dto.create.CreateOrderResponse;
import service.domain.mapper.OrderDataMapper;
import service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentReqeustMessagePublisher;
import service.domain.ports.output.repository.CustomerRepository;
import service.domain.ports.output.repository.OrderRepository;
import service.domain.ports.output.repository.RestaurantRepository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateCommandHandler {

    private final OrderCreateHelper orderCreateHelper;

    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentReqeustMessagePublisher orderCreatedPaymentReqeustMessagePublisher;

    public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper,
                                     OrderDataMapper orderDataMapper,
                                     OrderCreatedPaymentReqeustMessagePublisher orderCreatedPaymentReqeustMessagePublisher) {
        this.orderCreateHelper = orderCreateHelper;
        this.orderDataMapper = orderDataMapper;
        this.orderCreatedPaymentReqeustMessagePublisher = orderCreatedPaymentReqeustMessagePublisher;
    }

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        orderCreatedPaymentReqeustMessagePublisher.publish(orderCreatedEvent);
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(), "Order Created Successfully");
    }

}
