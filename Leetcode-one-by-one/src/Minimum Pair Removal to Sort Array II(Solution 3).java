import java.util.*;

class Solution {

    static class Pair {
        long sum;
        int i;
        Pair(long s, int i) {
            this.sum = s;
            this.i = i;
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = nums[i];

        int[] L = new int[n];
        int[] R = new int[n];
        boolean[] alive = new boolean[n];

        for (int i = 0; i < n; i++) {
            L[i] = i - 1;
            R[i] = i + 1;
            alive[i] = true;
        }

        // count initial inversions
        int inv = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (a[i] > a[i + 1]) inv++;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (x, y) -> x.sum == y.sum ? x.i - y.i : Long.compare(x.sum, y.sum)
        );

        for (int i = 0; i + 1 < n; i++) {
            pq.add(new Pair(a[i] + a[i + 1], i));
        }

        int ops = 0;

        while (inv > 0) {

            Pair p;
            while (true) {
                p = pq.poll();
                int i = p.i;
                int j = R[i];
                if (j < n && alive[i] && alive[j] && a[i] + a[j] == p.sum)
                    break;
            }

            int i = p.i;
            int j = R[i];

            // remove old inversions
            if (L[i] >= 0 && alive[L[i]] && a[L[i]] > a[i]) inv--;
            if (a[i] > a[j]) inv--;
            if (R[j] < n && alive[R[j]] && a[j] > a[R[j]]) inv--;

            // merge
            a[i] += a[j];
            alive[j] = false;
            ops++;

            int rj = R[j];
            R[i] = rj;
            if (rj < n) L[rj] = i;

            // add new inversions
            if (L[i] >= 0 && alive[L[i]] && a[L[i]] > a[i]) inv++;
            if (R[i] < n && alive[R[i]] && a[i] > a[R[i]]) inv++;

            // push new pairs
            if (L[i] >= 0)
                pq.add(new Pair(a[L[i]] + a[i], L[i]));
            if (R[i] < n)
                pq.add(new Pair(a[i] + a[R[i]], i));
        }

        return ops;
    }
}
