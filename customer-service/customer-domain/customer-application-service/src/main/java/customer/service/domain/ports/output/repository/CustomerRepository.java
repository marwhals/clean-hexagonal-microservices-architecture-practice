package customer.service.domain.ports.output.repository;

import customer.service.domain.entity.Customer;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);

}
