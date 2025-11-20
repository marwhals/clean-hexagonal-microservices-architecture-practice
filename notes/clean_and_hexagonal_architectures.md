# Notes on Clean and Hexagonal architectures

---

## Introduction to clean architecture

- Separation of concerns by dividing software in to different layers
- Use Dependency Inversion and Polymorphism to create a dependency rule to make the domain layer the most independent layer.
  - Dependency Inversion - High level modules should not depend on low level modules
- Source code dependencies can only point inward, toward the domain layer
- Independent or Frameworks, UI, Databases and any external agency
- Testable domain layer without requiring an external layer
- Define entities and use cases for domain logic
- Same high level principle with hexagonal and onion architectures: Isolate the domain.

[//]: # (TODO Insert Diagram)

---

## Hexagonal Architecture

- Hexagonal Architecture also known as Ports and Adapters
  - Ports and Adapters - Separate insides (domain layer) and outsides(infrastructure layers)

[//]: # (TODO Insert Diagram)

- The principle of the hexagonal architecture is to isolate the domain from any dependency such as UI, Data layer or frameworks like Spring
- It is a response to the desire to create thoroughly testable applications with isolated business logic from the infrastructure and data sources.
  - Domain Layer - Should be the most independent and stable component of a system.
- Invented in 2005 by Alistair Cockburn
  - "Allow an application to equally by driven by users, programs, automated test or batch scripts and to be developed and tested in isolation from its eventual run-time devices and databases"
  - Primary Adapters - Implement the input ports to execute use cases
  - Secondary Adapters - Implement the output ports and called by business logic to complete external tasks.
  - Dependency Injection - Inject the implementation of ports at runtime

---

### Advantages of Clean and Hexagonal Architectures

- Especially useful for long-lasting applications that need to keep up with changing requirements
- Improvement to traditional Layered Architecture. The dependencies are not plugins to the business logic. All dependency arrows point to business logic making the domain independent. Reverses the relation using ports and adapters.
  - Clean Architecture - All dependency arrows point to the business layer, making it independent and stable.
- Delay the implementation details of dependencies.
- Easier to test the business logic by mocking the dependencies even before deciding the implementation details.
- Replace an adapter implementation easily, without touching the business logic.
- Easier to maintain, as changing a part of software will not affect the other parts.
- Independent development and deployment of different parts.
  - The price paid with clean or hexagonal architecture is writing more code and sometime duplicate code, like having different data transfer objects for different layers to represent some data
  - More code is written but the trade off is a fully maintainable and easily testable application. So an application should be independent of frameworks, user interface, database and external services.

[//]: # (TODO add diagram)

---

### Business Logic in clean Architecture

- **Entities**: Objects that embodies a small set of critical business rules
- **Use Cases**: Describes application-specific business rules. Contains the rules that specify how and when the Critical Business Rules within the Entities are invoked
    - Orchestrates the flow of data to and from the entities and direct those entities to use the Critical Business Rules to achieve the goals of the use case.

### Clean Architecture and Domain Driven Design (DDD)

- High level approach is the same
- Differences
  - In Clean Architecture Domain layer consists of:
    - Entities
    - Use Cases
  - In Domain Driven Design
    - Domain Layer consists of
      - Entities with an Aggregate root
      - Domain services
        - *Aggregate root* main business entity that orchestrates the core business rule of the current context. This context is called the Bounded Context
        - **Bounded Context** - Boundary within a domain with a particular domain model
        - **Aggregate** - Group of domain objects that always need to be in consistent state
        - **Domain Service** - Business logic that spans multiple entities and the logic that doesn't fit to an entity.
        - **Application Service** - First contact point from outside to the domain layer
          - Can handle validations, data mappings, transaction management and security

---

### Clean Architecture

[//]: # (TODO Read Clean architecture by Robert C Martin)