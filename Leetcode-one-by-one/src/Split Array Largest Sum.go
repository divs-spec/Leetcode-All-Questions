func splitArray(nums []int, k int) int {
	var left, right int64 = 0, 0

	for _, x := range nums {
		if int64(x) > left {
			left = int64(x)
		}
		right += int64(x)
	}

	canSplit := func(limit int64) bool {
		parts := 1
		var curr int64 = 0

		for _, x := range nums {
			if curr+int64(x) > limit {
				parts++
				curr = int64(x)
			} else {
				curr += int64(x)
			}
		}

		return parts <= k
	}

	for left < right {
		mid := left + (right-left)/2

		if canSplit(mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}

	return int(left)
}
