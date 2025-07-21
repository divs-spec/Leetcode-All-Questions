import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> pairs = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return pairs;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Push function equivalent
        // Initial push: (sum, index in nums1, index in nums2)
        minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});

        while (!minHeap.isEmpty() && pairs.size() < k) {
            int[] top = minHeap.poll();
            int i = top[1], j = top[2];
            pairs.add(Arrays.asList(nums1[i], nums2[j]));

            // Push next in nums2: (i, j+1)
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }

            // Push next in nums1: (i+1, 0), only when j == 0
            if (j == 0 && i + 1 < nums1.length) {
                minHeap.offer(new int[]{nums1[i + 1] + nums2[0], i + 1, 0});
            }
        }

        return pairs;
    }
}
