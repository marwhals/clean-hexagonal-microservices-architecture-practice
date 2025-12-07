package customer.service.domain.event;

import core.domain.event.DomainEvent;
import customer.service.domain.entity.Customer;

import java.time.ZonedDateTime;

public class CustomerCreatedEvent implements DomainEvent<Customer> {

    private final Customer customer;

    private ZonedDateTime createdAt;

    public CustomerCreatedEvent(Customer customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }
}
