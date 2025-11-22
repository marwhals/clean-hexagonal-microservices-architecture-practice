# Introduction to Domain-Driven Design

---

## Introduction to Domain-Driven Design

- Domain-Driven design offers solutions ot common problems when building enterprise software
  - *Domain model* - Conceptual model of the domain the defines the behaviour and data of a system
- Domain logic is in the centre of the software. Independent form other dependencies. For both DDD and hexagonal architecture
  - This separation makes the software easier to design, develop and test, build and maintain over time.
    - Keeps the domain logic separate and allows for adopting new technologies easily.

### Strategic DDD vs Tactical DDD

- Strategic DDD: Introduces boundaries for domain model. Single Bounded context per domain
  - **Domain** - operational area of your application
  - **Bounded Context** - Central Pattern in DDD. Boundary within a domain.
  - **Ubiquitous Language** Common Language used by domain experts and developers

---

## Tactical DDD - Focuses on the implementation details

- Tactical DDD - Implementation patterns
  - *entities*: Domain object with a unique identity. Embodies set of critical business rules.
  - *aggregates*: Group of Entity object which always need to be in a consistent state
    - Important thing with an aggregate is that it should be retrieved and stores as a whole in a consistent state
  - *Aggregate Root AR* - Entry point Entity for an aggregate. All business operations should go through root. An aggregate root should be referenced from outside through its root only.
    - An Aggregate Root should have pure, side effect free functions.
      - An aggregate root is responsible to enforce business environments.
      - *an important thing to help with keeping the aggregate in a consistent state is that an aggregate can only be referenced through the aggregate root from the outside.
        - All state altering operations should go through the root entity, even if the state is change is related with one of tow other entities.
        - The entities other than the aggregate roots, cannot be referenced by outside objects.
        - When completing all state change operations for an aggregate before saving the data you should enforce strict validations each time to keep the aggregate in a consistent state.
        - Should also enforce data store constraints and optimistic locking. This will prevent data loss in case of conflicting updates caused by multiple threads.
          - To decide which entity will be the aggregate roots you should consider if the entity will be modified independency and be access by the identifier form the outside, like from other aggregates.
          - In that case the identifier entity should be chosen as the aggregate root.
  - *Value Objects* - Immutable objects without identity. Only value matters. Brings context to the value. Allow business operations inside the object and allow validations in constructor.

[//]: # (TODO Include diagram)

- *Domain Events* - Decouple different domains. Describe things that happen and change the state of a domain.
  - Makes the system extendable. Domain event listeners runs in a different transaction than the event publishers.
  - In a Domain-driven system, domain events are a good way of achieving eventual consistency.
  - Any system or module that needs to update itself when something happens in another module or system cna subscribe to domain events coming from that system.
    - *Eventual Consistency* - All reads of the system will eventually return the latest value, provided no new updates are made.
- With such a system involving the distribution and consuming of events, is to have a system with a retry operation.
  - A message queue or an event log is a good match for this. Like Kafka.
  - *Event sourcing* - where you keep the state of a system as an ordered log of events. (doesn't have to be the persistence model for a system)
-  *Domain Services* - Business logic that cannot fit in the aggregate. Used when multiple aggregates are required in business logic. Can interact with other domain services.
- *Application Services* - Allows the isolated domain to communicate with the outside. Orchestrate transaction, security, looking up proper aggregates and saving state changes of the domain to the database. Does not contain any business logic.
  - Domain event listeners are a special kind of Application services that are triggered by domain events. Each domain event listener can have a separate domain service to handle business logic.

---

[//]: # (TODO Books)
- Domain Driven Design - Eric Evans
- Implementing Domain-Driven Design - Vaughn Vernon