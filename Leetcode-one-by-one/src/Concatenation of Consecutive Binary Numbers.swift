class Solution {
    func concatenatedBinary(_ n: Int) -> Int {
        let MOD = 1_000_000_007
        var result = 0
        var bitLength = 0
        
        for i in 1...n {
            // If i is power of 2, increase bit length
            if (i & (i - 1)) == 0 {
                bitLength += 1
            }
            
            // Shift left and add current number
            result = ((result << bitLength) % MOD + i) % MOD
        }
        
        return result
    }
}
