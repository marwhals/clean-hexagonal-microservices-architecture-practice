package customer.service.domain;

import customer.service.domain.entity.Customer;
import customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);
}



