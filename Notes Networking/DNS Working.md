## HOW DNS WORKS

https://www.youtube.com/watch?v=QVdX34quUgU&list=PL6W8uoQQ2c63W58rpNFDwdrBnq5G3EfT7&index=31&ab_channel=Concept%26%26Coding-byShrayansh

### Basics
- IP Address: Numerical address assigned to any device on the internet
- Domain name: Human readable address
- Name to IP translation: DNS

<img width="450" alt="Screenshot 2025-06-21 at 3 57 16 PM" src="https://github.com/user-attachments/assets/d367f5a0-1eab-4fd9-b07d-736ec7c36456" />

### How DNS works:
Cached locally:

<img width="651" alt="Screenshot 2025-06-21 at 4 00 23 PM" src="https://github.com/user-attachments/assets/7d85b407-c60a-4f3a-957d-5ade393b71e6" />
Each locally cached record:

<img width="896" alt="Screenshot 2025-06-21 at 4 01 38 PM" src="https://github.com/user-attachments/assets/b6cb5690-1327-410e-aef6-d24d1241414b" />

CNAME works only on sub-domain level.

### Recursive method of IP resolution:
- DNS resolver takes the responsibility of resolving IP.
- If IP is not found in local cache, we query DNS resolver (The IP of this is configuerd in our systems or 8.8.8.8 is the one provided by Google)
- If IP not found in DNS resolver cache, querying starts to resolve IP:
<img width="1062" alt="Screenshot 2025-06-21 at 4 19 22 PM" src="https://github.com/user-attachments/assets/ea6aca0e-a224-4675-ada4-0d89893e703e" />

### Iterative method of IP resolution:
- DNS client takes the responsibility of resolving IP.
- <img width="1209" alt="Screenshot 2025-06-21 at 4 26 56 PM" src="https://github.com/user-attachments/assets/bb023831-4733-4692-9f7d-8ad95d4ff507" />
