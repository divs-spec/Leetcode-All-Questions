func maxTotalValue(nums []int, k int) int64 {
    mn, mx := nums[0], nums[0]

    for _, x := range nums {
        if x < mn {
            mn = x
        }
        if x > mx {
            mx = x
        }
    }

    return int64(k) * int64(mx-mn)
}
