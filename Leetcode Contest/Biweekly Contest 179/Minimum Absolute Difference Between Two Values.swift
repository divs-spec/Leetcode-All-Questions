class Solution {
    func minAbsoluteDifference(_ nums: [Int]) -> Int {
        var minDiff = Int.max
        
        // Pass 1: left → right (1 before 2)
        var lastOne = -1
        for i in 0..<nums.count {
            if nums[i] == 1 {
                lastOne = i
            } else if nums[i] == 2, lastOne != -1 {
                minDiff = min(minDiff, i - lastOne)
            }
        }
        
        // Pass 2: right → left (1 after 2)
        lastOne = -1
        for i in stride(from: nums.count - 1, through: 0, by: -1) {
            if nums[i] == 1 {
                lastOne = i
            } else if nums[i] == 2, lastOne != -1 {
                minDiff = min(minDiff, lastOne - i)
            }
        }
        
        return minDiff == Int.max ? -1 : minDiff
    }
}
