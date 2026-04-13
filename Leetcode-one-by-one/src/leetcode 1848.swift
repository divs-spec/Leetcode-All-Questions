class Solution {
    func getMinDistance(_ nums: [Int], _ target: Int, _ start: Int) -> Int {
        var minDistance = Int.max
        
        for i in 0..<nums.count {
            if nums[i] == target {
                let k = abs(i - start)
                if minDistance > k {
                    minDistance = k
                }
            }
        }
        
        return minDistance
    }
}
