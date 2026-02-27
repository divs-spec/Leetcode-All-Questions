class Solution {
    func minOperations(_ s: String, _ k: Int) -> Int {
        let n = s.count
        let chars = Array(s)
        
        var m = 0
        for c in chars {
            if c == "0" { m += 1 }
        }
        
        let INF = Int.max
        var dist = Array(repeating: INF, count: n + 1)
        
        // Two sorted arrays acting as TreeSets
        var nodeSets = [Array<Int>(), Array<Int>()]
        
        for i in 0...n {
            nodeSets[i & 1].append(i)
        }
        
        // Remove initial m
        remove(&nodeSets[m & 1], m)
        
        var queue = [Int]()
        var head = 0
        queue.append(m)
        dist[m] = 0
        
        while head < queue.count {
            let cur = queue[head]
            head += 1
            
            let c1 = max(k - n + cur, 0)
            let c2 = min(cur, k)
            
            let lnode = cur + k - 2 * c2
            let rnode = cur + k - 2 * c1
            
            let parity = lnode & 1
            var set = nodeSets[parity]
            
            var idx = lowerBound(set, lnode)
            while idx < set.count && set[idx] <= rnode {
                let next = set[idx]
                dist[next] = dist[cur] + 1
                queue.append(next)
                
                // remove element
                set.remove(at: idx)
            }
            
            nodeSets[parity] = set
        }
        
        return dist[0] == INF ? -1 : dist[0]
    }
    
    // MARK: - Helpers
    
    func lowerBound(_ arr: [Int], _ target: Int) -> Int {
        var l = 0, r = arr.count
        while l < r {
            let mid = (l + r) >> 1
            if arr[mid] < target {
                l = mid + 1
            } else {
                r = mid
            }
        }
        return l
    }
    
    func remove(_ arr: inout [Int], _ val: Int) {
        let idx = lowerBound(arr, val)
        if idx < arr.count && arr[idx] == val {
            arr.remove(at: idx)
        }
    }
}
