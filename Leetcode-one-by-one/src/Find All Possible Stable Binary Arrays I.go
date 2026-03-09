package main

import "fmt"

func numberOfStableArrays(zero int, one int, limit int) int {
	mod := 1000000007
	L := limit + 1

	dp0 := make([][]int, zero+1)
	dp1 := make([][]int, zero+1)

	for i := range dp0 {
		dp0[i] = make([]int, one+1)
		dp1[i] = make([]int, one+1)
	}

	for i := 1; i <= zero && i <= limit; i++ {
		dp0[i][0] = 1
	}

	for j := 1; j <= one && j <= limit; j++ {
		dp1[0][j] = 1
	}

	for i := 1; i <= zero; i++ {
		for j := 1; j <= one; j++ {

			val0 := dp0[i-1][j] + dp1[i-1][j]
			if i >= L {
				val0 -= dp1[i-L][j]
			}

			val1 := dp1[i][j-1] + dp0[i][j-1]
			if j >= L {
				val1 -= dp0[i][j-L]
			}

			dp0[i][j] = ((val0 % mod) + mod) % mod
			dp1[i][j] = ((val1 % mod) + mod) % mod
		}
	}

	return (dp0[zero][one] + dp1[zero][one]) % mod
}

func main() {
	fmt.Println(numberOfStableArrays(1, 2, 1))
}
