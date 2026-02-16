class Solution {
    func reverseBits(_ n: Int) -> Int {
        var answer = 0
        
        for i in 0...31 {
            let bit = (n >> i) & 1
            answer += (bit << (31 - i))
        }

        return answer
    }
}
