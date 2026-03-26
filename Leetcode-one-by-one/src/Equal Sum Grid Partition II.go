func canPartitionGrid(grid [][]int) bool {
    m := len(grid)
    n := len(grid[0])

    rowSum := make([]int64, m)
    var T int64 = 0

    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            rowSum[i] += int64(grid[i][j])
            T += int64(grid[i][j])
        }
    }

    rowPrefix := make([]int64, m+1)
    for i := 1; i <= m; i++ {
        rowPrefix[i] = rowPrefix[i-1] + rowSum[i-1]
    }

    colSum := make([]int64, n)
    for j := 0; j < n; j++ {
        for i := 0; i < m; i++ {
            colSum[j] += int64(grid[i][j])
        }
    }

    colPrefix := make([]int64, n+1)
    for j := 1; j <= n; j++ {
        colPrefix[j] = colPrefix[j-1] + colSum[j-1]
    }

    // Equal split
    for k := 1; k < m; k++ {
        s1 := rowPrefix[k]
        if s1 == T-s1 {
            return true
        }
    }

    for k := 1; k < n; k++ {
        s1 := colPrefix[k]
        if s1 == T-s1 {
            return true
        }
    }

    const MAXV = 100000

    // Horizontal top
    if m >= 2 {
        freq := make([]int, MAXV+1)

        for k := 1; k < m; k++ {
            for j := 0; j < n; j++ {
                v := grid[k-1][j]
                if v <= MAXV {
                    freq[v]++
                }
            }

            s1 := rowPrefix[k]
            s2 := T - s1

            if s1 <= s2 {
                continue
            }

            dd := s1 - s2
            if dd > MAXV {
                continue
            }

            D := int(dd)
            h, w := k, n
            rlo, rhi := 0, k-1
            clo, chi := 0, n-1

            can := false

            if !(h == 1 && w == 1) {
                if h >= 2 && w >= 2 {
                    if freq[D] > 0 {
                        can = true
                    }
                } else {
                    if grid[rlo][clo] == D ||
                        grid[rlo][chi] == D ||
                        grid[rhi][clo] == D ||
                        grid[rhi][chi] == D {
                        can = true
                    }
                }
            }

            if can {
                return true
            }
        }
    }

    // Horizontal bottom
    if m >= 2 {
        freq := make([]int, MAXV+1)

        for bh := 1; bh < m; bh++ {
            addRow := m - bh
            for j := 0; j < n; j++ {
                v := grid[addRow][j]
                if v <= MAXV {
                    freq[v]++
                }
            }

            topk := m - bh
            s1 := rowPrefix[topk]
            s2 := T - s1

            if s2 <= s1 {
                continue
            }

            dd := s2 - s1
            if dd > MAXV {
                continue
            }

            D := int(dd)
            h, w := bh, n
            rlo, rhi := m-bh, m-1
            clo, chi := 0, n-1

            can := false

            if !(h == 1 && w == 1) {
                if h >= 2 && w >= 2 {
                    if freq[D] > 0 {
                        can = true
                    }
                } else {
                    if grid[rlo][clo] == D ||
                        grid[rlo][chi] == D ||
                        grid[rhi][clo] == D ||
                        grid[rhi][chi] == D {
                        can = true
                    }
                }
            }

            if can {
                return true
            }
        }
    }

    // Vertical left
    if n >= 2 {
        freq := make([]int, MAXV+1)

        for k := 1; k < n; k++ {
            addCol := k - 1
            for i := 0; i < m; i++ {
                v := grid[i][addCol]
                if v <= MAXV {
                    freq[v]++
                }
            }

            s1 := colPrefix[k]
            s2 := T - s1

            if s1 <= s2 {
                continue
            }

            dd := s1 - s2
            if dd > MAXV {
                continue
            }

            D := int(dd)
            h, w := m, k
            rlo, rhi := 0, m-1
            clo, chi := 0, k-1

            can := false

            if !(h == 1 && w == 1) {
                if h >= 2 && w >= 2 {
                    if freq[D] > 0 {
                        can = true
                    }
                } else {
                    if grid[rlo][clo] == D ||
                        grid[rlo][chi] == D ||
                        grid[rhi][clo] == D ||
                        grid[rhi][chi] == D {
                        can = true
                    }
                }
            }

            if can {
                return true
            }
        }
    }

    // Vertical right
    if n >= 2 {
        freq := make([]int, MAXV+1)

        for bw := 1; bw < n; bw++ {
            addCol := n - bw
            for i := 0; i < m; i++ {
                v := grid[i][addCol]
                if v <= MAXV {
                    freq[v]++
                }
            }

            leftk := n - bw
            s1 := colPrefix[leftk]
            s2 := T - s1

            if s2 <= s1 {
                continue
            }

            dd := s2 - s1
            if dd > MAXV {
                continue
            }

            D := int(dd)
            h, w := m, bw
            rlo, rhi := 0, m-1
            clo, chi := n-bw, n-1

            can := false

            if !(h == 1 && w == 1) {
                if h >= 2 && w >= 2 {
                    if freq[D] > 0 {
                        can = true
                    }
                } else {
                    if grid[rlo][clo] == D ||
                        grid[rlo][chi] == D ||
                        grid[rhi][clo] == D ||
                        grid[rhi][chi] == D {
                        can = true
                    }
                }
            }

            if can {
                return true
            }
        }
    }

    return false
}
