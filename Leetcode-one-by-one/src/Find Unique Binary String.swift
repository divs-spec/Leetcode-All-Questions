class Solution {
    func findDifferentBinaryString(_ nums: [String]) -> String {
        let n = nums.count
        var result = ""

        for i in 0..<n {
            let index = nums[i].index(nums[i].startIndex, offsetBy: i)
            let bit = nums[i][index]
            result.append(bit == "0" ? "1" : "0")
        }

        return result
    }
}
