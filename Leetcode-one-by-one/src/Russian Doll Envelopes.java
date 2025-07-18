import java.util.*;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // Step 1: Sort envelopes
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // Descending height if same width
            } else {
                return a[0] - b[0]; // Ascending width
            }
        });

        // Step 2: Extract LIS from heights
        List<Integer> lis = new ArrayList<>();
        for (int[] envelope : envelopes) {
            int height = envelope[1];
            int idx = Collections.binarySearch(lis, height);
            if (idx < 0) idx = -idx - 1;
            if (idx == lis.size()) {
                lis.add(height);
            } else {
                lis.set(idx, height);
            }
        }

        return lis.size();
    }
}
