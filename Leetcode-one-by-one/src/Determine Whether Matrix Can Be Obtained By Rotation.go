func findRotation(mat [][]int, target [][]int) bool {
    for i := 0; i < 4; i++ {
        if isEqual(mat, target) {
            return true
        }
        mat = rotate(mat)
    }
    return false
}

func isEqual(a [][]int, b [][]int) bool {
    n := len(a)
    for i := 0; i < n; i++ {
        for j := 0; j < n; j++ {
            if a[i][j] != b[i][j] {
                return false
            }
        }
    }
    return true
}

func rotate(mat [][]int) [][]int {
    n := len(mat)
    res := make([][]int, n)
    for i := range res {
        res[i] = make([]int, n)
    }

    for i := 0; i < n; i++ {
        for j := 0; j < n; j++ {
            res[j][n-1-i] = mat[i][j]
        }
    }
    return res
}
