from typing import List

class Solution:
    def minOperations(self, nums: List[int], target: List[int]) -> int:
        seen = set()

        for i in range(len(nums)):
            if nums[i] != target[i]:
                seen.add(nums[i])

        return len(seen)
