# Other Alogs/Tricks

## Concept of P, NP, NP Hard, NP Complete
  - https://stackoverflow.com/questions/1857244/what-are-the-differences-between-np-np-complete-and-np-hard
    

## Sieve of Eratosthenes : Find all Prime Numbers
  - Used to find all prime numbers in a range
  - https://takeuforward.org/data-structure/sieve-of-eratosthenes-find-all-prime-numbers
  - Time Complexity: O(N + N*log(log(N)))
  - Space Complexity : O(N)
  - Start with the prime number which is 2. Mark all of its multiple greater than 2 as composite (ie. 4, 6, 8, 10 and so on).
  - Find the next number in the list that is not marked as composite. This is the next prime number. Repeat until all numbers up to ‘n’ have to be processed.

```java
                                
public class Main {
    // Function to find all prime
    // numbers up to 'n'
    public static List<Integer> findAllPrimes(int n) {
         // Initialize with 1 (assuming all
         // numbers are prime initially)
        List<Integer> prime = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            prime.add(1);
        }
        
        // 0 and 1 are not prime
        prime.set(0, 0);
        prime.set(1, 0);
        
        // Apply Sieve of Eratosthenes
        for (int i = 2; i <= Math.sqrt(n); ++i) {
            if (prime.get(i) == 1) {
                for (int j = i * i; j <= n; j += i) {
                    // Mark multiples of prime
                    // numbers as not prime
                    prime.set(j, 0);
                }
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        // Collect prime numbers
        for (int i = 2; i <= n; ++i) {
            if (prime.get(i) == 1) {
                ans.add(i);
            }
        }
        return ans;
    }
}
                                
```
