import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;

        // Step 1: Sort the intervals based on the start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Step 2: Initialize a list to hold merged intervals
        List<int[]> merged = new ArrayList<>();

        // Step 3: Traverse the intervals and merge if overlapping
        for (int i = 0; i < n; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            // If merged is empty or there is no overlap with the last interval
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < start) {
                merged.add(new int[] { start, end });
            } else {
                // There is overlap, so we merge the intervals
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], end);
            }
        }

        // Step 4: Convert list to array
        return merged.toArray(new int[merged.size()][]);
    }
}
