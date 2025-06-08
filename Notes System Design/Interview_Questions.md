# System Design common interview questions

## Index
### Databases
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
