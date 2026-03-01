class Solution {
    func minPartitions(_ n: String) -> Int {
        var maxDigit = 0
        
        for ch in n {
            let digit = Int(String(ch))!
            if digit > maxDigit {
                maxDigit = digit
            }
        }
        
        return maxDigit
    }
}
