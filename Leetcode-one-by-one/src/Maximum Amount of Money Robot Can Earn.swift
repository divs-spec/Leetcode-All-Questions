import Foundation

class Solution {
    func maximumAmount(_ coins: [[Int]]) -> Int {
        let n = coins[0].count
        
        var dp = Array(repeating: Array(repeating: Int.min / 2, count: 3), count: n + 1)
        
        for i in 0..<3 {
            dp[1][i] = 0
        }
        
        for row in coins {
            for j in 1...n {
                let x = row[j - 1]
                
                dp[j][2] = max(
                    max(dp[j - 1][2] + x, dp[j][2] + x),
                    max(dp[j - 1][1], dp[j][1])
                )
                
                dp[j][1] = max(
                    max(dp[j - 1][1] + x, dp[j][1] + x),
                    max(dp[j - 1][0], dp[j][0])
                )
                
                dp[j][0] = max(dp[j - 1][0], dp[j][0]) + x
            }
        }
        
        return dp[n][2]
    }
}
