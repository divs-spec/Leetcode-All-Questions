class Solution {
    func binaryGap(_ n: Int) -> Int {
        var n = n
        var lastOne = -1
        var position = 0
        var maxGap = 0

        while n > 0 {
            if (n & 1) == 1 {
                if lastOne != -1 {
                    maxGap = max(maxGap, position - lastOne)
                }
                lastOne = position
            }
            position += 1
            n >>= 1
        }

        return maxGap
    }
}
