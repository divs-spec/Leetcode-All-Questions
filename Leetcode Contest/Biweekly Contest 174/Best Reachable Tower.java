class Solution {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int bestQuality = -1;
        int bestX = -1, bestY = -1;

        int cx = center[0];
        int cy = center[1];

        for (int[] tower : towers) {
            int x = tower[0];
            int y = tower[1];
            int q = tower[2];

            int dist = Math.abs(x - cx) + Math.abs(y - cy);

            if (dist <= radius) {
                if (q > bestQuality ||
                   (q == bestQuality && (x < bestX || (x == bestX && y < bestY)))) {
                    bestQuality = q;
                    bestX = x;
                    bestY = y;
                }
            }
        }

        if (bestQuality == -1) {
            return new int[]{-1, -1};
        }

        return new int[]{bestX, bestY};
    }
}
©leetcode
