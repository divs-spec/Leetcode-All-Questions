class Solution {
    func minimumTotalDistance(_ r: [Int], _ f: [[Int]]) -> Int {
        let r = r.sorted()
        let f = f.sorted { $0[0] < $1[0] }
        
        let R = r.count
        let F = f.count
        
        let INF: Int64 = Int64.max / 4
        var dp = Array(repeating: Array(repeating: Int64(0), count: F + 1), count: R + 1)
        
        for i in 0..<R {
            dp[i][F] = INF
        }
        
        for j in stride(from: F - 1, through: 0, by: -1) {
            var distSum: Int64 = 0
            var deque: [(Int, Int64)] = []
            
            deque.append((R, 0))
            
            for i in stride(from: R - 1, through: 0, by: -1) {
                distSum += Int64(abs(r[i] - f[j][0]))
                
                while !deque.isEmpty && deque.first!.0 > i + f[j][1] {
                    deque.removeFirst()
                }
                
                let val = dp[i][j + 1] - distSum
                
                while !deque.isEmpty && deque.last!.1 >= val {
                    deque.removeLast()
                }
                
                deque.append((i, val))
                
                dp[i][j] = deque.first!.1 + distSum
            }
        }
        
        return Int(dp[0][0]) // ✅ FIX
    }
}
