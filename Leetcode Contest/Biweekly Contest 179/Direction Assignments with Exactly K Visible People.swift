class Solution {
    let MOD = 1_000_000_007
    
    func countVisiblePeople(_ n: Int, _ pos: Int, _ k: Int) -> Int {
        return Int((2 * Int64(comb(n - 1, k))) % Int64(MOD))
    }
    
    func comb(_ n: Int, _ k: Int) -> Int {
        if k < 0 || k > n { return 0 }
        
        var num: Int64 = 1
        var den: Int64 = 1
        
        for i in 0..<k {
            num = num * Int64(n - i) % Int64(MOD)
            den = den * Int64(i + 1) % Int64(MOD)
        }
        
        return Int(num * modInverse(den) % Int64(MOD))
    }
    
    func modInverse(_ x: Int64) -> Int64 {
        return modPow(x, Int64(MOD - 2))
    }
    
    func modPow(_ base: Int64, _ exp: Int64) -> Int64 {
        var base = base
        var exp = exp
        var result: Int64 = 1
        
        while exp > 0 {
            if exp % 2 == 1 {
                result = result * base % Int64(MOD)
            }
            base = base * base % Int64(MOD)
            exp /= 2
        }
        return result
    }
}
