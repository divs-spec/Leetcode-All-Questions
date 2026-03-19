func numberOfSubmatrices(grid [][]byte) int {
    m := len(grid)
    n := len(grid[0])

    colX := make([]int, n)
    colY := make([]int, n)
    ans := 0

    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if grid[i][j] == 'X' {
                colX[j]++
            }
            if grid[i][j] == 'Y' {
                colY[j]++
            }
        }

        x, y := 0, 0

        for j := 0; j < n; j++ {
            x += colX[j]
            y += colY[j]

            if x == y && x > 0 {
                ans++
            }
        }
    }

    return ans
}
