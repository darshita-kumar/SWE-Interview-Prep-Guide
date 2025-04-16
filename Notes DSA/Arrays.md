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
  - [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/description/) Same as finding max non overlapping intervals. Sort by end time and pick greedily
  - [Minimum Interval to Include Each Query](https://leetcode.com/problems/minimum-interval-to-include-each-query/description/) : Hard, Heap is used, saw sol 
  - [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/description/) sort intervals and have a min heap to get available room


<br>

----
---

### Difference array
  - Useful article: https://medium.com/@ishankkatiyar162/understanding-difference-array-the-underrated-constant-time-range-update-algorithm-part-1-e432ada7f1f5
  - Constant time range update query, useful for lazy computation
  - Useful in cases like update all values in array index range (3, 10) by val=v
  - Only efficient when there are only update queries and no fetch query (lazy computation)
  - Sample snippet:

  ```java
  // Example we are given k queries in an int[] array queries
          int[] differenceArray = new int[n];
          // Process query
          for (int queryIndex = 0; queryIndex < k; queryIndex++) {
              int left = queries[queryIndex][0], right =
                  queries[queryIndex][1], val = queries[queryIndex][2];
  
              // Process start and end of range
              differenceArray[left] += val;
              differenceArray[right + 1] -= val;
          }

        // calculating prefix sum of the array differenceArray
        for (int i = 1; i < n; i++) {
      		differenceArray[i] += differenceArray[i - 1];
      	}
      
      	// adding all updates back to original array a
      	for (int i = 0; i < n; i++) {
      		a[i] += differenceArray[i];
      	}
  ```

<br>

----
---

## Aman's notes:

### [Encode and Decode Strings](https://leetcode.com/problems/encode-and-decode-strings/description/) ⭐️
> You are given a list of strings. You need to encode it into a single string and then you need to decode it back into the list of strings. Strings can contain any character.

- Saw solution
- `["aman","garg"] -> "4aman4garg"` what if size of a string is more than 1 digit?
- `["aman","amangargamangarg","garg"] -> "4#aman12#amangargamangarg4#garg"`

### [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/description/) ⭐️
> Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence. 

- Saw solution
- Store all elements in a set
- now traverse the list and see if you have all the next elements required to make the consecutive list. (make sure pre element does not belong in set so you only check when element is at the beginning)

## 2Pointer

- Last finished 3 Feb 2025

### [Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/) ⭐️
> Given sorted array and target. Find `(i,j)` such that `num[i] + num[j] = target`
- 2 pointers, move left if less than target, move right if more than target

### [3Sum](https://leetcode.com/problems/3sum/description/) ⭐️
> Given an array `nums` you need to find all unique triplets (nums[i],nums[j],nums[k]) triplets suich that all indices are different and numbers sum to `0`
- Many ways to do

### [Container With Most Water](https://leetcode.com/problems/container-with-most-water/description/) ⭐️⭐️
> Given an array of nums, each num represents a verticle line at $i^{th}$ coordinate. Find the pair of lines which can hold max amount of water together

- Area formed between the lines will always be limited by the height of the shorter line. 

- We take two pointers, i=0 & j=n-1. At every step, we find out the area formed between them, update maxarea, and move the pointer pointing to the shorter line towards the other

### [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/description/) ⭐️
> Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

- for each `i` store leftMax and rightMax column length

<br>

----
---

## Sliding Window

- Last finished 3 Feb 2025

### [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/) ⭐️
> You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

- was able to O(n) anf O(n) space sol
- Kedane's algorithm: I couldnt think of a constant space sol on my own

``` java
public int maxProfit(int[] prices) {
    int n = prices.length;
    int l = 0;
    int r = 1;
    int res = 0;
    
    while(r<n){
        if(prices[l] < prices[r]){
            res = Math.max(res, prices[r] - prices[l]);
            r++;
        }
        else {
            l = r;
            r++;
        }
    }
    return res;
}
```

### [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/) ⭐️⭐️

>Given a string s, find the length of the longest 
substring without repeating characters.

- Kinda frustrating, no major trick, saw sol, used set

### [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/description/) ⭐️⭐️⭐️

> You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times. Return the length of the longest substring containing the same letter you can get after performing the above operations.

- Kinda hard
- Saw sol
- Saw sol second time as well
- Increase the window until the length of window - maxFreq <= k, then decrease the window until the condition is satisfied again. While decreasing we must recalculate the maxFreq O(26) everytime (This is not needed but its tricky to realise)

### [Permutation in String](https://leetcode.com/problems/permutation-in-string/description/)

> Return true if one of s1's permutations is the substring of s2

- Able to think on my own
- Create a rolling hash or compare hashmaps

### [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/description/) ⭐️

> Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string ""

- Able to think on my own
- Take pointers i,j 
- Move j untill all required characters are covered, then move i to shorten the window as much as possible
- How to check if our window is valid? We can do this is constant time without checking for each each character. See sol of neetcode (Maintain a count variable to see how many unique characters we need to satify)

### [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/description/) ⭐️

> You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max value in sliding window.

- Able to think on my own immediately
- Have a deque all numbers in a way that deque is always decreasing from left to right. deque's leftmost element is always the biggest value in current window
- [Not sure] Can add redundan elements in deque so we dont have to keep track of indices

