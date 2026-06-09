class Solution {
    func maxTotalValue(_ nums: [Int], _ k: Int) -> Int64 {
        var mn = Int.max
        var mx = Int.min
        
        for x in nums {
            mn = min(mn, x)
            mx = max(mx, x)
        }
        
        return Int64(k) * Int64(mx - mn)
    }
}
