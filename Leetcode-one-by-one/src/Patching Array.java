class Solution {
    public int minPatches(int[] nums, int n) {
        long miss = 1;
        int index = 0;
        int patches = 0;

        while (miss <= n) {
            if (index < nums.length && nums[index] <= miss) {
                miss += nums[index++];
            } else {
                // Patch needed
                miss += miss;
                patches++;
            }
        }
        return patches;
    }
}
