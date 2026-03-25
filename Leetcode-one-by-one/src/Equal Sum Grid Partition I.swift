class Solution {
    func canPartitionGrid(_ grid: [[Int]]) -> Bool {
        let m = grid.count
        let n = grid[0].count
        
        var rowSum = Array(repeating: Int64(0), count: m)
        var sum: Int64 = 0
        
        // compute row sums and total
        for i in 0..<m {
            for val in grid[i] {
                rowSum[i] += Int64(val)
            }
            sum += rowSum[i]
        }
        
        if sum % 2 != 0 {
            return false
        }
        
        sum /= 2
        
        // check horizontal cut
        var total: Int64 = 0
        for i in 0..<(m - 1) {
            if total >= sum { break }
            total += rowSum[i]
        }
        
        if total == sum {
            return true
        }
        
        // check vertical cut
        total = 0
        for j in 0..<(n - 1) {
            if total >= sum { break }
            for i in 0..<m {
                total += Int64(grid[i][j])
            }
        }
        
        return total == sum
    }
}
