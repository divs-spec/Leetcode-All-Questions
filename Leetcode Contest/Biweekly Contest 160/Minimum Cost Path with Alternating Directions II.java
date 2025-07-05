import java.util.*;

class Solution {
    static class State implements Comparable<State> {
        int x, y, timeParity;
        long cost;

        State(int x, int y, int timeParity, long cost) {
            this.x = x;
            this.y = y;
            this.timeParity = timeParity;
            this.cost = cost;
        }

        public int compareTo(State other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    public long minCost(int m, int n, int[][] waitCost) {
        long[][][] dist = new long[m][n][2];  // [i][j][timeParity]
        for (long[][] row : dist)
            for (long[] d : row)
                Arrays.fill(d, Long.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>();

        // Start at (0,0), time = 1 -> parity = 1
        long startCost = 1;  // (0+1)*(0+1)
        dist[0][0][1] = startCost;
        pq.offer(new State(0, 0, 1, startCost));

        int[] dx = {1, 0}; // down, right
        int[] dy = {0, 1};

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            if (curr.x == m - 1 && curr.y == n - 1) {
                return curr.cost;
            }

            // If already visited with lower cost, skip
            if (curr.cost > dist[curr.x][curr.y][curr.timeParity]) continue;

            if (curr.timeParity == 1) { // Move time
                for (int d = 0; d < 2; d++) {
                    int nx = curr.x + dx[d];
                    int ny = curr.y + dy[d];
                    if (nx < m && ny < n) {
                        long entryCost = (long)(nx + 1) * (ny + 1);
                        long newCost = curr.cost + entryCost;
                        if (newCost < dist[nx][ny][0]) {
                            dist[nx][ny][0] = newCost;
                            pq.offer(new State(nx, ny, 0, newCost));
                        }
                    }
                }
            } else { // Wait time
                long wait = waitCost[curr.x][curr.y];
                long newCost = curr.cost + wait;
                if (newCost < dist[curr.x][curr.y][1]) {
                    dist[curr.x][curr.y][1] = newCost;
                    pq.offer(new State(curr.x, curr.y, 1, newCost));
                }
            }
        }

        return -1; // unreachable
    }
}
©leetcode
