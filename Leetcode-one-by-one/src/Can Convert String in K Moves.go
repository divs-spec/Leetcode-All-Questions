func canConvertString(s string, t string, k int) bool {
	if len(s) != len(t) {
		return false
	}

	cnt := make([]int, 26)

	for i := 0; i < len(s); i++ {
		diff := (int(t[i])-int(s[i]) + 26) % 26

		if diff == 0 {
			continue
		}

		requiredMove := diff + 26*cnt[diff]

		if requiredMove > k {
			return false
		}

		cnt[diff]++
	}

	return true
}
