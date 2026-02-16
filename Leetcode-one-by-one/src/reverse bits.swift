class Solution {
    func reverseBits(_ n: Int) -> Int {
        var n = n
        var result = 0

        for _ in 0..<32 {
            result <<= 1           // shift result left
            result |= (n & 1)      // add last bit of n
            n >>= 1                // shift n right
        }

        return result
    }
}
