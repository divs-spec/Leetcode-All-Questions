import java.util.HashMap;
import java.util.Map;

class Solution {

    public long maxArea(int[][] pts) {
        if (pts.length < 3) return -1;

        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        // maps: key → {min, max}
        Map<Integer, int[]> x2y = new HashMap<>();
        Map<Integer, int[]> y2x = new HashMap<>();

        // one pass – gather global & per‑column/row extremes
        for (int[] p : pts) {
            int x = p[0], y = p[1];

            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);

            x2y.compute(x, (k, v) -> update(v, y));
            y2x.compute(y, (k, v) -> update(v, x));
        }

        long best2Area = -1;                  // double area (avoids division)

        // 1) triangles with a vertical base
        for (Map.Entry<Integer, int[]> e : x2y.entrySet()) {
            int x0 = e.getKey();
            long base = e.getValue()[1] - e.getValue()[0];   // max Δy in this column
            if (base == 0) continue;                         // need two distinct points

            long horz = Math.max(x0 - (long) minX, (long) maxX - x0);
            if (horz == 0) continue;                         // no third point on the side

            best2Area = Math.max(best2Area, base * horz);
        }

        // 2) triangles with a horizontal base
        for (Map.Entry<Integer, int[]> e : y2x.entrySet()) {
            int y0 = e.getKey();
            long base = e.getValue()[1] - e.getValue()[0];   // max Δx in this row
            if (base == 0) continue;

            long vert = Math.max(y0 - (long) minY, (long) maxY - y0);
            if (vert == 0) continue;

            best2Area = Math.max(best2Area, base * vert);
        }

        return best2Area;    // already “double area” as required by original code
    }

    /** Helper: create or update an int[]{min,max} pair. */
    private int[] update(int[] arr, int v) {
        if (arr == null) return new int[]{v, v};
        if (v < arr[0]) arr[0] = v;
        else if (v > arr[1]) arr[1] = v;
        return arr;
    }
}
