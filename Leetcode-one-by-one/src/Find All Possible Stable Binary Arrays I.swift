class Solution {
    func numberOfStableArrays(_ zero: Int, _ one: Int, _ limit: Int) -> Int {
        let mod = 1_000_000_007
        let L = limit + 1

        var dp0 = Array(repeating: Array(repeating: 0, count: one + 1), count: zero + 1)
        var dp1 = Array(repeating: Array(repeating: 0, count: one + 1), count: zero + 1)

        for i in 1...min(zero, limit) {
            dp0[i][0] = 1
        }

        for j in 1...min(one, limit) {
            dp1[0][j] = 1
        }

        if zero > 0 && one > 0 {
            for i in 1...zero {
                for j in 1...one {

                    var val0 = dp0[i-1][j] + dp1[i-1][j]
                    if i >= L {
                        val0 -= dp1[i-L][j]
                    }

                    var val1 = dp1[i][j-1] + dp0[i][j-1]
                    if j >= L {
                        val1 -= dp0[i][j-L]
                    }

                    dp0[i][j] = (val0 % mod + mod) % mod
                    dp1[i][j] = (val1 % mod + mod) % mod
                }
            }
        }

        return (dp0[zero][one] + dp1[zero][one]) % mod
    }
}
