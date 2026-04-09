import Foundation

class Solution {
    
    private let MOD = 1_000_000_007
    
    private func pow(_ x: Int64, _ y: Int64) -> Int {
        var base = x % Int64(MOD)
        var exp = y
        var res: Int64 = 1
        
        while exp > 0 {
            if (exp & 1) == 1 {
                res = (res * base) % Int64(MOD)
            }
            base = (base * base) % Int64(MOD)
            exp >>= 1
        }
        return Int(res)
    }
    
    func xorAfterQueries(_ nums: [Int], _ queries: [[Int]]) -> Int {
        var nums = nums
        let n = nums.count
        let T = Int(sqrt(Double(n)))
        
        // groups[k] stores queries with step k
        var groups = Array(repeating: [[Int]](), count: T)
        
        for q in queries {
            let l = q[0], r = q[1], k = q[2], v = q[3]
            if k < T {
                groups[k].append([l, r, v])
            } else {
                var i = l
                while i <= r {
                    nums[i] = Int((Int64(nums[i]) * Int64(v)) % Int64(MOD))
                    i += k
                }
            }
        }
        
        var dif = Array(repeating: Int64(1), count: n + T)
        
        for k in 1..<T {
            if groups[k].isEmpty { continue }
            
            // reset dif
            for i in 0..<dif.count {
                dif[i] = 1
            }
            
            for q in groups[k] {
                let l = q[0], r = q[1], v = q[2]
                
                dif[l] = (dif[l] * Int64(v)) % Int64(MOD)
                
                let R = ((r - l) / k + 1) * k + l
                if R < dif.count {
                    let inv = pow(Int64(v), Int64(MOD - 2))
                    dif[R] = (dif[R] * Int64(inv)) % Int64(MOD)
                }
            }
            
            // prefix multiplication with step k
            for i in k..<n {
                dif[i] = (dif[i] * dif[i - k]) % Int64(MOD)
            }
            
            // apply to nums
            for i in 0..<n {
                nums[i] = Int((Int64(nums[i]) * dif[i]) % Int64(MOD))
            }
        }
        
        var res = 0
        for x in nums {
            res ^= x
        }
        
        return res
    }
}
