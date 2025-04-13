# Arrays

### Two pointer

#### Points to remember 
  - Place the 2 pointers to the left and right of the array
  - Let them move towards each other based on some condition
  - Can also be placed at the same position at the start and move away from each other

#### Sample questions
  - Trapping rain water (https://leetcode.com/problems/trapping-rain-water/description)
  - 3 Sum
  - Container with most water (https://leetcode.com/problems/container-with-most-water)

<br>

---
---

### Sliding window

#### Points to remember 
  - The size of the window can stay constant or shrink/increase based on requirements
  - It is a concept which allows us to minimize recomputations depending on the problem statement 

#### Sample questions
  - Longest substring without repeating characters (https://leetcode.com/problems/longest-substring-without-repeating-characters)
  - Max consecutive ones (https://leetcode.com/problems/max-consecutive-ones-iii) 
  - Contains duplicate (https://leetcode.com/problems/contains-duplicate-ii/description)
  - Minimum number of swaps to group all 1's together: (https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/description/) [Trick:

<br>

---
---

### Kadane's algo (https://www.youtube.com/watch?v=AHZpyENo7k4&feature=youtu.be)

#### Points to remember:
  - A dynamic programming technique used to find the maximum sum of a contiguous subarray within a given array of numbers

```java
public static long maxSubarraySum(int[] arr, int n) {
        long maxi = Long.MIN_VALUE; // maximum sum
        long sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum > maxi) {
                maxi = sum;
            }
            // If sum < 0: discard the sum calculated
            if (sum < 0) {
                sum = 0;
            }
        }

        // To consider the sum of the empty subarray
        // uncomment the following check:

        //if (maxi < 0) maxi = 0;

        return maxi;
    }
```

<br>

---
---

### Moore's voting algorithm

#### Points to remember:
  - Problem Statement: Given an array of N integers, write a program to return an element that occurs more than N/2 times in the given array.
  - The algorithm states that if the array contains a majority element, its occurrence must be greater than the floor(N/2).
  - https://takeuforward.org/data-structure/find-the-majority-element-that-occurs-more-than-n-2-times/
  - We try to keep track of the occurrences of the majority element and minority elements dynamically
  - Initialize 2 variables:
      - Count –  for tracking the count of element
      - Element – for which element we are counting
  - Traverse through the given array.
      - If Count is 0 then store the current element of the array as Element.
      - If the current element and Element are the same increase the Count by 1.
      - If they are different decrease the Count by 1.
      - The integer present in Element should be the result we are expecting 

<br>

---
---

### Interval problems

#### Observations
  - Usually interval problems can be solved by either sorting the intervals based on start value or end value of the interval

#### Sample questions
  - Merge overlapping subintervals (https://leetcode.com/problems/merge-intervals/description/)
  - Insert interval (https://leetcode.com/problems/insert-interval/description/) [Good question, see Binary Search approach in editorial]
