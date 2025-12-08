package service.domain;

import core.domain.entity.Customer;
import core.domain.excecption.OrderDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import service.domain.dto.message.CustomerModel;
import service.domain.mapper.OrderDataMapper;
import service.domain.ports.input.service.message.listener.customer.CustomerMessageListener;
import service.domain.ports.output.repository.CustomerRepository;

@Slf4j
@Service
public class CustomerMessageImpl implements CustomerMessageListener {
    private final CustomerRepository customerRepository;
    private final OrderDataMapper orderDataMapper;

    public CustomerMessageImpl(CustomerRepository customerRepository,
                               OrderDataMapper orderDataMapper) {
        this.customerRepository = customerRepository;
        this.orderDataMapper = orderDataMapper;
    }


    @Override
    public void customerCreated(CustomerModel customerModel) {
        Customer customer = customerRepository.save(orderDataMapper.customerModelToCustomer(customerModel));
        if (customer == null) {
            log.error("Customer could not be created in order database with id: {}", customerModel.getId());
            throw new OrderDomainException("Customer could not be created in order database with id " +
                    customerModel.getId());
        }
        log.info("Customer is created in order database with id: {}", customer.getId());
    }
}
