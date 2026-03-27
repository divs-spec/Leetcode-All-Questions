class Solution {
    func areSimilar(_ mat: [[Int]], _ k: Int) -> Bool {
        let m = mat.count
        let n = mat[0].count
        let k = k % n
        
        for i in 0..<m {
            for j in 0..<n {
                if i % 2 == 0 {
                    if mat[i][j] != mat[i][(j + k) % n] {
                        return false
                    }
                } else {
                    if mat[i][j] != mat[i][(j - k + n) % n] {
                        return false
                    }
                }
            }
        }
        return true
    }
}
