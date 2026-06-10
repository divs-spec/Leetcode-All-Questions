class Solution {

    final class SparseTable {
        let pow: [Int]
        let table1: [[Int]]
        let table2: [[Int]]

        init(_ arr: [Int]) {
            let n = arr.count

            var pow = Array(repeating: 0, count: n + 1)
            if n >= 2 {
                for i in 2...n {
                    pow[i] = pow[i >> 1] + 1
                }
            }

            let maxP = pow[n]

            var table1 = Array(
                repeating: Array(repeating: 0, count: n),
                count: maxP + 1
            )

            var table2 = Array(
                repeating: Array(repeating: 0, count: n),
                count: maxP + 1
            )

            table1[0] = arr
            table2[0] = arr

            if maxP > 0 {
                for p in 1...maxP {
                    let len = n - (1 << p)
                    let val = 1 << (p - 1)

                    for i in 0...len {
                        table1[p][i] = max(
                            table1[p - 1][i],
                            table1[p - 1][i + val]
                        )

                        table2[p][i] = min(
                            table2[p - 1][i],
                            table2[p - 1][i + val]
                        )
                    }
                }
            }

            self.pow = pow
            self.table1 = table1
            self.table2 = table2
        }

        func query(_ left: Int, _ right: Int) -> Int {
            let p = pow[right - left + 1]

            let mx = max(
                table1[p][left],
                table1[p][right - (1 << p) + 1]
            )

            let mn = min(
                table2[p][left],
                table2[p][right - (1 << p) + 1]
            )

            return mx - mn
        }
    }

    struct Node {
        var val: Int
        var l: Int
        var r: Int
    }

    struct MaxHeap {
        private var heap: [Node] = []

        mutating func push(_ x: Node) {
            heap.append(x)

            var i = heap.count - 1

            while i > 0 {
                let p = (i - 1) / 2

                if heap[p].val >= heap[i].val {
                    break
                }

                heap.swapAt(p, i)
                i = p
            }
        }

        mutating func pop() -> Node {
            let res = heap[0]

            if heap.count == 1 {
                heap.removeLast()
                return res
            }

            heap[0] = heap.removeLast()

            var i = 0

            while true {
                let l = i * 2 + 1
                let r = i * 2 + 2

                var best = i

                if l < heap.count && heap[l].val > heap[best].val {
                    best = l
                }

                if r < heap.count && heap[r].val > heap[best].val {
                    best = r
                }

                if best == i {
                    break
                }

                heap.swapAt(i, best)
                i = best
            }

            return res
        }
    }

    func maxTotalValue(_ nums: [Int], _ k: Int) -> Int {
        let n = nums.count

        let table = SparseTable(nums)

        var pq = MaxHeap()

        for i in 0..<n {
            pq.push(
                Node(
                    val: table.query(i, n - 1),
                    l: i,
                    r: n - 1
                )
            )
        }

        var sum = 0

        for _ in 0..<k {
            var cur = pq.pop()

            sum += cur.val

            if cur.l < cur.r {
                cur.r -= 1
                cur.val = table.query(cur.l, cur.r)
                pq.push(cur)
            }
        }

        return sum
    }
}
