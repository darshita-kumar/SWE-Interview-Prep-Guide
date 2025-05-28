# System Design HLD

##Topics:
- Vertical scaling: add more resources
- Preprocessing during non-peak hours to prep for peak hours
- Resilience: Avoid single-point-of-failure. Keep backups
- For growth: Horizontal scaling, microservice architecture, distributed systems

## Scaling
- Horizontal scaling vs Vertical scaling
- <img width="1159" alt="Screenshot 2025-05-28 at 11 38 38 PM" src="https://github.com/user-attachments/assets/fee3fe6c-cde1-41e6-b2ee-c8bd4f9163f3" />

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

