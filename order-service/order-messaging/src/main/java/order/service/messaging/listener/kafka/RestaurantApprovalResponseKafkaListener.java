package order.service.messaging.listener.kafka;

import common.messaging.DebeziumOp;
import core.domain.event.payload.RestaurantOrderEventPayload;
import debezium.restaurant.order_outbox.Envelope;
import debezium.restaurant.order_outbox.Value;
import kafka.producer.KafkaMessageHelper;
import order.service.domain.excecption.OrderNotFoundException;
import kafka.consumer.KafkaConsumer;
import kafka.order.avro.model.OrderApprovalStatus;
import lombok.extern.slf4j.Slf4j;
import order.service.messaging.mapper.OrderMessagingDataMapper;
import org.postgresql.util.PSQLState;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import order.service.domain.ports.input.service.message.listener.restrauntapproval.RestaurantApprovalResponseMessageListener;

import java.sql.SQLException;
import java.util.List;

import static order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Component
public class RestaurantApprovalResponseKafkaListener implements KafkaConsumer<Envelope> {

    private final RestaurantApprovalResponseMessageListener restaurantApprovalResponseMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final KafkaMessageHelper kafkaMessageHelper;

    public RestaurantApprovalResponseKafkaListener(RestaurantApprovalResponseMessageListener
                                                           restaurantApprovalResponseMessageListener,
                                                   OrderMessagingDataMapper orderMessagingDataMapper,
                                                   KafkaMessageHelper kafkaMessageHelper) {
        this.restaurantApprovalResponseMessageListener = restaurantApprovalResponseMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}",
            topics = "${order-service.restaurant-approval-response-topic-name}")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of restaurant approval responses received!",
                messages.stream().filter(message -> message.getBefore() == null &&
                        DebeziumOp.CREATE.getValue().equals(message.getOp())).toList().size());

        messages.forEach(avroModel -> {
            if (avroModel.getBefore() == null && DebeziumOp.CREATE.getValue().equals(avroModel.getOp())) {
                log.info("Incoming message in RestaurantApprovalResponseKafkaListener: {}", avroModel);
                Value restaurantApprovalResponseAvroModel = avroModel.getAfter();
                RestaurantOrderEventPayload restaurantOrderEventPayload =
                        kafkaMessageHelper.getOrderEventPayload(restaurantApprovalResponseAvroModel.getPayload(),
                                RestaurantOrderEventPayload.class);
                try {
                    if (OrderApprovalStatus.APPROVED.name().equals(restaurantOrderEventPayload.getOrderApprovalStatus())) {
                        log.info("Processing approved order for order id: {}",
                                restaurantOrderEventPayload.getOrderId());
                        restaurantApprovalResponseMessageListener.orderApproved(orderMessagingDataMapper
                                .approvalResponseAvroModelToApprovalResponse(restaurantOrderEventPayload, restaurantApprovalResponseAvroModel));
                    } else if (OrderApprovalStatus.REJECTED.name().equals(restaurantOrderEventPayload.getOrderApprovalStatus())) {
                        log.info("Processing rejected order for order id: {}, with failure messages: {}",
                                restaurantOrderEventPayload.getOrderId(),
                                String.join(FAILURE_MESSAGE_DELIMITER,
                                        restaurantOrderEventPayload.getFailureMessages()));
                        restaurantApprovalResponseMessageListener.orderRejected(orderMessagingDataMapper
                                .approvalResponseAvroModelToApprovalResponse(restaurantOrderEventPayload, restaurantApprovalResponseAvroModel));
                    }
                } catch (OptimisticLockingFailureException e) {
                    log.error("Caught optimistic locking exception in RestaurantApprovalResponseKafkaListener for order id: {}",
                            restaurantOrderEventPayload.getOrderId());
                } catch (OrderNotFoundException e) {
                    log.error("No order found for order id: {}", restaurantOrderEventPayload.getOrderId());
                } catch (DataAccessException e) {
                    SQLException sqlException = (SQLException) e.getRootCause();
                    if (sqlException != null && sqlException.getSQLState() != null &&
                            PSQLState.UNIQUE_VIOLATION.getState().equals(sqlException.getSQLState())) {
                        log.error("Caught unique constraint exception with sql state: {} " +
                                        "in RestaurantApprovalResponseKafkaListener for order id: {}",
                                sqlException.getSQLState(), restaurantOrderEventPayload.getOrderId());
                    }
                }
            }
        });
    }

}
