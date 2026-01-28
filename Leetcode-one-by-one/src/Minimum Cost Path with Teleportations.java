import java.util.*;

class Solution {
    static class State {
        int i, j, t;
        long d;
        State(int i, int j, int t, long d) {
            this.i = i;
            this.j = j;
            this.t = t;
            this.d = d;
        }
    }

    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int N = m * n;

        int[][] cells = new int[N][3];
        int idx = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                cells[idx++] = new int[]{grid[i][j], i, j};

        Arrays.sort(cells, Comparator.comparingInt(a -> a[0]));

        long[][][] dist = new long[m][n][k + 1];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(dist[i][j], Long.MAX_VALUE);

        PriorityQueue<State> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a.d));

        dist[0][0][0] = 0;
        pq.offer(new State(0, 0, 0, 0));

        int[] ptr = new int[k + 1];
        int[] dx = {0, 1};
        int[] dy = {1, 0};

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int i = cur.i, j = cur.j, t = cur.t;
            long d = cur.d;

            if (d != dist[i][j][t]) continue;
            if (i == m - 1 && j == n - 1) return (int) d;

            // normal moves
            for (int dir = 0; dir < 2; dir++) {
                int ni = i + dx[dir], nj = j + dy[dir];
                if (ni < m && nj < n) {
                    long nd = d + grid[ni][nj];
                    if (nd < dist[ni][nj][t]) {
                        dist[ni][nj][t] = nd;
                        pq.offer(new State(ni, nj, t, nd));
                    }
                }
            }

            // teleport
            if (t < k) {
                while (ptr[t] < N && cells[ptr[t]][0] <= grid[i][j]) {
                    int x = cells[ptr[t]][1];
                    int y = cells[ptr[t]][2];
                    if (d < dist[x][y][t + 1]) {
                        dist[x][y][t + 1] = d;
                        pq.offer(new State(x, y, t + 1, d));
                    }
                    ptr[t]++;
                }
            }
        }

        return -1;
    }
}
