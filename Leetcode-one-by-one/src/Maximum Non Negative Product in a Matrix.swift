class Solution {
    func maxProductPath(_ grid: [[Int]]) -> Int {
        let m = grid.count
        let n = grid[0].count
        let MOD = 1_000_000_007
        
        var maxDP = Array(repeating: Array(repeating: Int64(0), count: n), count: m)
        var minDP = Array(repeating: Array(repeating: Int64(0), count: n), count: m)
        
        maxDP[0][0] = Int64(grid[0][0])
        minDP[0][0] = Int64(grid[0][0])
        
        // first column
        for i in 1..<m {
            let val = Int64(grid[i][0])
            maxDP[i][0] = maxDP[i-1][0] * val
            minDP[i][0] = maxDP[i][0]
        }
        
        // first row
        for j in 1..<n {
            let val = Int64(grid[0][j])
            maxDP[0][j] = maxDP[0][j-1] * val
            minDP[0][j] = maxDP[0][j]
        }
        
        // DP
        for i in 1..<m {
            for j in 1..<n {
                let val = Int64(grid[i][j])
                
                let a = maxDP[i-1][j] * val
                let b = minDP[i-1][j] * val
                let c = maxDP[i][j-1] * val
                let d = minDP[i][j-1] * val
                
                maxDP[i][j] = max(max(a, b), max(c, d))
                minDP[i][j] = min(min(a, b), min(c, d))
            }
        }
        
        let result = maxDP[m-1][n-1]
        if result < 0 {
            return -1
        }
        
        return Int(result % Int64(MOD))
    }
}
