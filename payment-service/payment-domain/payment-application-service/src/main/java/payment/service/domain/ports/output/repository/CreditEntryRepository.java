package payment.service.domain.ports.output.repository;

import core.domain.valueobject.CustomerId;
import payment.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);

}
