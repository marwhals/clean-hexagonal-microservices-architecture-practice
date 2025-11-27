package payment.service.domain.entity;

import core.domain.entity.BaseEntity;
import core.domain.valueobject.CustomerId;
import core.domain.valueobject.Money;
import payment.service.domain.valueobject.CreditHistoryId;
import payment.service.domain.valueobject.TransactionType;

public class CreditHistory extends BaseEntity<CreditHistoryId> {

    private final CustomerId customerId;
    private final Money amount;
    private final TransactionType transactionType;

    private CreditHistory(Builder builder) {
        setId(builder.creditHistoryId);
        customerId = builder.customerId;
        amount = builder.amount;
        transactionType = builder.transactionType;
    }


    public Money getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public static final class Builder {
        private CreditHistoryId creditHistoryId;
        private CustomerId customerId;
        private Money amount;
        private TransactionType transactionType;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder creditHistoryId(CreditHistoryId val) {
            creditHistoryId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder amount(Money val) {
            amount = val;
            return this;
        }

        public Builder transactionType(TransactionType val) {
            transactionType = val;
            return this;
        }

        public CreditHistory build() {
            return new CreditHistory(this);
        }
    }
}
