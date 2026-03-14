class Solution {
    func getHappyString(_ n: Int, _ k: Int) -> String {
        var k = k
        let total = 3 * (1 << (n - 1))
        if k > total { return "" }

        var res = ""
        var prev: Character = "#"

        for i in 0..<n {
            for c in ["a", "b", "c"] {
                if c.first! == prev { continue }

                let remain = n - i - 1
                let count = 1 << remain   // 2^(remaining)

                if k > count {
                    k -= count
                } else {
                    res.append(c)
                    prev = c.first!
                    break
                }
            }
        }

        return res
    }
}
