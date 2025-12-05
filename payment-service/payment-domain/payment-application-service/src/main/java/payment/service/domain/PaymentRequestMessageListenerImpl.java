package payment.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import payment.service.domain.dto.PaymentRequest;
import payment.service.domain.event.PaymentEvent;
import payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;

@Slf4j
@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

    private final PaymentRequestHelper paymentRequestHelper;
    private final PaymentCompletedMessagePublisher paymentCompletedMessagePublisher;
    private final PaymentCancelledMessagePublisher paymentCancelledMessagePublisher;
    private final PaymentFailedMessagePublisher paymentFailedMessagePublisher;


    public PaymentRequestMessageListenerImpl(PaymentRequestHelper paymentRequestHelper,
                                             PaymentCompletedMessagePublisher paymentCompletedEventDomainEventPublisher,
                                             PaymentCancelledMessagePublisher paymentCancelledEventDomainEventPublisher,
                                             PaymentFailedMessagePublisher paymentFailedEventDomainEventPublisher) {
        this.paymentRequestHelper = paymentRequestHelper;
        this.paymentCompletedMessagePublisher = paymentCompletedEventDomainEventPublisher;
        this.paymentCancelledMessagePublisher = paymentCancelledEventDomainEventPublisher;
        this.paymentFailedMessagePublisher = paymentFailedEventDomainEventPublisher;
    }

    @Override
    public void completePayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
        fireEvent(paymentEvent);
    }

    private void fireEvent(PaymentEvent paymentEvent) {
        log.info("Publishing payment event with payment id: {} and order id: {}",
                paymentEvent.getPayment().getId().getValue(),
                paymentEvent.getPayment().getOrderId().getValue());

        paymentEvent.fire();
    }

    @Override
    public void cancelPayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistCancelPayment(paymentRequest);
        fireEvent(paymentEvent);
    }
}
