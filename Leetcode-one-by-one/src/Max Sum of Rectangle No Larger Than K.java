// Approach no 2: NO TLE
class Solution {

private int maxSumSubarray(int[] A) {
    int min = 0, res = Integer.MIN_VALUE;
    for(int sum: A) {
        res = Math.max(res, sum - min);
        min = Math.min(min, sum);
    }
    return res;
}
private int maxSumSubarray(int[] A, int k) {
    int res = maxSumSubarray(A);
    if(res <= k) return res;
    res = Integer.MIN_VALUE;
    TreeSet<Integer> set = new TreeSet<>();
    set.add(0);
    for(int sum: A) {
        Integer old = set.ceiling(sum - k);
        if(old != null) {
            if(sum - old > res) {
                res = sum - old;
                if(res == k) return res;
            }
        }
        set.add(sum);
    }
    return res;
}
public int maxSumSubmatrix(int[][] matrix, int k) {
    int m = matrix.length, n = m == 0 ? 0 : matrix[0].length, res = Integer.MIN_VALUE;
    boolean groupingRows = true;
    if(m > n) {
        int temp = m; m = n; n = temp;
        groupingRows = false;
    }
    int[] sum = new int[n];
    for(int i = 0; i < m; i++) {
        Arrays.fill(sum, 0);
        for(int j = i; j < m; j++) {
            int pre = 0;
            if(groupingRows) {
                for(int t = 0; t < n; t++) sum[t] += pre += matrix[j][t];
            } else {
                for(int t = 0; t < n; t++) sum[t] += pre += matrix[t][j];
            }
            res = Math.max(res, maxSumSubarray(sum, k));
            if(res == k) return res;
        }
    }
    return res;
}
}


// Approach no 1: TLE 

import java.util.*;

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int max = Integer.MIN_VALUE;

        for (int top = 0; top < m; top++) {
            int[] colSums = new int[n];
            for (int bottom = top; bottom < m; bottom++) {
                // Build the 1D column sum array
                for (int j = 0; j < n; j++) {
                    colSums[j] += matrix[bottom][j];
                }

                // Find the max subarray sum ≤ k in colSums
                max = Math.max(max, maxSubarrayNoLargerThanK(colSums, k));
                if (max == k) return k; // Early exit
            }
        }

        return max;
    }

    // Helper to find max subarray sum ≤ k in 1D array
    private int maxSubarrayNoLargerThanK(int[] nums, int k) {
        int sum = 0, max = Integer.MIN_VALUE;
        TreeSet<Integer> prefixSums = new TreeSet<>();
        prefixSums.add(0); // Base case for whole subarray

        for (int num : nums) {
            sum += num;
            // Find the smallest prefix sum >= sum - k
            Integer target = prefixSums.ceiling(sum - k);
            if (target != null) {
                max = Math.max(max, sum - target);
            }
            prefixSums.add(sum);
        }

        return max;
    }
}

/*
To solve "363. Max Sum of Rectangle No Larger Than K", you can extend the Kadane's algorithm for 2D submatrices, but with a twist: since the sum must be ≤ k, we need a data structure that lets us query prefix sums efficiently for "maximum subarray sum ≤ k".

✅ Key Idea:
Fix two row boundaries (top and bottom) and compress the 2D matrix into a 1D array of column sums.

Then, for this 1D array, find the maximum subarray sum no larger than k.

Use a TreeSet (or TreeMap) to maintain prefix sums and perform efficient binary search to find subarrays that satisfy the constraint.

✅ Time & Space Complexity:
Time:

O(m² * n * log n)

m² from row pairs, n to build column sums, log n for TreeSet operations.

Space:

O(n) for column sums and TreeSet.
*/
