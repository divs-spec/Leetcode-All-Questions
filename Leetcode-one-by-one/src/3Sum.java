class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        
        for(int i = 0; i < n; i++){
            // Skipping duplicates of i
            if(i > 0 && nums[i] == nums[i-1]) continue;

            // Now, i is set. Get two pointers for j and k (since we need a triplet)
            int j = i + 1;
            int k = nums.length - 1;

            while(j < k){
                int total = nums[i] + nums[j] + nums[k];
                if(total > 0){
                    // Higher than 0, so reducing k.
                    k--;
                } else if (total < 0){
                    // Lower than 0, so increasing j.
                    j++;
                }
                else{
                    // Target as 0 is achieved, add to the list.
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    // Skipping duplicates
                    while(nums[j] == nums[j-1] && j < k){
                        j++;
                    }
                }
            }
        }

        return ans;
    }
}
