package payment.service.domain.ports.output.message.publisher;


import core.domain.event.publisher.DomainEventPublisher;
import payment.service.domain.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
