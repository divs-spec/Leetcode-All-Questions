import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        List<Integer> h = new ArrayList<>();
        List<Integer> v = new ArrayList<>();

        // Add boundaries
        h.add(1);
        h.add(m);
        for (int x : hFences) h.add(x);

        v.add(1);
        v.add(n);
        for (int x : vFences) v.add(x);

        Collections.sort(h);
        Collections.sort(v);

        // All horizontal distances
        Set<Integer> hDist = new HashSet<>();
        for (int i = 0; i < h.size(); i++) {
            for (int j = i + 1; j < h.size(); j++) {
                hDist.add(h.get(j) - h.get(i));
            }
        }

        long maxSide = 0;

        // Check vertical distances
        for (int i = 0; i < v.size(); i++) {
            for (int j = i + 1; j < v.size(); j++) {
                int d = v.get(j) - v.get(i);
                if (hDist.contains(d)) {
                    maxSide = Math.max(maxSide, d);
                }
            }
        }

        if (maxSide == 0) return -1;

        return (int) ((maxSide * maxSide) % MOD);
    }
}
