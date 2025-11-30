package payment.service.domain;

import core.domain.event.publisher.DomainEventPublisher;
import core.domain.valueobject.OrderId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import payment.service.domain.dto.RestaurantApprovalRequest;
import payment.service.domain.mapper.RestaurantDataMapper;
import payment.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher;
import payment.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import payment.service.domain.ports.output.repository.OrderApprovalRepository;
import payment.service.domain.ports.output.repository.RestaurantRepository;
import restaurant.service.domain.RestaurantDomainService;
import restaurant.service.domain.entity.Restaurant;
import restaurant.service.domain.event.OrderApprovalEvent;
import restaurant.service.domain.event.OrderRejectedEvent;
import restaurant.service.domain.exception.RestaurantNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class RestaurantApprovalRequestHelper {

    private final RestaurantDomainService restaurantDomainService;
    private final RestaurantDataMapper restaurantDataMapper;
    private final RestaurantRepository restaurantRepository;
    private final OrderApprovalRepository orderApprovalRepository;
    private final OrderApprovedMessagePublisher orderApprovedMessagePublisher;
    private final OrderRejectedMessagePublisher orderRejectedMessagePublisher;

    public RestaurantApprovalRequestHelper(RestaurantDomainService restaurantDomainService,
                                           RestaurantDataMapper restaurantDataMapper,
                                           RestaurantRepository restaurantRepository,
                                           OrderApprovalRepository orderApprovalRepository,
                                           OrderApprovedMessagePublisher orderApprovedMessagePublisher,
                                           OrderRejectedMessagePublisher orderRejectedMessagePublisher) {
        this.restaurantDomainService = restaurantDomainService;
        this.restaurantDataMapper = restaurantDataMapper;
        this.restaurantRepository = restaurantRepository;
        this.orderApprovalRepository = orderApprovalRepository;
        this.orderApprovedMessagePublisher = orderApprovedMessagePublisher;
        this.orderRejectedMessagePublisher = orderRejectedMessagePublisher;
    }

    @Transactional // create database transaction before returning from this method
    public OrderApprovalEvent persistOrderApproval(RestaurantApprovalRequest restaurantApprovalRequest) {
        log.info("Processing restaurant approval for order id: {}", restaurantApprovalRequest.getOrderId());
        List<String> failureMessages = new ArrayList<>();
        Restaurant restaurant = findRestaurant(restaurantApprovalRequest);
        OrderApprovalEvent orderApprovalEvent =
                restaurantDomainService.validateOrder(
                        restaurant,
                        failureMessages,
                        orderApprovedMessagePublisher,
                        orderRejectedMessagePublisher);
        orderApprovalRepository.save(restaurant.getOrderApproval());
        return orderApprovalEvent;
    }

    private Restaurant findRestaurant(RestaurantApprovalRequest restaurantApprovalRequest) {

        Restaurant restaurant = restaurantDataMapper
                .restaurantApprovalRequestToRestaurant(restaurantApprovalRequest);

        Optional<Restaurant> restaurantResult = restaurantRepository.findRestaurantInformation(restaurant);

        if (restaurantResult.isEmpty()) {
            log.error("Restaurant with id " + restaurant.getId().getValue() + " not found!");
            throw new RestaurantNotFoundException("Restaurant with id " + restaurant.getId().getValue() +
                    " not found!");
        }

        Restaurant restaurantEntity = restaurantResult.get();
        restaurant.setActive(restaurantEntity.isActive());

        restaurant.getOrderDetail().getProducts().forEach(product ->
                restaurantEntity.getOrderDetail().getProducts().forEach(p -> {
                    if (p.getId().equals(product.getId())) {
                        product.updateWithConfirmedNamePriceAndAvailability(p.getName(), p.getPrice(), p.isAvailable());
                    }
                }));

        restaurant.getOrderDetail().setId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())));

        return restaurant;
    }


}
