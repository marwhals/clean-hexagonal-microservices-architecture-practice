package payment.service.domain.mapper;

import core.domain.valueobject.CustomerId;
import core.domain.valueobject.Money;
import core.domain.valueobject.OrderId;
import org.springframework.stereotype.Component;
import payment.service.domain.dto.PaymentRequest;
import payment.service.domain.entity.Payment;

import java.util.UUID;

@Component
public class PaymentDataMapper {
    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }
}
