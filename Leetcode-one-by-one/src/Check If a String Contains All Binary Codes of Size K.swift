class Solution {
    func hasAllCodes(_ s: String, _ k: Int) -> Bool {
        let n = s.count
        if n < k { return false }

        let need = 1 << k                 // total binary codes = 2^k
        var seen = Array(repeating: false, count: need)
        let mask = need - 1               // keeps last k bits

        var hash = 0
        var count = 0

        let chars = Array(s)

        for i in 0..<n {
            // rolling hash using bitwise ops
            hash = ((hash << 1) & mask) | (chars[i] == "1" ? 1 : 0)

            if i >= k - 1 {
                if !seen[hash] {
                    seen[hash] = true
                    count += 1
                    if count == need {
                        return true
                    }
                }
            }
        }
        return false
    }
}
