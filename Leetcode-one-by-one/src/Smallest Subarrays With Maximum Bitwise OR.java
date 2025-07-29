/*class Solution 
{
    public int[] smallestSubarrays(int[] nums) 
    {
        int n = nums.length;
        int[] result = new int[n];

        // Step 2: Initialize latest array to track last seen position of each bit
        int[] latest = new int[32];
        Arrays.fill(latest, -1);

        // Step 3: Traverse the array from right to left
        for (int i = n - 1; i >= 0; i--) 
        {
            int farthest = i;  // Default: subarray ends at i

            // Step 4: Update latest seen positions for bits set in nums[i]
            for (int b = 0; b < 32; b++) 
            {
                if (((nums[i] >> b) & 1) != 0) 
                {
                    latest[b] = i;
                }

                // Step 5: For each bit, if seen, update farthest position needed
                if (latest[b] != -1) 
                {
                    farthest = Math.max(farthest, latest[b]);
                }
            }

            // Step 6: Store the length of smallest subarray starting at i
            result[i] = farthest - i + 1;
        }

        // Step 7: Return the result array
        return result;
    }
}*/
class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ans = new int[nums.length];

        for (int i = 0; i < n; ++i)
        {
            int x = nums[i];
            ans[i] = 1;
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j]; --j)
            {
                nums[j] |= x;
                ans[j] = i - j + 1;
            }
        }

        return ans;
    }
}
