class Solution {

    private var first = -1
    private var second = -1
    private var third = -1

    func getBiggestThree(_ grid: [[Int]]) -> [Int] {
        let m = grid.count
        let n = grid[0].count

        for i in 0..<m {
            for j in 0..<n {
                add(grid[i][j])
            }
        }

        for i in 0..<m {
            for j in 0..<n {

                var k = 1
                while i - k >= 0 && i + k < m && j - k >= 0 && j + k < n {

                    var sum = 0

                    for d in 0..<k {
                        sum += grid[i - k + d][j + d]
                    }
                    for d in 0..<k {
                        sum += grid[i + d][j + k - d]
                    }
                    for d in 0..<k {
                        sum += grid[i + k - d][j - d]
                    }
                    for d in 0..<k {
                        sum += grid[i - d][j - k + d]
                    }

                    add(sum)
                    k += 1
                }
            }
        }

        if second == -1 { return [first] }
        if third == -1 { return [first, second] }

        return [first, second, third]
    }

    private func add(_ val: Int) {

        if val == first || val == second || val == third {
            return
        }

        if val > first {
            third = second
            second = first
            first = val
        }
        else if val > second {
            third = second
            second = val
        }
        else if val > third {
            third = val
        }
    }
}
