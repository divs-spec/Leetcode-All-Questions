class Solution {
    func closestTarget(_ words: [String], _ target: String, _ startIndex: Int) -> Int {
        let n = words.count
        var ans = Int.max
        
        for i in 0..<n {
            if words[i] == target {
                let diff = abs(i - startIndex)
                let dist = min(diff, n - diff)
                ans = min(ans, dist)
            }
        }
        
        return ans == Int.max ? -1 : ans
    }
}
