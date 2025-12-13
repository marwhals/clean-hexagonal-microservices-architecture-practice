package order.service.dataaccess.customer.mapper;

import order.service.domain.entity.Customer;
import order.service.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;
import order.service.dataaccess.customer.entity.CustomerEntity;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }


}
