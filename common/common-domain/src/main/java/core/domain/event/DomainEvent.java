package core.domain.event;

public interface DomainEvent<T> {
    void fire();
}
