func minimizedMaximum(n int, quantities []int) int {
	left, right := 1, 0

	for _, q := range quantities {
		if q > right {
			right = q
		}
	}

	for left < right {
		mid := left + (right-left)/2

		var storesNeeded int64 = 0
		for _, q := range quantities {
			storesNeeded += int64((q + mid - 1) / mid)
		}

		if storesNeeded <= int64(n) {
			right = mid
		} else {
			left = mid + 1
		}
	}

	return left
}
