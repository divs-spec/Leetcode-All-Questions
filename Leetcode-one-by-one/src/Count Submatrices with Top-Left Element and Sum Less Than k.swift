class Solution {
    func countSubmatrices(_ grid: [[Int]], _ k: Int) -> Int {
        var grid = grid  // make mutable copy
        let m = grid.count
        let n = grid[0].count
        
        var count = 0
        
        for i in 0..<m {
            for j in 0..<n {
                
                // Build prefix sum in-place
                if i > 0 {
                    grid[i][j] += grid[i - 1][j]
                }
                if j > 0 {
                    grid[i][j] += grid[i][j - 1]
                }
                if i > 0 && j > 0 {
                    grid[i][j] -= grid[i - 1][j - 1]
                }
                
                // Check condition
                if grid[i][j] <= k {
                    count += 1
                }
            }
        }
        
        return count
    }
}
