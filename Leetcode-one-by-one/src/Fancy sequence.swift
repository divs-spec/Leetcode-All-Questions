class Fancy {
    private var nums: [Int64]
    private var mul: Int64
    private var add: Int64
    private let mod: Int64 = 1_000_000_007

    init() {
        nums = []
        mul = 1
        add = 0
    }

    private func modPow(_ base: Int64, _ exp: Int64) -> Int64 {
        var result: Int64 = 1
        var base = base % mod
        var exp = exp

        while exp > 0 {
            if exp & 1 == 1 {
                result = (result * base) % mod
            }
            base = (base * base) % mod
            exp >>= 1
        }

        return result
    }

    private func modInverse(_ x: Int64) -> Int64 {
        return modPow(x, mod - 2)
    }

    func append(_ val: Int) {
        var x = (Int64(val) - add + mod) % mod
        x = (x * modInverse(mul)) % mod
        nums.append(x)
    }

    func addAll(_ inc: Int) {
        add = (add + Int64(inc)) % mod
    }

    func multAll(_ m: Int) {
        mul = (mul * Int64(m)) % mod
        add = (add * Int64(m)) % mod
    }

    func getIndex(_ idx: Int) -> Int {
        if idx >= nums.count {
            return -1
        }

        let val = nums[idx]
        return Int((val * mul + add) % mod)
    }
}
