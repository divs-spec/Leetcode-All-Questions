class Solution {
    func minNumberOfSeconds(_ mountainHeight: Int, _ workerTimes: [Int]) -> Int {
        
        var maxVal = 0
        for x in workerTimes {
            maxVal = max(maxVal, x)
        }

        let h = (mountainHeight - 1) / workerTimes.count + 1
        var left: Int64 = 1
        var right: Int64 = Int64(maxVal) * Int64(h) * Int64(h + 1) / 2

        while left <= right {
            let mid = left + (right - left) / 2
            
            if check(mountainHeight, workerTimes, mid) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return Int(left)
    }

    func check(_ mountainHeight: Int, _ workerTimes: [Int], _ time: Int64) -> Bool {
        var remaining = mountainHeight

        for x in workerTimes {
            let val = Double(1 + 8 * (time / Int64(x)))
            let k = Int((-1 + sqrt(val)) / 2)
            remaining -= k
            
            if remaining <= 0 {
                return true
            }
        }

        return false
    }
}
