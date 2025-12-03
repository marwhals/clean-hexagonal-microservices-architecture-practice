package system.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
