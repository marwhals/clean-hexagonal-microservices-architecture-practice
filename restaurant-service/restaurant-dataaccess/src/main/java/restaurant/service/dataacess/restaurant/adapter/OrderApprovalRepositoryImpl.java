package restaurant.service.dataacess.restaurant.adapter;

import org.springframework.stereotype.Component;
import payment.service.domain.ports.output.repository.OrderApprovalRepository;
import restaurant.service.dataacess.restaurant.mapper.RestaurantDataAccessMapper;
import restaurant.service.dataacess.restaurant.repository.OrderApprovalJpaRepository;
import restaurant.service.domain.entity.OrderApproval;

@Component
public class OrderApprovalRepositoryImpl implements OrderApprovalRepository {

    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    public OrderApprovalRepositoryImpl(OrderApprovalJpaRepository orderApprovalJpaRepository,
                                       RestaurantDataAccessMapper restaurantDataAccessMapper) {
        this.orderApprovalJpaRepository = orderApprovalJpaRepository;
        this.restaurantDataAccessMapper = restaurantDataAccessMapper;
    }

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        return restaurantDataAccessMapper
                .orderApprovalEntityToOrderApproval(orderApprovalJpaRepository
                        .save(restaurantDataAccessMapper.orderApprovalToOrderApprovalEntity(orderApproval)));
    }

}
