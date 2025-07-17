import java.util.*;

class Solution {

    /* ------------------ binary‑trie for distinct XORs ------------------ */
    private static final int MAX_BITS = 20;          // covers values ≤ 2^20 ≈ 1,048,576
    private static class Trie {
        Trie[] child = new Trie[2];
        int cnt;                                    // #distinct XORs in this sub‑trie

        /* insert or delete (delta = +1   … add first copy,
                             delta = –1) … remove last copy )               */
        void update(int num, int delta) {
            Trie node = this;
            node.cnt += delta;                      // keep root count consistent
            for (int b = MAX_BITS; b >= 0; --b) {
                int bit = (num >> b) & 1;
                if (node.child[bit] == null) node.child[bit] = new Trie();
                node = node.child[bit];
                node.cnt += delta;
            }
        }

        /* k‑th smallest (1‑indexed).  Return –1 if not enough elements. */
        int kth(int k) {
            if (k <= 0 || k > cnt) return -1;
            Trie node = this;
            int val = 0;
            for (int b = MAX_BITS; b >= 0; --b) {
                int leftCnt = node.child[0] != null ? node.child[0].cnt : 0;
                if (k <= leftCnt) {
                    node = node.child[0];           // go left
                } else {
                    k -= leftCnt;                   // skip left subtree
                    if (node.child[1] == null) return -1;  // right child missing – shouldn’t happen
                    node = node.child[1];           // go right
                    val |= 1 << b;
                }
            }
            return val;
        }
    }

    /* --------------------------- Mo on tree --------------------------- */
    private int[] tin, tout, euler, pathXor;
    private List<Integer>[] g;
    private int timer = 0;

    public int[] kthSmallest(int[] par, int[] vals, int[][] qs) {
        int n = vals.length;

        // build adjacency
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i) g[i] = new ArrayList<>();
        int root = 0;
        for (int i = 0; i < n; ++i)
            if (par[i] != -1) g[par[i]].add(i);
            else root = i;

        tin = new int[n];
        tout = new int[n];
        euler = new int[2 * n];
        pathXor = new int[n];

        dfs(root, 0, vals);                         // Euler tour + path‑xor

        /* Mo‑ordering on the Euler array              */
        int m = qs.length;
        int B = (int) Math.sqrt(2 * n);
        int[][] q = new int[m][4];
        for (int i = 0; i < m; ++i) {
            int u = qs[i][0], k = qs[i][1];
            q[i][0] = tin[u];
            q[i][1] = tout[u];
            q[i][2] = k;
            q[i][3] = i;
        }

        Arrays.sort(q, (a, b) -> {
            int blkA = a[0] / B, blkB = b[0] / B;
            if (blkA != blkB) return blkA - blkB;
            return (blkA & 1) == 0 ? a[1] - b[1] : b[1] - a[1];
        });

        Trie trie = new Trie();
        Map<Integer, Integer> freq = new HashMap<>();
        int L = 0, R = -1;
        int[] ans = new int[m];

        for (int[] qu : q) {
            int l = qu[0], r = qu[1], k = qu[2], idx = qu[3];

            while (R < r) include(euler[++R], trie, freq);
            while (L > l) include(euler[--L], trie, freq);
            while (R > r) exclude(euler[R--], trie, freq);
            while (L < l) exclude(euler[L++], trie, freq);

            ans[idx] = trie.cnt < k ? -1 : trie.kth(k);
        }
        return ans;
    }

    /* ---------- helper: Euler DFS building tin/tout & pathXor ---------- */
    private void dfs(int u, int pxor, int[] vals) {
        pathXor[u] = pxor ^ vals[u];
        tin[u] = timer;
        euler[timer++] = u;
        for (int v : g[u]) dfs(v, pathXor[u], vals);
        tout[u] = timer - 1;
    }

    /* ----------------------- sliding‑window helpers -------------------- */
    private void include(int node, Trie trie, Map<Integer, Integer> freq) {
        int x = pathXor[node];
        int f = freq.getOrDefault(x, 0);
        freq.put(x, f + 1);
        if (f == 0) trie.update(x, +1);             // add distinct value
    }

    private void exclude(int node, Trie trie, Map<Integer, Integer> freq) {
        int x = pathXor[node];
        int f = freq.get(x);
        if (f == 1) trie.update(x, -1);             // remove last copy
        freq.put(x, f - 1);
    }
}
