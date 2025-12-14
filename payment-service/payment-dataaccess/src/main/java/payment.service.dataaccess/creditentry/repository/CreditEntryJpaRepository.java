package payment.service.dataaccess.creditentry.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import payment.service.dataaccess.creditentry.entity.CreditEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditEntryJpaRepository extends JpaRepository<CreditEntryEntity, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // This will serialise multiple transactions
    Optional<CreditEntryEntity> findByCustomerId(UUID customerId);

}
