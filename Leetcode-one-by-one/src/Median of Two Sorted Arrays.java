//Approach no 1
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        int left = 0, right = m;

        while (left <= right) {
            int i = (left + right) / 2;     // partition in nums1
            int j = totalLeft - i;          // partition in nums2

            int left1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int left2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (left1 <= right2 && left2 <= right1) {
                // Found correct partition
                if ((m + n) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    return Math.max(left1, left2);
                }
            } else if (left1 > right2) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}



//Approach no 2
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        // Merge both sorted arrays
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // Copy remaining elements (if any)
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];

        int total = m + n;
        // If even length
        if (total % 2 == 0) {
            return (merged[total / 2 - 1] + merged[total / 2]) / 2.0;
        } 
        // If odd length
        else {
            return merged[total / 2];
        }
    }
}




//Approach no 3
import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Left half
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Right half

        // Combine both arrays into one stream of numbers
        for (int num : nums1) addNumber(num, maxHeap, minHeap);
        for (int num : nums2) addNumber(num, maxHeap, minHeap);

        // Balance and find median
        return getMedian(maxHeap, minHeap);
    }

    private void addNumber(int num, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        // Step 1: Add to maxHeap first
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // Step 2: Balance the sizes (maxHeap may have at most one extra element)
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private double getMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}




//Approach no 4
import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Add all elements from both arrays
        for (int num : nums1) pq.add(num);
        for (int num : nums2) pq.add(num);

        int totalSize = pq.size();
        int midIndex1 = (totalSize - 1) / 2; // left middle (for even case)
        int midIndex2 = totalSize / 2;       // right middle

        // Extract elements until we reach the middle
        int count = 0;
        double median = 0.0;
        while (!pq.isEmpty()) {
            int current = pq.poll();
            if (count == midIndex1) median += current;
            if (count == midIndex2) {
                median += current;
                break; // we found both median elements
            }
            count++;
        }

        // For odd, both midIndex1 and midIndex2 are same
        median /= 2.0;
        return median;
    }
}





//Approach no 5
import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Add all elements from both arrays
        for (int num : nums1) pq.offer(num);
        for (int num : nums2) pq.offer(num);

        int n = pq.size();
        int mid1 = (n - 1) / 2;
        int mid2 = n / 2;

        // Convert heap to sorted array in one go
        Integer[] sorted = pq.toArray(new Integer[0]);
        Arrays.sort(sorted);

        // Compute median directly
        if (n % 2 == 1) {
            return sorted[mid2];
        } else {
            return (sorted[mid1] + sorted[mid2]) / 2.0;
        }
    }
}





//Approach no 6
import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Min-heap to store all elements in sorted order
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Add all elements from both arrays
        for (int num : nums1) pq.offer(num);
        for (int num : nums2) pq.offer(num);

        int total = pq.size();
        int mid1 = (total - 1) / 2;
        int mid2 = total / 2;

        // Instead of polling all, we only go till we reach the median
        int index = 0;
        double median = 0.0;

        while (!pq.isEmpty()) {
            int val = pq.poll();

            if (index == mid1) median += val;
            if (index == mid2) {
                median += val;
                break; // stop early — no need to remove remaining elements
            }
            index++;
        }

        return median / 2.0;  // works for both even & odd
    }
}
