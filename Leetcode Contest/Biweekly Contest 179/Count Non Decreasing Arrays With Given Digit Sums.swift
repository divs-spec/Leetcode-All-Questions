class Solution {
    let MOD = 1_000_000_007
    
    func countArrays(_ digitSum: [Int]) -> Int {
        let n = digitSum.count
        
        let maxSum = digitSum.max() ?? 0
        var nums = Array(repeating: [Int](), count: maxSum + 1)
        
        // Precompute valid numbers
        for x in 0...5000 {
            let s = digitSumOf(x)
            if s <= maxSum {
                nums[s].append(x)
            }
        }
        
        // Edge case
        if nums[digitSum[0]].isEmpty { return 0 }
        
        var dp = nums[digitSum[0]].map { _ in 1 }
        
        for i in 1..<n {
            let prevNums = nums[digitSum[i - 1]]
            let currNums = nums[digitSum[i]]
            
            if currNums.isEmpty { return 0 }
            
            // Prefix sum
            var prefix = [Int](repeating: 0, count: dp.count + 1)
            for j in 0..<dp.count {
                prefix[j + 1] = (prefix[j] + dp[j]) % MOD
            }
            
            var newDP = [Int](repeating: 0, count: currNums.count)
            
            for j in 0..<currNums.count {
                let val = currNums[j]
                
                // Binary search
                var l = 0, r = prevNums.count - 1, idx = -1
                while l <= r {
                    let mid = (l + r) / 2
                    if prevNums[mid] <= val {
                        idx = mid
                        l = mid + 1
                    } else {
                        r = mid - 1
                    }
                }
                
                if idx >= 0 {
                    newDP[j] = prefix[idx + 1]
                }
            }
            
            dp = newDP
        }
        
        return dp.reduce(0) { ($0 + $1) % MOD }
    }
    
    func digitSumOf(_ x: Int) -> Int {
        var x = x, sum = 0
        while x > 0 {
            sum += x % 10
            x /= 10
        }
        return sum
    }
}
