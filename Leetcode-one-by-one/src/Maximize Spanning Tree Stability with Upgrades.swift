import Foundation

class Solution {
    var parent: [Int] = []
    var rank: [Int] = []

    func maxStability(_ n: Int, _ edges: [[Int]], _ k: Int) -> Int {
        parent = Array(0..<n)
        rank = Array(repeating: 0, count: n)

        var edges = edges
        edges.sort {
            if $0[3] == $1[3] {
                return $0[2] > $1[2]
            }
            return $0[3] > $1[3]
        }

        var comp = n
        var minOne = Int(1e9)
        var minZero = Int(1e9)

        var pq: [Int] = []

        for e in edges {
            let didUnion = union(e[0], e[1])

            if e[3] == 1 && !didUnion {
                return -1
            }

            if didUnion {
                comp -= 1

                if e[3] == 0 {
                    pq.append(e[2])
                } else {
                    minOne = min(minOne, e[2])
                }
            }
        }

        pq.sort()

        var k = k
        for val in pq {
            if k > 0 {
                minZero = min(minZero, val * 2)
                k -= 1
            } else {
                minZero = min(minZero, val)
            }
        }

        if comp != 1 { return -1 }
        return min(minOne, minZero)
    }

    func union(_ u: Int, _ v: Int) -> Bool {
        let pu = find(u)
        let pv = find(v)

        if pu == pv { return false }

        if rank[pu] >= rank[pv] {
            parent[pv] = pu
            if rank[pu] == rank[pv] {
                rank[pu] += 1
            }
        } else {
            parent[pu] = pv
        }

        return true
    }

    func find(_ u: Int) -> Int {
        if parent[u] == u { return u }
        parent[u] = find(parent[parent[u]])
        return parent[u]
    }
}
