## Notes from Concept and Coding HLD

### Networking Protocols
- Application layer protocols: 2 types -> Server Client, P2P
- Server client:
  - Request-Response model
  - operate on TCP so have an open connection
  - Examples: HTTP/HTTPs, FTP, SMTP (email with IMAP), Web Sockets 
- P2P :
  - operate on UDP, so mostly for live video calling, etc.
  - WebRTC

### Data Management in Microservices
- *Two Approaches:*
    - *Database for Each Individual Service:* Each microservice has its own dedicated database, promoting autonomy and isolation.
    - *Shared Database:* All microservices share a single database, simplifying data access but potentially leading to complexities.
- Why Database per Service is Preferred: Scalability, Isolation, Technology Flexibility
- Advantages of Shared Database: Join Query, Transactional Property (ACID)
- Drawbacks of Shared Database:
    - *Performance Bottlenecks:* Increased contention and performance issues as more services access the same database.
    - *Complexity:* Managing dependencies and ensuring consistency across multiple services becomes difficult.
    - *Limited Scalability:* Scaling the entire database is necessary, even if only one service needs more resources.

### Microservices Design Patterns
- ### *STRANGLER Pattern:*
  - Used to move from an existing monolitihic architecture to microservices
  - Use a controller to divert x% of traffic from monolitihic to microservices
  - Eventually divert all traffic to microservices and decomission the monolitihic flow (Like strangling the monolitihic flow)
    
- ### *SAGA:*
  - Purpose: Managing distributed transactions across multiple databases, ensuring data consistency even if some operations fail.
  - How it Works:
      - A sequence of local transactions is executed within each participating microservice.
      - Each transaction updates the database and publishes an event.
      - Subsequent transactions listen to these events and continue the process.
      - In case of failure, compensation events are published to undo completed operations and maintain consistency.
  - Types of Sagas:
      - *Choreography:* Each service manages its own transactions and listens to events from other services.
      - *Orchestration:* A centralized orchestrator manages the transaction flow and handles compensation logic.
  - Example:
      - An order processing saga involving services for order creation, inventory management, and payment processing.
      - If the payment service fails, compensation events are triggered to cancel the order and update inventory.
  - Advantages:
      - Guarantees data consistency in distributed systems.
      - Provides a mechanism for handling failures and rollbacks.
      - Allows for flexibility in service interactions.
  - Disadvantages:
      - Increased complexity compared to local transactions.
      - Requires careful design and implementation to ensure correctness.
  - Interview Question Example:
      - Explain how you would handle a transaction involving transferring money between two users in a microservice architecture.

- ### *CQRS Pattern:*
- *Purpose:* Separating read (query) operations from write (command) operations for better performance and scalability.
- *How it Works:*
    - The system maintains separate models for read and write operations.
    - Write operations are performed through commands, updating the write model.
    - Read operations access the read model, which can be optimized for fast retrieval.
- *Advantages:*
    - *Performance Improvement:* Optimized read models can handle queries more efficiently.
    - *Scalability:* Read and write models can be scaled independently based on their specific needs.
    - *Flexibility:* Allows for different data structures and query languages for read and write operations.
- *Example:*
    - A blog application where write operations are performed on a relational database, while read operations access a denormalized view optimized for fast search.
- *Challenges:*
    - Maintaining consistency between the read and write models.
    - Ensuring the read model is up-to-date with changes in the write model.
 
