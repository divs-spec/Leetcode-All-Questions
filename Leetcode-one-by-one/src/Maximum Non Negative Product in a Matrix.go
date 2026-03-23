func maxProductPath(grid [][]int) int {
    m, n := len(grid), len(grid[0])
    const MOD int64 = 1e9 + 7

    maxDP := make([][]int64, m)
    minDP := make([][]int64, m)
    for i := range maxDP {
        maxDP[i] = make([]int64, n)
        minDP[i] = make([]int64, n)
    }

    maxDP[0][0] = int64(grid[0][0])
    minDP[0][0] = int64(grid[0][0])

    // first column
    for i := 1; i < m; i++ {
        val := int64(grid[i][0])
        maxDP[i][0] = maxDP[i-1][0] * val
        minDP[i][0] = maxDP[i][0]
    }

    // first row
    for j := 1; j < n; j++ {
        val := int64(grid[0][j])
        maxDP[0][j] = maxDP[0][j-1] * val
        minDP[0][j] = maxDP[0][j]
    }

    // DP
    for i := 1; i < m; i++ {
        for j := 1; j < n; j++ {
            val := int64(grid[i][j])

            a := maxDP[i-1][j] * val
            b := minDP[i-1][j] * val
            c := maxDP[i][j-1] * val
            d := minDP[i][j-1] * val

            maxDP[i][j] = max(max(a, b), max(c, d))
            minDP[i][j] = min(min(a, b), min(c, d))
        }
    }

    res := maxDP[m-1][n-1]
    if res < 0 {
        return -1
    }

    return int(res % MOD)
}

// helper functions
func max(a, b int64) int64 {
    if a > b {
        return a
    }
    return b
}

func min(a, b int64) int64 {
    if a < b {
        return a
    }
    return b
}
