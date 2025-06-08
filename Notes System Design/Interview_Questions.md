# System Design common interview questions

## Index
### Databases
- Write heavy: Cassandra
- Read heavy: bigTable
- Storing videos: Object storage like Amazon S3, GCS
- Analytics required: HDFS
- Relational: MySQL, Oracle, SQL Server, PostgreSQL, SQLite (smaller apps)
- NoSQL: MongoDb

### CDNs
- Cloudinary

### Protocols
- TCP
- UDP
- HTTP
- HLS: HTTP live streaming

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
