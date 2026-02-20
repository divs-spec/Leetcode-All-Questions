class Solution {
    func makeLargestSpecial(_ s: String) -> String {
        let chars = Array(s)
        return helper(chars, 0, chars.count)
    }

    private func helper(_ chars: [Character], _ start: Int, _ end: Int) -> String {
        var balance = 0
        var last = start
        var subs: [String] = []

        for i in start..<end {
            if chars[i] == "1" {
                balance += 1
            } else {
                balance -= 1
            }

            // Found a primitive special substring
            if balance == 0 {
                // recurse on inner part
                let inner = helper(chars, last + 1, i)
                subs.append("1" + inner + "0")
                last = i + 1
            }
        }

        // sort descending for lexicographically largest result
        subs.sort(by: >)
        return subs.joined()
    }
}
