# Linked Lists

### Cycle detection

#### Tortoise and Hare Algorithm
  - https://takeuforward.org/data-structure/detect-a-cycle-in-a-linked-list/
  - Move slow pointer one step at a time and fast pointer by 2 steps. If at some point they meet, cycle is found
  - Otherwise either fast==null (odd length linkedlist) || fast.next==null (even length linkedlist) is encountered.

<br>

---
---

### Intersection points of 2 linked lists

#### Algo
  - https://takeuforward.org/data-structure/find-intersection-of-two-linked-lists/
  - Difference in length approach:
      - We will reduce the search length. This can be done by searching the length of the shorter linked list. How? Let’s see the process.
      - Find the length of both lists
      - Find the positive difference between these lengths.
      - Move the dummy pointer of the larger list by the difference achieved. This makes our search length reduced to a smaller list length.
      - Move both pointers, each pointing two lists, ahead simultaneously if both do not collide.
        
  - Optimised approach:
      - Take two dummy nodes for each list. Point each to the head of the lists.
      - Iterate over them. If anyone becomes null, point them to the head of the opposite lists and continue iterating until they collide
