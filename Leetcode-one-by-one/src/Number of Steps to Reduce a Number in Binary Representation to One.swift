class Solution {
    func numSteps(_ s: String) -> Int {
        let chars = Array(s)
        var steps = 0
        var carry = 0

        // Traverse from right to left, ignore the most significant bit
        for i in stride(from: chars.count - 1, through: 1, by: -1) {
            let bit = Int(String(chars[i]))!

            if bit + carry == 1 {
                // odd -> add 1 (carry) + divide by 2
                steps += 2
                carry = 1
            } else {
                // even -> divide by 2
                steps += 1
            }
        }

        // If carry remains at the MSB
        if carry == 1 {
            steps += 1
        }

        return steps
    }
}
