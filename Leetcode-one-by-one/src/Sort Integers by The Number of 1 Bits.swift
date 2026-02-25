class Solution {
    func sortByBits(_ arr: [Int]) -> [Int] {
        return arr.sorted { a, b in
            let bitsA = a.nonzeroBitCount
            let bitsB = b.nonzeroBitCount
            
            if bitsA != bitsB {
                return bitsA < bitsB
            }
            return a < b
        }
    }
}
