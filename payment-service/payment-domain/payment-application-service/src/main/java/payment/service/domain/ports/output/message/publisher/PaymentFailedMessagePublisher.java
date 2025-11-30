package payment.service.domain.ports.output.message.publisher;


import core.domain.event.publisher.DomainEventPublisher;
import payment.service.domain.event.PaymentFailedEvent;

public interface PaymentFailedMessagePublisher extends DomainEventPublisher<PaymentFailedEvent> {
}
