class Solution {
    func minCost(_ grid: [[Int]]) -> Int {
        let m = grid.count
        let n = grid[0].count
        
        // visited[i][j] = set of XOR values we've seen at this cell
        var visited = Array(
            repeating: Array(repeating: Set<Int>(), count: n),
            count: m
        )
        
        var queue: [(Int, Int, Int)] = []
        queue.append((0, 0, grid[0][0]))
        visited[0][0].insert(grid[0][0])
        
        var index = 0
        
        while index < queue.count {
            let (i, j, xorVal) = queue[index]
            index += 1
            
            // Move right
            if j + 1 < n {
                let newXor = xorVal ^ grid[i][j + 1]
                if !visited[i][j + 1].contains(newXor) {
                    visited[i][j + 1].insert(newXor)
                    queue.append((i, j + 1, newXor))
                }
            }
            
            // Move down
            if i + 1 < m {
                let newXor = xorVal ^ grid[i + 1][j]
                if !visited[i + 1][j].contains(newXor) {
                    visited[i + 1][j].insert(newXor)
                    queue.append((i + 1, j, newXor))
                }
            }
        }
        
        // Find minimum XOR at destination
        return visited[m - 1][n - 1].min()!
    }
}
