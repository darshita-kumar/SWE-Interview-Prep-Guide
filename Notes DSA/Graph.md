# Graphs

### Note: While performing traversals always consider that the graph might have disconnected components.
<img width="925" alt="Screenshot 2025-04-13 at 5 14 12 PM" src="https://github.com/user-attachments/assets/d043db93-5935-4640-96c8-6f078d6c6d8f" />

### BFS
   - $O(V+E)$
   - Print a node then push all unvisited neighbours into a queue (mark them visited before pushing), repeat until queue is empty

### DFS
  - $O(V+E)$
  - Maintain a visited array and a queue. Initialize with 1st node
  - Loop on all nodes and push each of it's neighbour to queue
  - Make visited[neighbour]=true while pushing to queue

<br>

---
---

### Topological Sort (Only for DAGs: Directed Acyclic Graph)
   - Concept: An ordering in which if there exists an edge from u to v then u appears before v in the ordering
   - Why only DAGs? Because in an undirected graph the above condition cannot be satistified
   - Why not in cyclic graphs? If a cycle exists then u cannot always be before v in the ordering (go in circles)
   - There can be multiple valid topological orderings for a graph 
     
#### Recursive
  - $O(V+E)$
  - Do DFS, also maintain an extra stack and pass it to the recursive method
  - Whenever the recursive call for a node ends (i.e. all the neighbours are visited), push the curr node to the extra stack
  - This extra stack basically indicates that for any element at the top of the stack, all it's neighbours have been processed
  - Pop the stack, so every element will come before it's neighbours

#### Iterative (Kahn's Algo)
  - $O(V+E)$
  - Calculate indegree of each node 
  - Start by pushing indegree=0 nodes to queue (no need for visited array)
  - For each node, traverse it's neighbours and reduce indegree of each neighbour by 1
  - If any neighbour's indegree becomes 0, push it to queue
  - When pushing to queue add this to result topoSort arrayList

<br>

---
---

### Cycle Detection 
   - Sample question: https://leetcode.com/problems/course-schedule/description/

#### Undirected Graph:
##### DFS
   - SC: O(N), TC: O(N+2E)
   - Maintain a parent variable in the recursive call
   - Mark curr node as visited in each recursive call
   - Traverse neighbours, if any neighbour is visited and not parent, its a cycle
##### BFS
   - TC: O(V+E), SC: O(V)
   - Starts traversal in 2 diff directions from the same node.
   - If you arrive at a node a 2nd time, you have a cycle
   - Maintain curr node and it's parent in the queue. Maintain visited set.
   - For each node popped from queue, traverse it's neighbours, skipping the parent. Mark each neighbour as visited and add to queue
   - If any neighbour has been visited previously, cycle=true

#### Directed graph:
##### DFS
   - Maintain 2 arrays visited and pathVisited
   - pathVisited is to check if we have visited a particular node in the same path
   - For each recursive call, mark the node as visited and visit it's neighbors. If any neighbor is visited and also pathVisited, we have a cycle
   - Mark pathVisited[currNode]=false when recursive call for that node ends and cycle is not found

```java
private boolean dfsCheck(int node, ArrayList<ArrayList<Integer>> adj, int vis[], int pathVis[]) {
        if(pathVis[node]==1) return false;
        vis[node] = 1; 
        pathVis[node] = 1; 
        
        // traverse for adjacent nodes 
        for(int it : adj.get(node)) {
            // when the node is not visited 
            if(vis[it] == 0) {
                if(dfsCheck(it, adj, vis, pathVis) == true) 
                    return true; 
            }
            // if the node has been previously visited
            // but it has to be visited on the same path 
            else if(pathVis[it] == 1) {
                return true; 
            }
        }
        
        pathVis[node] = 0; 
        return false; 
    }
```

##### BFS (Use Kahn's algo)
  - Cycle exists if length of topo sort obtained < total number of vertices


<br>

---
---

### Shortest Path Algos


#### Dijkastra's Algo

  - Single source shortest path
  - Non negative weight
  - $O(E.logV)$  ~  $O(E.logE)$ 

    ```
    Use dist[]: initialised as inf, 0 for src 
    Use boolean fin[]: true for nodes whose dist is finalised
    loop V-1 times:
        take the node with smallest dist and not finalised yet 
        make it finalised 
        update distance of all its unfinalised neighbours if they can become smaller 
    ```

  - Can use min heap to get the min dist node to improve time complexity 

#### Undirected graph with equal weight on all edges
  - Normal BFS and distance map
  - No need for finalised map or fetching minimum distance, any distance updated will be finalised
  - Maintain a queue, add all the neighbours in queue who are not present in distance map


