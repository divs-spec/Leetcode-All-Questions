class Solution {
    func countPrimeSetBits(_ left: Int, _ right: Int) -> Int {
        // Prime numbers possible for set bits (max ~32 for Int)
        let primes: Set<Int> = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31]
        
        var result = 0
        
        for num in left...right {
            let setBits = num.nonzeroBitCount
            if primes.contains(setBits) {
                result += 1
            }
        }
        
        return result
    }
}
