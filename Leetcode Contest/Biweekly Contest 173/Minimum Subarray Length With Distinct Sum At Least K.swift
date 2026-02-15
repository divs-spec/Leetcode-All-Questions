class Solution {
    func minLength(_ nums: [Int], _ k: Int) -> Int {
        var frequency: [Int: Int] = [:]
        var leftIndex = 0
        var distinctSum = 0
        var minimumLength = Int.max

        for rightIndex in 0..<nums.count {
            let currentValue = nums[rightIndex]
            
            frequency[currentValue, default: 0] += 1
            if frequency[currentValue] == 1 {
                distinctSum += currentValue
            }

            while distinctSum >= k {
                minimumLength = min(minimumLength, rightIndex - leftIndex + 1)
                
                let leftValue = nums[leftIndex]
                frequency[leftValue]! -= 1
                if frequency[leftValue] == 0 {
                    distinctSum -= leftValue
                }
                leftIndex += 1
            }
        }

        return minimumLength == Int.max ? -1 : minimumLength
    }
}
