import java.util.*;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        long[] val = new long[n];
        int[] prev = new int[n];
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        int inv = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) inv++;
        }

        TreeSet<long[]> heap = new TreeSet<>(
            (a, b) -> a[0] != b[0]
                ? Long.compare(a[0], b[0])
                : Long.compare(a[1], b[1])
        );

        for (int i = 0; i < n - 1; i++) {
            heap.add(new long[]{val[i] + val[i + 1], i});
        }

        int ops = 0;

        while (inv > 0) {
            ops++;

            long[] cur = heap.pollFirst();
            int i = (int) cur[1];
            int j = next[i];


            if (j >= n) continue;

            long merged = val[i] + val[j];


            int pi = prev[i];
            int nj = next[j];

            if (pi >= 0) {
                heap.remove(new long[]{val[pi] + val[i], pi});
                if (val[pi] > val[i]) inv--;
            }
            if (nj < n) {
                heap.remove(new long[]{val[j] + val[nj], j});
                if (val[j] > val[nj]) inv--;
            }
            if (val[i] > val[j]) inv--;


            val[i] = merged;
            next[i] = nj;
            if (nj < n) prev[nj] = i;

            if (pi >= 0) {
                heap.add(new long[]{val[pi] + val[i], pi});
                if (val[pi] > val[i]) inv++;
            }
            if (nj < n) {
                heap.add(new long[]{val[i] + val[nj], i});
                if (val[i] > val[nj]) inv++;
            }
        }

        return ops;
    }
}
