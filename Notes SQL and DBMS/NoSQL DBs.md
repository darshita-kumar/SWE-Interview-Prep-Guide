## NoSQL Databases

- BASE is often used to describe the properties of NoSQL databases.
- In comparison with the CAP Theorem, BASE chooses availability over consistency
- BASE:
  1. Basically available - the system guarantees availability.
  2. Soft state - the state of the system may change over time, even without input.
  3. Eventual consistency - the system will become consistent over a period of time, given that the system doesn't receive input during that period

### Types of NoSQL databases
1. Key-value store
2. Document store
3. Wide-column store
4. Graph Databases

### Key Value store db
- Like a hashmap, allow O(1) reads and writes, so used in caches
- Backed my memory/SSD
- e.g. Redis, Memecached

### Document store
- Stores document data (XML, JSON, binary, etc)
<img width="1247" alt="Screenshot 2025-06-21 at 5 19 12 PM" src="https://github.com/user-attachments/assets/175c1575-5bf6-4482-b198-6be0fd831a62" />
- Document stores provide APIs or a query language to query based on the internal structure of the document itself
- e.g. MongoDB, CouchDB
- Fault tolerant, high availability

### Wide-column store (Columnar database)
- Stores data in columns
- So contradictory to SQL db, it can read only certain columns in the database and does not need to fetch data row wise, e.g:
<img width="1240" alt="Screenshot 2025-06-21 at 5 17 49 PM" src="https://github.com/user-attachments/assets/6d737cd6-fb69-4da1-b0a2-66a5cc7809ff" />
- e.g. Cassandra
- Stores such as BigTable, HBase, and Cassandra maintain keys in lexicographic order, allowing efficient retrieval of selective key ranges
- Wide column stores offer high availability and high scalability, suitable for large datasets

### Graph Databases
<img width="781" alt="Screenshot 2025-06-22 at 4 18 16 PM" src="https://github.com/user-attachments/assets/85a7c76c-7ad9-4edd-99a9-fb42b9b4fda9" />
- Graph databases are optimized to represent complex relationships with many foreign keys or many-to-many relationships
- e.g. Neo4J, FlockDB
