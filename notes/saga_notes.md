# Saga pattern notes

---

- SAGA: Distributed long running transactions across services.
  - Used for Long Lived Transactions. First invented in
[//]: # (    TODO link to publication)
- Chain of local ACID transactions to finalise a long running transaction across services.
  - Compensating transactions: Rollback in case of failure.