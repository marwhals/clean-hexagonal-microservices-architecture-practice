package service.domain;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import service.domain.dto.message.RestaurantApprovalResponse;
import service.domain.ports.input.service.message.listener.restrauntapproval.RestaurantApprovalResponseMessageListener;

@Slf4j
@Service
@Validated
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {

    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

    }
}
