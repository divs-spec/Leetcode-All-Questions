func sortByBits(arr []int) []int {
    sort.Slice(arr, func(i, j int) bool { return bits.OnesCount(uint(arr[i])) == bits.OnesCount(uint(arr[j])) && arr[i] < arr[j] || bits.OnesCount(uint(arr[i])) < bits.OnesCount(uint(arr[j])) })
    return arr
}
