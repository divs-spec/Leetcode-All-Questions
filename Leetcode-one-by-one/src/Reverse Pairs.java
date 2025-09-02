/*class Solution {
    public int reversePairs(int[] nums) {
        int c = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                if((long)nums[i] > 2L * nums[j])
                ++c;
            }
        }
        return c;
    }
}*/
class Solution{
    public int reversePairs(int[] nums){
        return mergeSort(nums, 0, nums.length-1);
    }
    public static int mergeSort(int[] nums, int low, int high){
        int cnt = 0;
        if(low >= high) return cnt;
        int mid = low + (high - low)/2;
        cnt += mergeSort(nums, low, mid);
        cnt += mergeSort(nums,mid+1,high);
        cnt += countPairs(nums ,low ,mid , high);
        merge(nums, low , mid , high);
        return cnt;
    }
    public static void merge(int[] nums, int low , int mid , int high){
        ArrayList<Integer> ls = new ArrayList<>();
        int left = low;
        int right = mid  + 1;
        while(left <= mid && right <= high){
            if(nums[left] <= nums[right])
            {
                ls.add(nums[left]);
                left++;
            }
            else{
                ls.add(nums[right]);
                right++;
            }
        }
        while(left <= mid){
            ls.add(nums[left]);
            ++left;
        }
        while(right <= high){
            ls.add(nums[right]);
            ++right;
        }
        for(int i = low; i <= high; i++){
            nums[i] = ls.get(i - low);
        }
    }
    public static int countPairs(int[] nums, int low , int mid, int high){
        int right = mid + 1;
        int cnt = 0;

        for(int i = low; i <= mid; i++){
            while(right <= high && (long)nums[i] > 2L*nums[right])
            ++right;
            cnt += right - (mid + 1);
        }
        return cnt;
    }
}
