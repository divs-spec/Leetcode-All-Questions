func canPartitionGrid(grid [][]int) bool {
    m, n := len(grid), len(grid[0])

    rowSum := make([]int64, m)
    var sum int64 = 0

    // compute row sums and total
    for i := 0; i < m; i++ {
        for _, val := range grid[i] {
            rowSum[i] += int64(val)
        }
        sum += rowSum[i]
    }

    if sum%2 != 0 {
        return false
    }

    sum /= 2

    // check horizontal cut
    var total int64 = 0
    for i := 0; i < m-1 && total < sum; i++ {
        total += rowSum[i]
    }

    if total == sum {
        return true
    }

    // check vertical cut
    total = 0
    for j := 0; j < n-1 && total < sum; j++ {
        for i := 0; i < m; i++ {
            total += int64(grid[i][j])
        }
    }

    return total == sum
}
