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

<br>

---
---

## Caching
- 2 considerations: When to update the cache? Which data to keep in the cache and which to evict?
- Cache eviction policies: LRU, LFU, Segmented LRU
- Good eviction policies imp as it prevents thrashing: Example when data is being queried in a sequence and the data which was just evicted is queried immediately
- Imp: where do you place your cache? Client side? Server side? As a different service queried via APIs?
- Typically in diff systems, all 3 of the above are used.
- Distributed caching for a large scale system: Benefits:
  - (1) cache can scale independently
  - (2) Deployments are separate
  - (3) Can use any language etc to write it

<br>

---
---

## Single point of failure
- How to avoid single point of failure: Redundancy to ensure high availability
- Server failure: Add more nodes
- DB failure: Use master slave architecture
- Load balancer/gateway failures: Connect via DNS
- Region failure: Multiple regions for deployment
- How to test this? DR tests, e.g. Netflix: Chaos Monkey

<br>

---
---

## Content Delivery Networks (CDN)
- Make systems cheaper and faster
- Like distributing cache around the globe to reduce latency
- Can store static info on (servers)CDNs across the globe for quick rendering
- CDNs are service providers which have servers which are cheap and follow region protocols (e.g. a movie can't be shown in US
- e.g. Amazon CloudFront, Akamai CDN

<br>

---
---

## Publisher Subscriber Model
- Fire and forget
- A lot of responsibilities are decoupled
- Each consumer can control the rate of consumption, consumer downtime does not affect publisher
- Simplified interactions, single interface of interaction bw the systems
- Acknowledgement received from consumers about consumption, 'transaction guarantee of at least once'
- More scalable
- Disadvantages: Can lead to poor consistency, so can't use it for mission critical systems like financial systems
- Idempotency not guaranteed: Idempotency means that applying the same operation multiple times produces the same result as applying it once (e.g. If the first msg consumption fails and if we allow data to reprocess again, if it makes a change to the db then idempotency is lost. e.g. In item-publisher, if kafka offset does't get committed, consumption of same msg twice does not change the db data twice)
- Used by Twitter, logging systems

<br>

---
---

## Event-driven systems
- A little diff from pub-sub model
- Used by Git, React, Node.js, games, etc.
- Each service stores event info relevant to itself and other services, so high availablitiy (but less consistency then)
- Each service's db stores an event log, so you can go back in time and reprocess certain events if required
- Replacement of an existing service with a new one is easy because all the relevant events are available in the db and can be replayed to bring the new service up-to-date with the one being replaced
- Transactional guarantee: at least one OR at most one guarantee
- Disadvantages: less consistency (events stored in 1 system related to another system might become outdated), No SLAs since event driven
- flow not easy to understand and debug

<br>

---
---


  
