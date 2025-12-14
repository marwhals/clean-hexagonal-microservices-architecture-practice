package payment.service.messaging.mapper;


import core.domain.event.payload.OrderPaymentEventPayload;
import core.domain.valueobject.PaymentOrderStatus;
import debezium.order.payment_outbox.Value;
import kafka.order.avro.model.PaymentResponseAvroModel;
import kafka.order.avro.model.PaymentStatus;
import org.springframework.stereotype.Component;
import restaurant.service.domain.dto.PaymentRequest;
import restaurant.service.domain.outbox.model.OrderEventPayload;

import java.time.Instant;
import java.util.UUID;

@Component
public class PaymentMessagingDataMapper {

    public PaymentRequest paymentRequestAvroModelToPaymentRequest(OrderPaymentEventPayload orderPaymentEventPayload,
                                                                  Value paymentRequestAvroModel) {
        return PaymentRequest.builder()
                .id(paymentRequestAvroModel.getId())
                .sagaId(paymentRequestAvroModel.getSagaId())
                .customerId(orderPaymentEventPayload.getCustomerId())
                .orderId(orderPaymentEventPayload.getOrderId())
                .price(orderPaymentEventPayload.getPrice())
                .createdAt(Instant.parse(paymentRequestAvroModel.getCreatedAt()))
                .paymentOrderStatus(PaymentOrderStatus.valueOf(orderPaymentEventPayload.getPaymentOrderStatus()))
                .build();
    }

    public PaymentResponseAvroModel orderEventPayloadToPaymentResponseAvroModel(String sagaId,
                                                                                OrderEventPayload orderEventPayload) {
        return PaymentResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId(sagaId)
                .setPaymentId(orderEventPayload.getPaymentId())
                .setCustomerId(orderEventPayload.getCustomerId())
                .setOrderId(orderEventPayload.getOrderId())
                .setPrice(orderEventPayload.getPrice())
                .setCreatedAt(orderEventPayload.getCreatedAt().toInstant())//??
                .setPaymentStatus(PaymentStatus.valueOf(orderEventPayload.getPaymentStatus()))
                .setFailureMessages(orderEventPayload.getFailureMessages())
                .build();
    }


}
