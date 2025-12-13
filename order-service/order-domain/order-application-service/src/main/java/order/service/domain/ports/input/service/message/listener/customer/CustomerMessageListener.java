package order.service.domain.ports.input.service.message.listener.customer;

import order.service.domain.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel); // Needs to be implemented in the domain layer

}
