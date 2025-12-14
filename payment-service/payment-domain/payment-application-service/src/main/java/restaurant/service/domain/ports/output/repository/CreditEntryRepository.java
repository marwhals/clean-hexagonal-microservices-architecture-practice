package restaurant.service.domain.ports.output.repository;

import order.service.domain.valueobject.CustomerId;
import payment.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);

}
