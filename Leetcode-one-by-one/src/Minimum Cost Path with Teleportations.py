
import heapq
from bisect import bisect_left

class Solution(object):
    def minCost(self, grid, K):
        m, n = len(grid), len(grid[0])
        N = m * n

        def idx(i, j):
            return i * n + j

        # Flatten values
        val = [0] * N
        for i in range(m):
            for j in range(n):
                val[idx(i, j)] = grid[i][j]

        # Indices sorted by value
        ord_idx = list(range(N))
        ord_idx.sort(key=lambda x: val[x])
        sortedVals = [val[u] for u in ord_idx]

        # lb[u] = lower_bound position of val[u] in sortedVals
        lb = [0] * N
        for u in range(N):
            lb[u] = bisect_left(sortedVals, val[u])

        INF = 1 << 62

        def dijkstra(dist):
            pq = []
            for u in range(N):
                if dist[u] < INF:
                    heapq.heappush(pq, (dist[u], u))

            while pq:
                d, u = heapq.heappop(pq)
                if d != dist[u]:
                    continue
                i, j = divmod(u, n)

                if j + 1 < n:
                    v = u + 1
                    nd = d + val[v]
                    if nd < dist[v]:
                        dist[v] = nd
                        heapq.heappush(pq, (nd, v))
                if i + 1 < m:
                    v = u + n
                    nd = d + val[v]
                    if nd < dist[v]:
                        dist[v] = nd
                        heapq.heappush(pq, (nd, v))

        start, target = 0, idx(m - 1, n - 1)

        prev = [INF] * N
        prev[start] = 0
        dijkstra(prev)
        ans = prev[target]

        for _ in range(K):
            # suffix min over ord_idx
            suff = [INF] * (N + 1)
            for i in range(N - 1, -1, -1):
                suff[i] = min(prev[ord_idx[i]], suff[i + 1])

            # initialize next layer with teleports
            nxt = [INF] * N
            for u in range(N):
                nxt[u] = suff[lb[u]]

            dijkstra(nxt)

            ans = min(ans, nxt[target])
            prev = nxt

        if ans >= INF // 2:
            return -1
        return int(ans)
