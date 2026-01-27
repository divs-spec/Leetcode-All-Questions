
import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        List<List<int[]>> revGraph = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }
        
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph.get(u).add(new int[]{v, w});
            revGraph.get(v).add(new int[]{u, w}); // incoming edge
        }

        long[][] dist = new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        dist[0][0] = 0;
        pq.offer(new long[]{0, 0, 0}); // {cost, node, usedHere}

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long cost = cur[0];
            int u = (int) cur[1];
            int usedHere = (int) cur[2];

            if (cost > dist[u][usedHere]) continue;
            if (u == n - 1) return (int) cost;

            // Normal outgoing edges
            for (int[] edge : graph.get(u)) {
                int v = edge[0], w = edge[1];
                long newCost = cost + w;
                if (newCost < dist[v][0]) {
                    dist[v][0] = newCost;
                    pq.offer(new long[]{newCost, v, 0});
                }
            }

            // Use switch at this node (if not used yet)
            if (usedHere == 0) {
                for (int[] edge : revGraph.get(u)) {
                    int v = edge[0], w = edge[1];
                    long newCost = cost + 2L * w;
                    if (newCost < dist[v][0]) {
                        dist[v][0] = newCost;
                        pq.offer(new long[]{newCost, v, 0});
                    }
                }
            }
        }

        return -1;
    }
}
