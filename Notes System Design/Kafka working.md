## Kafka in-depth

### What kind of architecture do kafka brokers follow for it's producer? Master-slave or multi-master? How does the replication work?

✅ Kafka Broker Architecture for Producers
- Kafka follows a multi-master architecture — not master-slave.
- All Kafka brokers are equal from a producer’s point of view.
- Any broker can receive data from a producer and will route it to the correct partition leader. Kafka does not rely on a single master broker for writes.
- So:
  Multi-master for producers ✅
  No single point of write ❌

🔁 How Kafka Replication Works
1. Partitions and Leaders:
   - Each topic is divided into partitions.
   - Each partition has: One leader replica, Zero or more follower replicas

2. Leader-Follower Replication (within a partition)
   - The leader handles all reads and writes for a given partition.
   - Followers replicate the data from the leader asynchronously. Followers are spread across other brokers for fault tolerance.

3. Replication Factor
   - You define this at topic creation.
   - E.g., replication.factor = 3 → 1 leader + 2 followers

4. In-Sync Replicas (ISR)
  - Only followers that are caught up with the leader are considered in-sync.
  - Kafka only acknowledges a write if the data is written to all in-sync replicas (based on acks config).

🧠 Example Scenario
- Suppose a topic has: 3 partitions: P0, P1, P2, Replication factor: 3
- Then:
  - Each partition has 1 leader and 2 followers.
  - Let’s say Broker 1 is leader for P0, Broker 2 for P1, Broker 3 for P2.
  - Producers send messages to any broker, which routes them to the right partition leader.
  - Followers on other brokers pull data from the leader to keep replicas up to date.

![ChatGPT Image Jun 28, 2025, 11_55_59 PM](https://github.com/user-attachments/assets/06d96bd3-f8ac-41ac-a304-b19ae131d90c)

<br>

---
---

### So each consumer subscribes to only one replica of each partition, right? Does it have to be the leader of each partition?

✅ Yes — Consumers Read Only from the Leader Replica
- Consumers always read from the leader replica of each partition.
- Even though partitions are replicated across brokers, only the leader serves read and write requests.
- Followers are purely for failover and redundancy, not for reading.

❓ Why Not Read from Followers?
- Kafka’s design prioritizes:
- Strong consistency: You get the latest committed data.
- Simplicity: No need to coordinate multiple versions of data from replicas.
- Avoiding stale reads: Followers might lag behind the leader.
- Kafka does not support follower reads out-of-the-box (as of now), but KIP-392 and KIP-405 aim to allow this in some future enhancements.

<br>

---
---

### So who coordinates the writes to the brokers and reads from them? What is the role of zookeeper in all this? Where and how is offset commit data stored for each partition?

🧠 Who Coordinates Writes and Reads?
🔸 Writes (Producer to Broker)
- Producer decides which partition to write to:
- Either using a partitioner function (e.g., based on key hash) or Kafka assigns partitions in round-robin if no key
- Producer queries any broker to get metadata about:
  - Which broker is the leader for each partition
  - Then sends the message directly to the leader broker for that partition

➡️ So, producers coordinate writes using metadata from Kafka brokers (not ZooKeeper directly).

🔸 Reads (Consumer from Broker)
- Consumer group coordinator assigns partitions to consumers in a group (based on group.id)
- Each consumer then pulls data from the leader of its assigned partitions

➡️ So, consumers pull from partition leaders. The consumer group coordinator (a broker) handles the assignment.

<img width="813" alt="Screenshot 2025-06-28 at 11 58 58 PM" src="https://github.com/user-attachments/assets/e93534e3-dc55-47c0-8da9-6a844542a330" />

🗃️ Where and How Is Offset Commit Data Stored?
🔸 Default: __consumer_offsets Topic
Kafka stores consumer offsets in a special internal topic: __consumer_offsets

Each record in this topic looks like:
```
(consumer group id, topic, partition) → offset
```
This topic is: Replicated, Partitioned, Used by the group coordinator to track committed offsets

➡️ Offset commit can happen:
- Automatically (via enable.auto.commit=true)
- Manually (via commitSync() or commitAsync())

📍 Location of __consumer_offsets
- This topic is spread across brokers
- It uses log compaction to keep only the latest offset per group-topic-partition key

