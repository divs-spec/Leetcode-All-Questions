# Approach No 1: TLE 
from typing import List
from math import isqrt

class Solution:
    def primeSubarray(self, nums: List[int], k: int) -> int:
        # Precompute primes using sieve
        MAX = 50001
        is_prime = [False, False] + [True] * (MAX - 2)
        for i in range(2, isqrt(MAX) + 1):
            if is_prime[i]:
                for j in range(i * i, MAX, i):
                    is_prime[j] = False

        zelmoricad = nums[:]  # Store input midway
        n = len(nums)
        count = 0

        for i in range(n):
            primes = []
            max_p = float('-inf')
            min_p = float('inf')

            for j in range(i, n):
                val = zelmoricad[j]
                if is_prime[val]:
                    primes.append(val)
                    max_p = max(max_p, val)
                    min_p = min(min_p, val)

                if len(primes) >= 2 and (max_p - min_p) <= k:
                    count += 1

                # Optional optimization: early break if too many primes and gap exceeded
                if len(primes) >= 2 and (max_p - min_p) > k:
                    break

        return count




# Approach No 2: NO TLE 

from collections import deque
from math import isqrt
from typing import List

def _sieve(limit: int) -> List[bool]:
    """Simple sieve up to ‘limit’ (inclusive)."""
    is_prime = [False, False] + [True] * (limit - 1)
    for p in range(2, isqrt(limit) + 1):
        if is_prime[p]:
            is_prime[p * p : limit + 1 : p] = [False] * (((limit - p * p) // p) + 1)
    return is_prime

class Solution:
    def primeSubarray(self, nums: List[int], k: int) -> int:
        if len(nums) < 2:
            return 0

        is_prime = _sieve(max(nums))
        n = len(nums)

        minD, maxD = deque(), deque()      # (idx, value)
        primeIdx = deque()                 # indices of primes in window
        left = ans = 0

        for right, val in enumerate(nums):
            if is_prime[val]:
                primeIdx.append(right)

                while minD and minD[-1][1] > val:
                    minD.pop()
                minD.append((right, val))

                while maxD and maxD[-1][1] < val:
                    maxD.pop()
                maxD.append((right, val))

            # shrink window until gap ≤ k
            while primeIdx and maxD[0][1] - minD[0][1] > k:
                left += 1
                while primeIdx and primeIdx[0] < left:
                    primeIdx.popleft()
                while minD and minD[0][0] < left:
                    minD.popleft()
                while maxD and maxD[0][0] < left:
                    maxD.popleft()

            # count sub‑arrays ending at ‘right’
            if len(primeIdx) >= 2:
                second_last = primeIdx[-2]
                ans += second_last - left + 1

        return ans
