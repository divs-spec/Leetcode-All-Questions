class Solution {
    func findRotation(_ mat: [[Int]], _ target: [[Int]]) -> Bool {
        var mat = mat
        
        for _ in 0..<4 {
            if isEqual(mat, target) {
                return true
            }
            mat = rotate(mat)
        }
        return false
    }
    
    private func isEqual(_ a: [[Int]], _ b: [[Int]]) -> Bool {
        let n = a.count
        for i in 0..<n {
            for j in 0..<n {
                if a[i][j] != b[i][j] {
                    return false
                }
            }
        }
        return true
    }
    
    private func rotate(_ mat: [[Int]]) -> [[Int]] {
        let n = mat.count
        var res = Array(repeating: Array(repeating: 0, count: n), count: n)
        
        for i in 0..<n {
            for j in 0..<n {
                res[j][n - 1 - i] = mat[i][j]
            }
        }
        return res
    }
}