#### Bellmanford Algo

  - Single source shortest path
  - Any kind of weight 
  - $O(E.V)$

    ```
    Use dist[]: initialised as inf, 0 for src 
    loop V-1 times:
        loop for all edges uv:
            if(d[v] > d[u] + weight_u_v ) d[v] = d[u] + weight_u_v
    ```

  - Can do one extra iteration, if weight is getting relaxed then there exist a negative weight cycle 

#### Floyd Warshall

  - $O(V^3)$
  - Shortest path btw all vertex pairs 


<br>

---
---

### Minimum spanning tree

`Only for Undirected, Connected, Weighted graph`

#### Prim's Algo

  - $O(E.logV)$
  - Similar to `Dijkastra's`

  - Idea is to add a vertex in MST set then add the minimum weight edge that connects this set to rest of the graph 

      ```
      Use key[]: initialised as inf, 0 for src 
      Use boolean mst[]: true for nodes who are in mst 
      Loop V times:
          take the minimum key which is not yet included in mst set 
          make mst[u] = true 
          add its key to the result 
          for all its unfinalised neighbours if edge_weight_uv is < key[v]: update key[v] = edge_weight_uv
      ```

  - Can use min heap to get the min key node to improve time complexity 

#### Kruskal's Algo

  - `Better`
  - $O(E.logV)$
  - Uses disjoint sets union 
  - Idea is to sort all edges, add an edge if it does not create cycle. Mst size should be v-1 


<br>

---
---

#### Kosraju's Algo of strongly connected components


<br>

---
---

#### Bipartite graph


<br>

---
---

### Other topics

- Articulation point and bridges:  O(V+E): Smart DFS 
- Kosaraju algo: For minimum number of strongly connected components: Directed graph
- Network flow (usually not asked): Ford Fulkerson
- Eulerian circuit: Reconstruct Itinerary

<br>

---
---

### Neetcode Questions

#### Normal
- [Number of Islands](https://leetcode.com/problems/number-of-islands/): Flod fill algo 
- [Max Area of Island](https://leetcode.com/problems/max-area-of-island/): Flod fill algo 
- [Clone Graph](https://leetcode.com/problems/clone-graph/): serious backtracking, wasnt intuitive, saw sol (Didnt feel like doing in second attempt)
- [Walls and Gates](https://leetcode.com/problems/walls-and-gates/): Doable
- [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/): used to approach of As Far from Land as Possible
- [Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/): do dfs from ocean to inside
- [Surrounded Regions](https://leetcode.com/problems/surrounded-regions/): do dfs from outside
- [Course Schedule](https://leetcode.com/problems/course-schedule/): Cycle detection, used dfs
- [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) ⭐️ Cycle detection, used Kahn's algo 
- [Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/) ⭐️ union find and the property of tree that edges = n-1
- [Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/): easy
- [Redundant Connection](https://leetcode.com/problems/redundant-connection/) ⭐️ disjoint set, union find algo 
- [Word Ladder](https://leetcode.com/problems/word-ladder/) ⭐️ kind hard, saw sol
  - If we can build a graph of words (where 2 words are connected if they have 1 character diff) then we can simply run bfs and find the distance.
  - We can optimise building the graph by having patterns as key and list of words as value. Neighbour of a word is all the lists of all patterns it can make

#### Advanced
- [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/) ⭐️ 
  - very interesting, saw the video 
  - Sort the edges `uv` based on `v`, so neighbours in adj_list are in sorted order
  - Cant use visited array as we can visit the nodes multiple times. So how to do?
  - Keep removing the edges(neighbours) from adj_list once you use it, and add the node in sol list
  - You can say you have used all edges once len(sol) = len(tickets) + 1
  - How to backtrack? add/remove from sol list, add/remove neighbours
  - Earlier this solution was working but now leetcode has put TLE for this

- [Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) ⭐️ Minimum spaning tree problem
- [Network Delay Time](https://leetcode.com/problems/network-delay-time/) ⭐️ Dijkastra algo, shortest path from a src
- [Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water/) ⭐️ 
  - modified Dijkastra. Thought of brute force on my but but it would have caused tle so saw approach in video 
  - use pq to get the node with min weight
  - We dont need a finalised set because weight will never be reduced, so we only use the visited set
- [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) ⭐️ 
  - Topological sort
  - Why are we comparng only adjacent words? why not all the word pairs?
- [Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) ⭐️ 
  - saw video, used bellmann ford, nice problem 
  - we need to clone dist array at each iteration because we want to limit the updates going beyond k
  - `dist[v] = Math.min(dist[v], distClone[u]+w)`


