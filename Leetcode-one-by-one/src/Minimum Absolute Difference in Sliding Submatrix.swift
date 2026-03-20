import Foundation

class Solution {
    func minAbsDiff(_ grid: [[Int]], _ k: Int) -> [[Int]] {
        let m = grid.count
        let n = grid[0].count
        
        // Edge case
        if k == 1 {
            return Array(repeating: Array(repeating: 0, count: n), count: m)
        }
        
        var ans = Array(
            repeating: Array(repeating: 0, count: n - k + 1),
            count: m - k + 1
        )
        
        for i in 0...(m - k) {
            for j in 0...(n - k) {
                
                var arr: [Int] = []
                
                // Collect elements
                for x in i..<(i + k) {
                    for y in j..<(j + k) {
                        arr.append(grid[x][y])
                    }
                }
                
                arr.sort()
                
                var minDiff = Int.max
                
                for t in 1..<arr.count {
                    if arr[t] == arr[t - 1] { continue }
                    minDiff = min(minDiff, arr[t] - arr[t - 1])
                }
                
                // If all elements same
                if minDiff == Int.max {
                    minDiff = 0
                }
                
                ans[i][j] = minDiff
            }
        }
        
        return ans
    }
}
