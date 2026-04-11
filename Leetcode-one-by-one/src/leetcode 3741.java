class Solution {
    public int minimumDistance(int[] nums) {
        int min = Integer.MAX_VALUE;;
        LinkedList[] items = new LinkedList[nums.length+1];
        for(int i=0;i<=nums.length;i++){
            items[i] = new LinkedList<Integer>();
        }
        for(int i=0;i<nums.length;i++){
            items[nums[i]].add(i);
            if(items[nums[i]].size() == 3){
                min = Math.min(min, 2*(i-(int) items[nums[i]].removeFirst()));
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
