# System Design HLD

## Topics:
- Vertical scaling: add more resources
- Preprocessing during non-peak hours to prep for peak hours
- Resilience: Avoid single-point-of-failure. Keep backups
- For growth: Horizontal scaling, microservice architecture, distributed systems

## Scaling
- Horizontal scaling vs Vertical scaling
<img width="1159" alt="Screenshot 2025-05-28 at 11 38 38 PM" src="https://github.com/user-attachments/assets/fee3fe6c-cde1-41e6-b2ee-c8bd4f9163f3" />

<br>

---
---

## Load balancing: Consistent Hashing
- Say you hash each request using a hashing function to map it to a server (load balancer)
- How to add more servers now? -> Difficult since previous servers mappings will change, so very costly
- Plus caching becomes difficult if more servers are added
- Solution: consistent hashing - like a ring of servers and requests, all mapped in the same ring
- Advantages:
  - Average load factor for each server: 1/(no of servers)
  - Adding/removing servers is easier
- Disadvantages:
  - Load can be skewed
- Fixing uniform distribution: virtual server IDs mapped in the same ring

<br>

---
---

## Hashing & collision resolution
- https://www.hackerearth.com/practice/data-structures/hash-tables/basics-of-hash-tables/tutorial/
- To achieve a good hashing mechanism, It is important to have a good hash function with the following basic requirements:
  - Easy to compute: It should be easy to compute and must not become an algorithm in itself.
  - Uniform distribution: It should provide a uniform distribution across the hash table and should not result in clustering.
  - Less collisions: Collisions occur when pairs of elements are mapped to the same hash value. These should be avoided.
    
### Collision resolution techniques:
1. Separate chaining (Open hashing):
- Each table entry is a linkedList
- Good for high number of table entries

2. Linear probing (open addressing or closed hashing):
- If acquired hash is filled, linearly search for the next available position

3. Quadratic probing:
- In case of a collision, the next available position will be probed by adding the successive value of an arbitrary polynomial in the original hashed index.

4. Double hashing:
- Here the interval between probes is computed by using two hash functions.

<br>

---
---

## Message Queues
- Async processing using message queues.
- Functionalities: Scheduling tasks/messages, load balancing, checking for heartbeat, etc.
<img width="754" alt="Screenshot 2025-05-29 at 12 35 38 AM" src="https://github.com/user-attachments/assets/6935f474-eee4-4811-bb82-75f92fb243cb" />

<br>

---
---

## Monoliths vs Microservices
- Note: Monoliths can also scale horizontally, that is not the point of difference.
- https://buttercms.com/books/microservices-for-startups/should-you-always-start-with-a-monolith/
- https://highscalability.com/do-you-have-too-many-microservices-five-design-attributes-th/
<img width="756" alt="Screenshot 2025-05-29 at 11 17 49 PM" src="https://github.com/user-attachments/assets/9cb7c00d-3002-4196-a750-2795759accc6" />
<img width="763" alt="Screenshot 2025-05-29 at 11 18 45 PM" src="https://github.com/user-attachments/assets/4a631083-f799-41be-a6d9-c63233f3a89b" />

## Sharding
- Types of partitioning: Horizontal(Sharding), Vertical, Hierarchical
- Vertical partitioning is normalisation. Implemented in application logic
- Horizontal sharding is partitioning data based on some key in each row
- Hierarchical sharding means breaking down each partition into smaller paritions if too large
- You can create an index on each shard if required. This does not have to be equal to the parition key
- Read and write queries are optimised as all queries fall on a single shard
- Good tolerance for single point of failure as each shard follows master-slave architecture
- Cons:
    - Inflexible (difficult to increase/decrease no. of shards),
    - Cross-shard joins are difficult
    - Difficult to implement
    - Hotspots: Uneven distribution of data (max data in a few shards, others empty)
- Cassandra, HBase, HDFS, and MongoDB are popular distributed databases. Non-sharded modern databases: Sqlite, Redis (spec in progress), Memcached, and Zookeeper
- Memcached is not sharded on its own, but expects client libraries to distribute data within a cluster

### Sharding in depth:
- https://medium.com/@jeeyoungk/how-sharding-works-b4dec46b3f6
- Shard or Partition Key is a portion of primary key which determines how data should be distributed
- A logical shard is a collection of data sharing the same partition key. A database node, sometimes referred as a physical shard, contains multiple logical shards
- Algorithmic sharding:
    - Implementing sharding at client side
    - Partitioned queries are used to identify the right shard to query the db
    - suitable for key-value databases with homogeneous values to avoid hotspots
    - Consistent hashing can be used to allow re-sharding of data
- Dynamic sharding:
    - An external locator service determines the location of entries
    - more resilient to nonuniform distribution of data. Locators can be created, split, and reassigned to redistribute data
    - The locator service becomes a single point of contention and failure. Also, locators cannot be cached or replicated simply. Out of date locators will route operations to incorrect databases
    - Hence, consistency is prioritised in locators
- Entity-groups:
    - Store related entities in the same partition to provide additional capabilities within a single partition
    - This is a popular approach to shard a relational database
-  Hierarchical keys & Column-Oriented Databases:
    - 

  
