import (
    "math"
    "sort"
)

func minAbsDiff(grid [][]int, k int) [][]int {
    m, n := len(grid), len(grid[0])

    // Edge case
    if k == 1 {
        ans := make([][]int, m)
        for i := range ans {
            ans[i] = make([]int, n)
        }
        return ans
    }

    ans := make([][]int, m-k+1)
    for i := range ans {
        ans[i] = make([]int, n-k+1)
    }

    for i := 0; i <= m-k; i++ {
        for j := 0; j <= n-k; j++ {

            arr := make([]int, 0, k*k)

            // Collect elements
            for x := i; x < i+k; x++ {
                for y := j; y < j+k; y++ {
                    arr = append(arr, grid[x][y])
                }
            }

            sort.Ints(arr)

            minDiff := math.MaxInt32

            for t := 1; t < len(arr); t++ {
                if arr[t] == arr[t-1] {
                    continue
                }
                diff := arr[t] - arr[t-1]
                if diff < minDiff {
                    minDiff = diff
                }
            }

            // If all elements same
            if minDiff == math.MaxInt32 {
                minDiff = 0
            }

            ans[i][j] = minDiff
        }
    }

    return ans
}
