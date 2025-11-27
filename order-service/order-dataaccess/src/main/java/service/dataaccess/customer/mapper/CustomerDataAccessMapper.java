package service.dataaccess.customer.mapper;

import core.domain.entity.Customer;
import core.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;
import service.dataaccess.customer.entity.CustomerEntity;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }

}
