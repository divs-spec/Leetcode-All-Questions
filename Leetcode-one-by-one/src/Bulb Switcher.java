class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
/*
Intuition
🔍 Core Idea (Math Insight)
Each bulb is toggled in every round where its position is divisible by the round number.

For example, bulb 6 is toggled in rounds: 1, 2, 3, 6.

So bulb i is toggled k times, where k = number of divisors of i.

Approach
💡 Key Observation:
A bulb ends up ON only if it's toggled an odd number of times.

Only perfect squares have an odd number of divisors.

E.g., 16 has divisors: 1, 2, 4, 8, 16 → 5 (odd).

Because (a, b) and (b, a) are paired unless a = b.

✅ Final Insight:
Only bulbs at positions 1^2, 2^2, ..., k^2 ≤ n will be ON.

So answer is simply:
floor(sqrt(n))

Complexity
Time complexity: O(1)

Space complexity: O(1)
*/
