package payment.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import payment.service.domain.dto.RestaurantApprovalRequest;
import payment.service.domain.ports.input.message.listener.RestaurantApprovalRequestMessageListener;
import restaurant.service.domain.event.OrderApprovalEvent;

@Slf4j
@Service
public class RestaurantApprovalRequestMessageListenerImpl implements RestaurantApprovalRequestMessageListener {

    private final RestaurantApprovalRequestHelper restaurantApprovalRequestHelper;

    public RestaurantApprovalRequestMessageListenerImpl(RestaurantApprovalRequestHelper restaurantApprovalRequestHelper) {
        this.restaurantApprovalRequestHelper = restaurantApprovalRequestHelper;
    }

    @Override
    public void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest) {
        OrderApprovalEvent orderApprovalEvent =
                restaurantApprovalRequestHelper.persistOrderApproval(restaurantApprovalRequest);
        orderApprovalEvent.fire();
    }
}
