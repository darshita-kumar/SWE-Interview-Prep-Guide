# Database Concepts

<img width="1035" alt="Screenshot 2025-06-06 at 9 37 11 PM" src="https://github.com/user-attachments/assets/e2e67f26-f737-4a5a-9e2d-55f95ee7bdca" />

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
    - more resilient to non-uniform distribution of data. Locators can be created, split, and reassigned to redistribute data
    - The locator service becomes a single point of contention and failure. Also, locators cannot be cached or replicated simply. Out of date locators will route operations to incorrect databases
    - Hence, consistency is prioritised in locators
- Entity-groups:
    - Store related entities in the same partition to provide additional capabilities within a single partition
    - This is a popular approach to shard a relational database
-  Hierarchical keys & Column-Oriented Databases:
    - A primary key is composed of a pair (row key, column key)
    - Entries with the same partition key are stored together
    - Column-oriented databases can model a problem such as time series efficiently

<br>

---
---

## NoSQL Databases
- Used to store schemaless data
- Not read optimised for reading a specific data value, joins not possible
- Replication factor: For distibuted databases having the same data in multiple nodes for high availability
- Quorum: Due to multiple nodes having the data, reads are optimised now and data can be read from any cluster. A quorum makes sure the data is not stale. Say replication factor is 3, then 2 nodes may be asked for consenses before returning a read value for a data point, in case the original write cluster goes down.
- Cassandra: SST (Sorted String Tables):
  - Cassandra stores all writes in-memory first as a log file.
  - Data written in a sequential fashion, with multiple entries allowed for a key, where table is sorted based on key
  - Periodically, this data is dumped to the clsuters where the data has to be written
  - SSTs are immutable
  - Compaction: mergeSort 2 SSTs for the same key before writing to db, so some records get deleted to optimise for space
  - How to deal with deleted records? Cassandra places a tombstone on these (like a flag)

<br>

---
---

## How DBs scale writes:
- https://www.youtube.com/watch?v=_5vrfuwhvlQ&ab_channel=GauravSen
- Some NoSQL databases use B+ trees : good insertion and search times, Some use SSTs
- Other methods: LSM, Fractal trees
- Working of SST explained:
- Basic idea: Used linkedLists (like logs) to insert at the end so writes are fast. But then reads become extremely slow. So, use sorted arrays for reads (binary search to read)
- Use multiple sorted arrays to store data because 1 sorted array only will make sorting slow as data increases.
- From time to time, can merge sorted arrays and use a **bloom filter** on each sorted array to find the data to read (as too many sorted arrays will again make reads slow)
- The above sorted arrays are called Sorted String Tables (SSTs), the merging of the sorted arrays is Compaction
  
<br>

---
---

## Consistency in Distributed Systems
- https://www.youtube.com/watch?v=m4q7VkgDWrM&ab_channel=GauravSen
- Distributed systems fix the problems of Single point of failure, latency but introduce the problem of consistency
- Consistency vs Availability is a trade-off
- How to ensure consistency between 2 servers (when network may be unreliable): Two Generals problem -> infinite loop of ACKs
- So do writes on only 1 db: Master slave architecture (leader-follower), read from all. But now need to sync data to all slaves
- See 2 phase commit protocol: From Master: Prepare request -> wait for ACKs and then commit -> wait for ACKs
- <img width="1230" alt="Screenshot 2025-06-06 at 11 33 49 PM" src="https://github.com/user-attachments/assets/7bf7d8f6-8f47-487e-87bc-ab46ef1b298c" />
- High Consistency: place locks on all DBs in the distributed structure and wait for writes, don't allow reads. This compromises availability
- Most systems: Evenutal consistency

<br>

---
---

## Selecting the right db
- https://www.youtube.com/watch?v=6GebEqt6Ynk&t=59s&ab_channel=Jordanhasnolife
- 2 types of DBs: (1) Use LSM trees and SSTables  (2) Use B-trees

### LSM trees and SSTables
- <img width="1252" alt="Screenshot 2025-06-15 at 12 34 05 PM" src="https://github.com/user-attachments/assets/f451c898-69f7-47ef-8623-35b0fd549c26" />
- Not as good as B-Trees for reads
- Writes go to RAM so they are fast
- Since the keys are sorted, can use Binary search to look for a record
- Can also use Bloom filters to speed up the searches

### B-Trees
- Implemented completely on disk
- <img width="1244" alt="Screenshot 2025-06-15 at 12 37 17 PM" src="https://github.com/user-attachments/assets/9aee9956-3c63-4623-ba86-c9187a88421d" />

### Replication
- Single leader: if primary goes down, a new primary is elected from the workers (no write conflicts, lesser throughpput)
- Multi-leader: multiples replicas can be written to, when reading a value, a quorum is used to decide the correct value (Data consistency is an issue, write conflicts)
<img width="1251" alt="Screenshot 2025-06-15 at 12 39 04 PM" src="https://github.com/user-attachments/assets/448d81a3-dac3-4e50-ac01-acc8294fc17a" />
<img width="1241" alt="Screenshot 2025-06-15 at 12 42 28 PM" src="https://github.com/user-attachments/assets/653638dd-a6e3-4fc4-a470-a72a3654b5a0" />

### SQL-DBs
- Better for normalisation, Joins, reads. But slow writes
- ACID, use B-Trees, guarantee correctness
- A lot of times writes may be to diff DBs in diff nodes, this causes issues in scaling. Need to use 2-phase-commit protocol to resolve this
- Distributed transations are very difficult to implement
- e.g. Banking transactions, job-scheduling

### No-SQL DBs
1. Mongo DB
2. Cassandra DB
   <img width="1233" alt="Screenshot 2025-06-15 at 1 52 46 PM" src="https://github.com/user-attachments/assets/6ce89f59-10d6-4ebf-8af1-525ca21dae80" />
   Last write times are not reliable unless a global GPS clock is used
   Okay to use if consistency can be compromised
3. Riak: same as Cassandra, but solves the problem of conflict resolution well, key-value store
4. Apache HBase
    <img width="1233" alt="Screenshot 2025-06-15 at 1 57 18 PM" src="https://github.com/user-attachments/assets/e32827e9-bd19-4157-9c30-5aa0fd36f55f" />
5. Memcached, Redis: implemented in memory, not good DBs but caching, use hashmap
   Redis: supports geosharding
   <img width="1230" alt="Screenshot 2025-06-15 at 2 01 36 PM" src="https://github.com/user-attachments/assets/01497e99-054b-4fa9-b207-5b556ef2b5d0" />
   HashMap bad for range queries, no indexes
6. Neo4j
   <img width="1239" alt="Screenshot 2025-06-15 at 2 05 19 PM" src="https://github.com/user-attachments/assets/5762ead2-a800-43c8-9297-8717533f4090" />
   Pointers from each data point to all connecting data points (like an edge)
7. TimeScaleDB/Apache Druid: time series db, maintains order relative to timestamp
   Use LSM trees and SSTables, but more efficient
   Split table into many smaller chunk indexes, so entire index is stores in memory, so CPU cache is used efficiently
   Enables deletion of data easily as opposed to the tombstone method of deletion (because need to wait for SSTables to merge for deleting tombstone)
