# System Design common interview questions

## Index
### Databases
- https://www.youtube.com/watch?v=6GebEqt6Ynk&ab_channel=Jordanhasnolife
- Write heavy: Cassandra (masterless replication schema)
- Read heavy: bigTable
- Storing videos: Object storage like Amazon S3, GCS
- Analytics required: HDFS
- Relational: MySQL, Oracle, SQL Server, PostgreSQL, SQLite (smaller apps)
- NoSQL: MongoDb

### CDNs
- Cloudinary
- AWS Cloudfront
- Akamai
- OpenConnect (Netflix)

### Protocols
- TCP
- UDP
- HTTP: sync comm
- HLS: HTTP live streaming
- Peer to peer comm: Web sockets (WSS):
  - when server wants to push data to client (runs over single TCP conn)
  - async
- HTTP long polling: Client continuously polling the server to fetch info
- WebRTC: uses UDP, once authenticated the peers can communicate amongst themselves without involving the server

### Concepts:
- CAP theorem: P=Partition Tolerance
- Master-slave architecture, Master Master architecture (like P2P)
- Slave slave arch does not exist
- Gossip protocol: Inter-server communication protocol

<br>

---
---

## Design Youtube
- https://www.youtube.com/watch?v=l3AOubKFB1U&ab_channel=KeertiPurswani (Lots of new info)
- Upload functionality:
  - Add a queue to uploader service since uploads take time
  - Video split into chunks while uploading
  - Each chunk can be processed via separate workers.
  - Each chunk has to be converted to diff formats (for diff platforms like macbook, iPhone, android). Also diff resolutions
- Storing videos: Object storage like Amazon S3, GCS, HDFS (if data analytics required)
- Likes, comments etc in a write heavy db like Cassandra (since scalable)
- User info in a relational DB
- Live streaming/Calls protocols:
  - YT uses RTMP (TCP underlying): for more reliable transmission, better quality
  - WebRTC (UDP): for low latency, typically in calls (can't use CDNs)
  - Cheaper to distribute videos via TCP using CDNs
  - Buffering/fetching packets of data: Getting chunks, not entire data at once. Plus better use of bandwidth
  - DASH or HLS (HTTP live streaming): protocol built by Apple -> ABR (adaptive bit rate: switching to lower resolution in case of bad internet)
  - While fetching the video on a website: (See in network tab) 2 files : .ts files(actual chunk of video), .m3us file(manifest file): has a list of chunks like an index
  - Each chunk is fetched on a need basis. which chunk we currently need? Get from m3u8 file.
- Distribution of videos: Use CDNs
- Thumbnail: very read heavy -> so store in bigTable (Google's read heavy noSql db)

<br>

---
---

## Design Uber
- https://www.youtube.com/watch?v=ZotHUoS-RCE&ab_channel=SCALER
- Some features require strong availability e.g. app should be available at all times
- Some require strong consistency e.g. if one driver has accepted the request, that data should be immediately consistent
- Web socket connections between each driver/rider and server, continously sending location info + request for asking for rides/accepting/rejecting rides
- For finding drivers in an area,
  - Each driver's/rider's location data needs to be stored somewhere. Also needs to be searched
  - It can be expensive and non-efficient to search via lat longitude
  - So can use GeoHash -> maps the entire globe's coords to a 1D structure
  - Each box on the globe is divided into smaller boxes. Say outer box is qr42 then inner box is qr42a, qr42b...
  - <img width="162" alt="Screenshot 2025-06-08 at 11 07 50 PM" src="https://github.com/user-attachments/assets/ffd60b42-b8f2-4e6e-88eb-e313c3eece49" />
  - Searching for a loc in the geohashes can be done using a binary search on the db containing all geohashes
  - Can also use QuadTrees, Postgres GIS
  - Uber uses: Google S2 library: divides the globe into chunks
- Each user's current loc can be stored in a redis cache, and historical can be stored in Cassandra
- A cron can periodically dump curr loc data into the historical db
- Use Google Maps APIs to find distance and route between driver and user
- Use consistent hashing to find eligible drivers, each node in the hash ring handles diff sets of chunks of locations. Servers communicate via gossip protocol
- ![image](https://github.com/user-attachments/assets/f7f8f9d8-9e6e-41d2-b108-a1ffbb2e4882)

- More about geohashes: Doordash (Swiggy) design - https://www.youtube.com/watch?v=iRhSAR3ldTw&ab_channel=GauravSen
  
<br>

---
---

## Design Rate-Limiter
It has 5 Algorithms:
1. Token Bucket Algorithm
2. Leaking Bucket Algorithm - uses queue for constant rates of serving requests, so not good for bursts of traffic
3. Fixed Window Counter Algorithm - edges of windows are vulnerable to 2x requests
4. Sliding Window Logs Algorithm 
5. Sliding Window Counter Algorithm
<img width="1238" alt="Screenshot 2025-06-16 at 10 52 46 PM" src="https://github.com/user-attachments/assets/0daced6f-897e-400d-ba5f-6a717829fc72" />
Redis is mostly single-threaded and handles concourrency well. Single-leader replication preferred for fast writes, keep everything in memory (Redis)

<br>

---
---

## Design Idempotent API
- https://www.youtube.com/watch?v=mI73eTlSqeU&list=PL6W8uoQQ2c63W58rpNFDwdrBnq5G3EfT7&index=15&ab_channel=Concept%26%26Coding-byShrayansh
- GET, DELETE, PUT APIs are idempotent in nature
- But POST is not, it has to be made idempotent with the help of Idempotency key (a UUID)
- This is because POST API usually involves creation of documents, etc in db which should only be done once
- (1) Client has to generate Idempotency key and send it in request header,
- (2) For each operation a new IK should be generated
- A db stores the IK and it's respective status (something like CREATED, CONSUMED). Consumed will be set only after the API has completed operation
- Also need to handle POST requests if 2 POST requests are made with the same resource at the same time. To handle this uses locks (Semaphore/ Mutex)
- To handle distributed systems, this IK logic can be implemented at the cache level too instead of db (since cache sync and access is fast)

<br>

---
---
