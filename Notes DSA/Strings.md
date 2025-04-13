# Strings

### Pattern Matching 

#### Brute force
  - $O(m*n)$
  - Use indexOf() method. e.g To serach for s1='abc' in s2='fyjgihgkabckhgi' you can write s2.indexOf(s1)
  - Returns first index of s1 in s2. Can also use lastIndexOf() method to get last Index of occurence 
  - Note: indexOf does not use the below algorithms (https://stackoverflow.com/questions/19543547/why-does-string-indexof-not-use-kmp)

#### Rabin Karp
  - $O(m+n)$
  - Where m = size of pattern, n = size of string
  - Uses a rolling Hash function to efficiently search for pattern occurences in String
  - Use a large prime number like 100007
  - https://leetcode.com/problems/repeated-string-match/solutions/416144/Rabin-Karp-algorithm-C++-implementation/

```java
class Solution {
    PRIME = 100007
    public int calculateHash(String str) {
        int hash = 0;
        for(int i=0; i<str.length(); i++) {
            hash += str.charAt(i)*Math.pow(PRIME,i);
        }
        return hash;
    }

    public int recalculateHash(String str, int prevIndex, int currIndex, int patternLen, int prevHash) {
        // currIndex = index being added
        // prevIndex = Index being removed
        int hash = prevHash;
        hash -= str.charAt(prevIndex);
        hash = hash/PRIME;
        hash += str.charAt(currIndex)*Math.pow(PRIME,patternLen-1);
        return hash;
    }
}
```

#### Z algo (https://youtu.be/CpZh4eF8QBw?si=1TMo9zur6siHDiw1)
  - $O(m+n)$
  - Compute Z array
  - Z(k) = longest substring starting at k which is also the prefix of the string
  - https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/submissions/1562112252/

```java
// Question - find first occurence of needle in haystack
class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack.length()<needle.length())
            return -1;
        int[] z = calculateZArray(needle+"#"+haystack);
        int prefixLen = needle.length();
        for(int i=prefixLen+1; i<z.length; i++) {
            if(z[i] == prefixLen)
                return i-prefixLen-1;
        }
        return -1;
    }

    public int[] calculateZArray(String str) {
        int n = str.length();
        int[] z = new int[n];
        int left = 0;
        int right = 0;
        for(int k=1; k<n; k++) {
            if(k > right) {
                // Outside z box
                right = k;
                left = k;
                // Try to form z box now
                while(right < n && str.charAt(right)==str.charAt(right-left))
                    right++;
                z[k] = right-left;
                // Set right bound of box
                right--;
            } else {
                // Operating inside z box
                if(z[k-left] < right-k+1) {
                    // Can copy value from z box
                    z[k] = z[k-left];
                } else {
                    // Recompute z box
                    left = k;
                    while(right < n && str.charAt(right)==str.charAt(right-left))
                        right++;
                    z[k] = right-left;
                    right--;
                }
            }
        }
        return z;
    }
}

```

#### KMP Algo
  - $O(m+n)$
  - Computes a LPS/Pi array
  - https://takeuforward.org/data-structure/kmp-algorithm/


<br>

---
---

### Tips & Notes

- Using array of 26 characters instead of a hashmap is more time efficient
- Use StringBuilder in case a string has to be altered frequently
- StringBuilder useful methods:
  - reverse()
  - append()
  - All string methods are applicable to StringBuilder too
- str.substring(start, end): start inedx is inclusive but end index is not. So substring(4,10) means 10th character will not be included in the result
