class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> hm = new HashMap<>();
        int sum = 0, cnt = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(sum == k)
            ++cnt;
            if(hm.containsKey(sum - k))
            cnt += hm.get(sum - k);
            hm.put(sum, hm.getOrDefault(sum,0)+1);
        }
        return cnt;
    }
}
