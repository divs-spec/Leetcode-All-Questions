from typing import List

class Solution:
    def minimumFlips(self, n: int, edges: List[List[int]], start: str, target: str) -> List[int]:
        # graph[u] = list of (v, edge_index)
        graph = [[] for _ in range(n)]

        for i, (u, v) in enumerate(edges):
            graph[u].append((v, i))
            graph[v].append((u, i))

        # diff[i] = 1 if start[i] != target[i], else 0
        diff = [(ord(start[i]) ^ ord(target[i])) & 1 for i in range(n)]

        answer = []

        def dfs(u: int, parent: int):
            for v, edge_idx in graph[u]:
                if v == parent:
                    continue

                dfs(v, u)

                if diff[v] == 1:
                    answer.append(edge_idx)
                    diff[v] ^= 1
                    diff[u] ^= 1

        dfs(0, -1)

        if diff[0] != 0:
            return [-1]

        answer.sort()
        return answer
