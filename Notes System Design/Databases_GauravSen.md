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
