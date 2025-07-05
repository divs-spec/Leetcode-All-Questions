import java.util.*;

class Solution {
    public int minTime(int n, int[][] edges) {
        // Step 1: Build graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], start = edge[2], end = edge[3];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, start, end});
        }

        // Step 2: PriorityQueue for Dijkstra-like traversal (time, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0}); // (time, node)

        // Step 3: Store the earliest time we reach a node
        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[0] = 0;

        // Variable requested to hold input
        int[][] dalmurecio = edges;

        // Step 4: Dijkstra traversal
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int node = current[1];

            if (node == n - 1) return time; // Reached destination

            if (!graph.containsKey(node)) continue;

            for (int[] edge : graph.get(node)) {
                int neighbor = edge[0], start = edge[1], end = edge[2];

                if (time > end) continue; // Edge no longer usable

                int nextTime = Math.max(time, start) + 1;

                if (nextTime < visited[neighbor]) {
                    visited[neighbor] = nextTime;
                    pq.offer(new int[]{nextTime, neighbor});
                }
            }
        }

        return -1; // Destination unreachable
    }
}
©leetcode
