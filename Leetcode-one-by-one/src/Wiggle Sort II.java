// Approach no : 1
class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int median = findKthLargest(nums, (n + 1) / 2);

        // Index-rewiring trick to handle virtual indices
        int left = 0, i = 0, right = n - 1;
        while (i <= right) {
            int mappedIndex = newIndex(i, n);
            if (nums[mappedIndex] > median) {
                swap(nums, newIndex(left++, n), mappedIndex);
                i++;
            } else if (nums[mappedIndex] < median) {
                swap(nums, mappedIndex, newIndex(right--, n));
            } else {
                i++;
            }
        }
    }

    // Index mapping to rearrange positions
    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

    // Quickselect: find kth largest element
    private int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        k = nums.length - k;
        while (left <= right) {
            int pivotIdx = partition(nums, left, right);
            if (pivotIdx == k) return nums[pivotIdx];
            else if (pivotIdx < k) left = pivotIdx + 1;
            else right = pivotIdx - 1;
        }
        return -1;
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right], i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i++, j);
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}


// Approach no : 2
class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int median = findKthLargest(nums, (n + 1) / 2);

        // Virtual indexing + Dutch national flag
        int left = 0, i = 0, right = n - 1;
        while (i <= right) {
            int vi = mappedIndex(i, n);
            if (nums[vi] > median) {
                swap(nums, mappedIndex(left++, n), vi);
                i++;
            } else if (nums[vi] < median) {
                swap(nums, vi, mappedIndex(right--, n));
            } else {
                i++;
            }
        }
    }

    // (1 + 2 * i) % (n | 1) ensures:
    // First half filled with large numbers at odd indices
    // Second half filled with small numbers at even indices
    private int mappedIndex(int i, int n) {
        return (1 + 2 * i) % (n | 1);
    }

    // Find the kth largest using quickselect (median in this case)
    private int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        k = nums.length - k;
        while (left <= right) {
            int pivot = partition(nums, left, right);
            if (pivot == k) return nums[pivot];
            else if (pivot < k) left = pivot + 1;
            else right = pivot - 1;
        }
        return -1;
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int store = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, store++, i);
            }
        }
        swap(nums, store, right);
        return store;
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}



// Approach no : 3 ( Most Optimized Solution)
import java.util.*;

class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int left = (n - 1) / 2;     // end of smaller half
        int right = n - 1;          // end of larger half

        for (int i = 0; i < n; i++) {
            nums[i] = (i % 2 == 0) ? sorted[left--] : sorted[right--];
        }
    }
}
