package order.service.messaging.publisher.kafka;

import core.domain.event.OrderCreatedEvent;
import core.domain.event.publisher.DomainEventPublisher;
import kafka.order.avro.model.PaymentRequestAvroModel;
import kafka.producer.KafkaMessageHelper;
import kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import order.service.messaging.mapper.OrderMessagingDataMapper;
import org.springframework.stereotype.Component;
import service.domain.config.OrderServiceConfigData;

@Slf4j
@Component
public class CreateOrderKafkaPublisher implements DomainEventPublisher<OrderCreatedEvent> {
    //^^^^ Output port in Hexagonal architecture terms


    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper orderKafkaMessageHelper;

    public CreateOrderKafkaPublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                     OrderServiceConfigData orderServiceConfigData,
                                     KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer, KafkaMessageHelper orderKafkaMessageHelper) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.orderKafkaMessageHelper = orderKafkaMessageHelper;
    }

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();
        log.info("Received OrderCreatedEvent for order id: {}", orderId);
        try {
            PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
                    .orderCreatedEventToPaymentRequestAvroModel(domainEvent);
            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
                    orderId,
                    paymentRequestAvroModel,
                    orderKafkaMessageHelper.
                            getKafkaCallback(orderServiceConfigData.getPaymentResponseTopicName(),
                                    paymentRequestAvroModel,
                                    orderId,
                                    "PaymentRequestAvroModel")
            );

            log.info("PaymentRequestAvroModel sent to Kafka for order id: {}", paymentRequestAvroModel.getOrderId());
        } catch (Exception e) {
            log.error("Error while sending PaymentRequestAvroModel message" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }

}
