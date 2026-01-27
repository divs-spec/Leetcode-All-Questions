import java.util.*;

class Solution {
    static class Edge {
        int to, w;
        Edge(int t, int w) {
            this.to = t;
            this.w = w;
        }
    }

    public int minCost(int n, int[][] edges) {
        List<Edge>[] out = new ArrayList[n];
        List<Edge>[] in = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            out[i] = new ArrayList<>();
            in[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            out[u].add(new Edge(v, w));
            in[v].add(new Edge(u, w));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        dist[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long d = cur[1];

            if (d > dist[u]) continue;

            // normal edges
            for (Edge e : out[u]) {
                if (dist[e.to] > d + e.w) {
                    dist[e.to] = d + e.w;
                    pq.offer(new long[]{e.to, dist[e.to]});
                }
            }

            // reversed incoming edges
            for (Edge e : in[u]) {
                long nd = d + 2L * e.w;
                if (dist[e.to] > nd) {
                    dist[e.to] = nd;
                    pq.offer(new long[]{e.to, nd});
                }
            }
        }

        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}
