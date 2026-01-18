from typing import List
from collections import defaultdict

class Solution:
    MOD = 1_000_000_007

    def alternatingXOR(self, nums: List[int], target1: int, target2: int) -> int:
        n = len(nums)

        # dp1[i]: ways where the last block XOR = target1
        # dp2[i]: ways where the last block XOR = target2
        dp1 = [0] * (n + 1)
        dp2 = [0] * (n + 1)

        map1 = defaultdict(int)  # prefix_xor -> sum of dp1
        map2 = defaultdict(int)  # prefix_xor -> sum of dp2

        px = 0

        for i in range(1, n + 1):
            px ^= nums[i - 1]

            # block XOR = target1
            ways1 = map2[px ^ target1]
            if px == target1:
                ways1 += 1  # single block from start
            dp1[i] = ways1 % self.MOD

            # block XOR = target2 (must follow target1)
            dp2[i] = map1[px ^ target2] % self.MOD

            map1[px] = (map1[px] + dp1[i]) % self.MOD
            map2[px] = (map2[px] + dp2[i]) % self.MOD

        return (dp1[n] + dp2[n]) % self.MOD
