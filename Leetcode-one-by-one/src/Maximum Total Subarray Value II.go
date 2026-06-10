package main

import "container/heap"

type SparseTable struct {
	pow    []int
	table1 [][]int
	table2 [][]int
}

func NewSparseTable(arr []int) *SparseTable {
	n := len(arr)

	pow := make([]int, n+1)
	for i := 2; i <= n; i++ {
		pow[i] = pow[i>>1] + 1
	}

	maxP := pow[n]

	table1 := make([][]int, maxP+1)
	table2 := make([][]int, maxP+1)

	table1[0] = make([]int, n)
	table2[0] = make([]int, n)

	copy(table1[0], arr)
	copy(table2[0], arr)

	for p := 1; p <= maxP; p++ {
		table1[p] = make([]int, n)
		table2[p] = make([]int, n)

		length := n - (1 << p)
		val := 1 << (p - 1)

		for i := 0; i <= length; i++ {
			table1[p][i] = max(table1[p-1][i], table1[p-1][i+val])
			table2[p][i] = min(table2[p-1][i], table2[p-1][i+val])
		}
	}

	return &SparseTable{
		pow:    pow,
		table1: table1,
		table2: table2,
	}
}

func (st *SparseTable) Query(left, right int) int {
	p := st.pow[right-left+1]

	mx := max(
		st.table1[p][left],
		st.table1[p][right-(1<<p)+1],
	)

	mn := min(
		st.table2[p][left],
		st.table2[p][right-(1<<p)+1],
	)

	return mx - mn
}

type Node struct {
	val int
	l   int
	r   int
}

type MaxHeap []Node

func (h MaxHeap) Len() int { return len(h) }
func (h MaxHeap) Less(i, j int) bool {
	return h[i].val > h[j].val
}
func (h MaxHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}
func (h *MaxHeap) Push(x interface{}) {
	*h = append(*h, x.(Node))
}
func (h *MaxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	item := old[n-1]
	*h = old[:n-1]
	return item
}

func maxTotalValue(nums []int, k int) int64 {
	n := len(nums)

	table := NewSparseTable(nums)

	pq := &MaxHeap{}
	heap.Init(pq)

	for i := 0; i < n; i++ {
		heap.Push(pq, Node{
			val: table.Query(i, n-1),
			l:   i,
			r:   n - 1,
		})
	}

	var sum int64

	for i := 0; i < k; i++ {
		cur := heap.Pop(pq).(Node)

		sum += int64(cur.val)

		if cur.l < cur.r {
			cur.r--

			cur.val = table.Query(cur.l, cur.r)

			heap.Push(pq, cur)
		}
	}

	return sum
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
