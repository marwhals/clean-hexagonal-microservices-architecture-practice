package customer.service.domain.ports.input.service;

import customer.service.domain.create.CreateCustomerCommand;
import customer.service.domain.create.CreateCustomerResponse;
import jakarta.validation.Valid;

public interface CustomerApplicationService {
    CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);
}
