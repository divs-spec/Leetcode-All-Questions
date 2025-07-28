class Solution {
    public int countMaxOrSubsets(int[] nums) {
           int maxOR = 0;
        for (int num : nums) {
            maxOR |= num;
        }
        
        return backtrack(nums, maxOR, 0, 0);
    }

    private int backtrack(int[] nums, int maxOR, int index, int currentOR) {
        if (index == nums.length) {
            return currentOR == maxOR ? 1 : 0;
        }
        
        if (currentOR == maxOR) {
            return 1 << (nums.length - index);
        }
        
        return backtrack(nums, maxOR, index + 1, currentOR | nums[index]) +
               backtrack(nums, maxOR, index + 1, currentOR);
    }
}

// approach no 2:
class Solution {
      int res = 0, target = 0;
    public int countMaxOrSubsets(int[] nums) {
        
     for (int num : nums)
            target |= num;
        
        dfs(nums, 0, 0);
        return res;
    }
    
    public void dfs(int[] nums, int idx, int mask) {
        if (mask == target) res++;
        
        for (int i = idx; i < nums.length; i++)
            dfs(nums, i + 1, mask | nums[i]);
    }
}
