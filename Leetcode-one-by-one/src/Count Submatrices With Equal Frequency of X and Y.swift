class Solution {
    func numberOfSubmatrices(_ grid: [[Character]]) -> Int {
        let m = grid.count
        let n = grid[0].count
        
        var colX = Array(repeating: 0, count: n)
        var colY = Array(repeating: 0, count: n)
        var ans = 0
        
        for i in 0..<m {
            for j in 0..<n {
                if grid[i][j] == "X" {
                    colX[j] += 1
                }
                if grid[i][j] == "Y" {
                    colY[j] += 1
                }
            }
            
            var x = 0
            var y = 0
            
            for j in 0..<n {
                x += colX[j]
                y += colY[j]
                
                if x == y && x > 0 {
                    ans += 1
                }
            }
        }
        
        return ans
    }
}
