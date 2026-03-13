package main

import "math"

func minNumberOfSeconds(mountainHeight int, workerTimes []int) int64 {

	maxVal := 0
	for _, x := range workerTimes {
		if x > maxVal {
			maxVal = x
		}
	}

	h := (mountainHeight-1)/len(workerTimes) + 1

	left := int64(1)
	right := int64(maxVal) * int64(h) * int64(h+1) / 2

	for left <= right {
		mid := left + (right-left)/2

		if check(mountainHeight, workerTimes, mid) {
			right = mid - 1
		} else {
			left = mid + 1
		}
	}

	return left
}

func check(mountainHeight int, workerTimes []int, time int64) bool {

	for _, x := range workerTimes {

		val := float64(1 + 8*(time/int64(x)))
		k := int((-1 + math.Sqrt(val)) / 2)

		mountainHeight -= k

		if mountainHeight <= 0 {
			return true
		}
	}

	return false
}
