class Solution {
    func countBinarySubstrings(_ s: String) -> Int {
        let chars = Array(s)
        var prev = 0      // length of previous group
        var curr = 1      // length of current group
        var result = 0

        for i in 1..<chars.count {
            if chars[i] == chars[i - 1] {
                curr += 1
            } else {
                result += min(prev, curr)
                prev = curr
                curr = 1
            }
        }

        result += min(prev, curr)
        return result
    }
}
