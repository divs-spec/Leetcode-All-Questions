class Solution {
    func readBinaryWatch(_ turnedOn: Int) -> [String] {
        var result: [String] = []

        for hour in 0..<12 {
            for minute in 0..<60 {
                if bitCount(hour) + bitCount(minute) == turnedOn {
                    let time = "\(hour):" + String(format: "%02d", minute)
                    result.append(time)
                }
            }
        }

        return result
    }

    private func bitCount(_ n: Int) -> Int {
        var n = n
        var count = 0
        while n > 0 {
            count += n & 1
            n >>= 1
        }
        return count
    }
}
