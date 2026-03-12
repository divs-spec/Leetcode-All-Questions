import (
	"math"
	"sort"
)

func maxStability(n int, edges [][]int, k int) int {

	parent := make([]int, n)
	rank := make([]int, n)

	for i := 0; i < n; i++ {
		parent[i] = i
	}

	var find func(int) int
	find = func(u int) int {
		if parent[u] == u {
			return u
		}
		parent[u] = find(parent[parent[u]])
		return parent[u]
	}

	union := func(u, v int) bool {
		pu := find(u)
		pv := find(v)

		if pu == pv {
			return false
		}

		if rank[pu] >= rank[pv] {
			parent[pv] = pu
			if rank[pu] == rank[pv] {
				rank[pu]++
			}
		} else {
			parent[pu] = pv
		}

		return true
	}

	sort.Slice(edges, func(i, j int) bool {
		if edges[i][3] == edges[j][3] {
			return edges[i][2] > edges[j][2]
		}
		return edges[i][3] > edges[j][3]
	})

	comp := n
	minOne := math.MaxInt32
	minZero := math.MaxInt32

	pq := []int{}

	for _, e := range edges {
		u, v, w, t := e[0], e[1], e[2], e[3]

		ok := union(u, v)

		if t == 1 && !ok {
			return -1
		}

		if ok {
			comp--

			if t == 0 {
				pq = append(pq, w)
			} else {
				if w < minOne {
					minOne = w
				}
			}
		}
	}

	sort.Ints(pq)

	for _, val := range pq {
		if k > 0 {
			if val*2 < minZero {
				minZero = val * 2
			}
			k--
		} else {
			if val < minZero {
				minZero = val
			}
		}
	}

	if comp != 1 {
		return -1
	}

	if minOne < minZero {
		return minOne
	}
	return minZero
}
