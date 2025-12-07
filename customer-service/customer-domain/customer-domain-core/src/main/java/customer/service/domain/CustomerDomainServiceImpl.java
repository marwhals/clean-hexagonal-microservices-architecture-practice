package customer.service.domain;

import customer.service.domain.entity.Customer;
import customer.service.domain.event.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static core.domain.DomainConstants.UTC;

@Slf4j
public class CustomerDomainServiceImpl implements CustomerDomainService{
    @Override
    public CustomerCreatedEvent validateAndInitiateCustomer(Customer customer) {
        log.info("Customer with id: {} has been initialised", customer.getId().getValue());
        return new CustomerCreatedEvent(customer, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
