class Solution {
    func canPartitionGrid(_ grid: [[Int]]) -> Bool {
        let m = grid.count
        let n = grid[0].count
        
        var rowSum = Array(repeating: Int64(0), count: m)
        var T: Int64 = 0
        
        for i in 0..<m {
            for j in 0..<n {
                rowSum[i] += Int64(grid[i][j])
                T += Int64(grid[i][j])
            }
        }
        
        var rowPrefix = Array(repeating: Int64(0), count: m + 1)
        for i in 1...m {
            rowPrefix[i] = rowPrefix[i - 1] + rowSum[i - 1]
        }
        
        var colSum = Array(repeating: Int64(0), count: n)
        for j in 0..<n {
            for i in 0..<m {
                colSum[j] += Int64(grid[i][j])
            }
        }
        
        var colPrefix = Array(repeating: Int64(0), count: n + 1)
        for j in 1...n {
            colPrefix[j] = colPrefix[j - 1] + colSum[j - 1]
        }
        
        // Equal partition check
        for k in 1..<m {
            let s1 = rowPrefix[k]
            if s1 == T - s1 { return true }
        }
        
        for k in 1..<n {
            let s1 = colPrefix[k]
            if s1 == T - s1 { return true }
        }
        
        let MAXV = 100000
        
        // Horizontal top
        if m >= 2 {
            var freq = Array(repeating: 0, count: MAXV + 1)
            
            for k in 1..<m {
                for j in 0..<n {
                    let v = grid[k - 1][j]
                    if v <= MAXV { freq[v] += 1 }
                }
                
                let s1 = rowPrefix[k]
                let s2 = T - s1
                
                if s1 <= s2 { continue }
                
                let dd = s1 - s2
                if dd > MAXV { continue }
                
                let D = Int(dd)
                let h = k, w = n
                let rlo = 0, rhi = k - 1
                let clo = 0, chi = n - 1
                
                var can = false
                
                if !(h == 1 && w == 1) {
                    if h >= 2 && w >= 2 {
                        if freq[D] > 0 { can = true }
                    } else {
                        if grid[rlo][clo] == D ||
                           grid[rlo][chi] == D ||
                           grid[rhi][clo] == D ||
                           grid[rhi][chi] == D {
                            can = true
                        }
                    }
                }
                
                if can { return true }
            }
        }
        
        // Horizontal bottom
        if m >= 2 {
            var freq = Array(repeating: 0, count: MAXV + 1)
            
            for bh in 1..<m {
                let addRow = m - bh
                for j in 0..<n {
                    let v = grid[addRow][j]
                    if v <= MAXV { freq[v] += 1 }
                }
                
                let topk = m - bh
                let s1 = rowPrefix[topk]
                let s2 = T - s1
                
                if s2 <= s1 { continue }
                
                let dd = s2 - s1
                if dd > MAXV { continue }
                
                let D = Int(dd)
                let h = bh, w = n
                let rlo = m - bh, rhi = m - 1
                let clo = 0, chi = n - 1
                
                var can = false
                
                if !(h == 1 && w == 1) {
                    if h >= 2 && w >= 2 {
                        if freq[D] > 0 { can = true }
                    } else {
                        if grid[rlo][clo] == D ||
                           grid[rlo][chi] == D ||
                           grid[rhi][clo] == D ||
                           grid[rhi][chi] == D {
                            can = true
                        }
                    }
                }
                
                if can { return true }
            }
        }
        
        // Vertical left
        if n >= 2 {
            var freq = Array(repeating: 0, count: MAXV + 1)
            
            for k in 1..<n {
                let addCol = k - 1
                for i in 0..<m {
                    let v = grid[i][addCol]
                    if v <= MAXV { freq[v] += 1 }
                }
                
                let s1 = colPrefix[k]
                let s2 = T - s1
                
                if s1 <= s2 { continue }
                
                let dd = s1 - s2
                if dd > MAXV { continue }
                
                let D = Int(dd)
                let h = m, w = k
                let rlo = 0, rhi = m - 1
                let clo = 0, chi = k - 1
                
                var can = false
                
                if !(h == 1 && w == 1) {
                    if h >= 2 && w >= 2 {
                        if freq[D] > 0 { can = true }
                    } else {
                        if grid[rlo][clo] == D ||
                           grid[rlo][chi] == D ||
                           grid[rhi][clo] == D ||
                           grid[rhi][chi] == D {
                            can = true
                        }
                    }
                }
                
                if can { return true }
            }
        }
        
        // Vertical right
        if n >= 2 {
            var freq = Array(repeating: 0, count: MAXV + 1)
            
            for bw in 1..<n {
                let addCol = n - bw
                for i in 0..<m {
                    let v = grid[i][addCol]
                    if v <= MAXV { freq[v] += 1 }
                }
                
                let leftk = n - bw
                let s1 = colPrefix[leftk]
                let s2 = T - s1
                
                if s2 <= s1 { continue }
                
                let dd = s2 - s1
                if dd > MAXV { continue }
                
                let D = Int(dd)
                let h = m, w = bw
                let rlo = 0, rhi = m - 1
                let clo = n - bw, chi = n - 1
                
                var can = false
                
                if !(h == 1 && w == 1) {
                    if h >= 2 && w >= 2 {
                        if freq[D] > 0 { can = true }
                    } else {
                        if grid[rlo][clo] == D ||
                           grid[rlo][chi] == D ||
                           grid[rhi][clo] == D ||
                           grid[rhi][chi] == D {
                            can = true
                        }
                    }
                }
                
                if can { return true }
            }
        }
        
        return false
    }
}
