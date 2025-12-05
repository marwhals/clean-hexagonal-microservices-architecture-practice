package restaurant.service.domain;

import restaurant.service.domain.entity.Restaurant;
import restaurant.service.domain.event.OrderApprovalEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant,
                                     List<String> failureMessages);

}
