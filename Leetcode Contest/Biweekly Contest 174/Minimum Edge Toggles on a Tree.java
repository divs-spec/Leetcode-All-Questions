import java.util.*;

class Solution {
    List<int[]>[] graph;   // FIXED
    int[] diff;
    List<Integer> answer = new ArrayList<>();

    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            graph[u].add(new int[]{v, i});
            graph[v].add(new int[]{u, i});
        }

        diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = (start.charAt(i) ^ target.charAt(i)) & 1;
        }

        dfs(0, -1);

        if (diff[0] != 0) {
            return List.of(-1);
        }

        Collections.sort(answer);
        return answer;
    }

    private void dfs(int u, int parent) {
        for (int[] e : graph[u]) {
            int v = e[0];
            int edgeIdx = e[1];
            if (v == parent) continue;

            dfs(v, u);

            if (diff[v] == 1) {
                answer.add(edgeIdx);
                diff[v] ^= 1;
                diff[u] ^= 1;
            }
        }
    }
}
