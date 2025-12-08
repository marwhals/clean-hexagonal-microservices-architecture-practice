package customer.service.dataaccess.adapter;

import customer.service.dataaccess.mapper.CustomerDataAccessMapper;
import customer.service.dataaccess.repository.CustomerJpaRepository;
import customer.service.domain.entity.Customer;
import customer.service.domain.ports.output.repository.CustomerRepository;

public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDataAccessMapper.customerEntityToCustomer(
                customerJpaRepository.save(
                        customerDataAccessMapper.customerToCustomerEntity(customer)
                )
        );
    }
}
