class Solution {
    func bitwiseComplement(_ n: Int) -> Int {
        if n == 0 { return 1 }

        var mask = 1
        while mask <= n {
            mask <<= 1
        }

        mask -= 1
        return n ^ mask
    }
}
