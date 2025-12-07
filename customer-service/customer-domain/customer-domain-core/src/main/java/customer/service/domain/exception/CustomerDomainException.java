package customer.service.domain.exception;

import core.domain.exception.DomainException;

public class CustomerDomainException extends DomainException {
    public CustomerDomainException(String message) {
        super(message);
    }
}
