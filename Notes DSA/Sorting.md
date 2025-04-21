# Sorting techniques

### Quick Sort

#### Points to remember 
  - TC: O(nlogn), SC: O(1)
  - Choose a pivot. Find it's correct position in the array
  - Place all elements<=pivot to it's left and all elements>pivot to its right.
  - Place pivot at correct position.
  - Recursively do the above now for the left half of the array (left of pivot) and right half (right of pivot)
  - Ref:https://takeuforward.org/data-structure/quick-sort-algorithm/
  - https://youtu.be/WIrA4YexLRQ?si=wTtOgAPBNlkwwAMV

  - For finding pivot position while also sorting array:
      - Choose arr[low]=pivot
      - Keep 2 pointers i=start and j=end
      - For i: Keep doing i++ until you find 1st ele greater than pivot
      - For j: Keep doing j-- until you find 1st ele lesser than pivot
      - If i<j, swap arr[i] and arr[j] (j is still not beyond the correct pos of pivot)
      - Keep looping till j crosses i
      - Swap pivot and j (j holds correct pos of pivot)
      - Return j

  ```java
  public class Solution {
      public static List<Integer> quickSort(List<Integer> arr){
          int n = arr.size();
          sort(0, n-1, arr);
          return arr;
      }
  
      public static void sort(int start, int end, List<Integer> arr) {
          if (start < end) {
              int pIndex = partitionArray(start, end, arr);
              sort(start, pIndex - 1, arr);
              sort(pIndex + 1, end, arr);
          }
      }
  
      public static int partitionArray(int start, int end, List<Integer> arr) {
          int pivot = arr.get(start);
          int i=start, j=end;
          while(i < j) {
              while(i<=end && arr.get(i)<=pivot)
                  i++;
              while(j>=start+1 && arr.get(j)>pivot)
                  j--;
              if(i < j) {
                  //swap arr[i] and arr[j]
                  swap(i,j,arr);
              }
          }
          // swap arr[j] and pivot
          swap(start, j, arr);
          return j;
      }
  
      public static void swap(int i, int j, List<Integer> arr) {
          int temp = arr.get(i);
          arr.set(i,arr.get(j));
          arr.set(j,temp);
      }
  }
  ```

<br>

---
---

### Merge Sort

#### Points to remember 
  - TC: O(nlogn), SC: O(n), Auxiliary stack space: O(n)
  - https://takeuforward.org/data-structure/merge-sort-algorithm/

  ```java
  import java.util.*;
  
  class Solution {
      private static void merge(int[] arr, int low, int mid, int high) {
          ArrayList<Integer> temp = new ArrayList<>(); // temporary array
          int left = low;      // starting index of left half of arr
          int right = mid + 1;   // starting index of right half of arr
  
          //storing elements in the temporary array in a sorted manner
          while (left <= mid && right <= high) {
              if (arr[left] <= arr[right]) {
                  temp.add(arr[left]);
                  left++;
              } else {
                  temp.add(arr[right]);
                  right++;
              }
          }
  
          // if elements on the left half are still left 
          while (left <= mid) {
              temp.add(arr[left]);
              left++;
          }
  
          //  if elements on the right half are still left //
          while (right <= high) {
              temp.add(arr[right]);
              right++;
          }
  
          // transfering all elements from temporary to arr //
          for (int i = low; i <= high; i++) {
              arr[i] = temp.get(i - low);
          }
      }
  
      public static void mergeSort(int[] arr, int low, int high) {
          if (low >= high) return;
          int mid = (low + high) / 2 ;
          mergeSort(arr, low, mid);  // left half
          mergeSort(arr, mid + 1, high); // right half
          merge(arr, low, mid, high);  // merging sorted halves
      }
  }
  ```
