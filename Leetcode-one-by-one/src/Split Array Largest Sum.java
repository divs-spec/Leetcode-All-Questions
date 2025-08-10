class Solution {
    public int splitArray(int[] nums, int k) {
        int n=nums.length;
        int arr[]=new int[2];
        arr=maxi(nums);
        int low=arr[0];
        int high=arr[1];
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            if(check(nums,mid,k)>k)
            {
                low=mid+1;
            }
            else
            {
                high=mid-1;
            }

        }
        return low;
    }
    public int[] maxi(int []arr)
    {
        int ans=arr[0];
        int sum=0;
        for(int num:arr)
        {
            ans=Math.max(ans,num);
            sum=sum+num;
        }
        return new int[]{ans,sum};
    }
    public int check(int []nums,int mid,int k)
    {
        int c=1;
        int total=0;
        for(int i=0;i<nums.length;i++)
        {
            total=total+nums[i];
            if(total>mid)
            {
                c++;
                total=nums[i];
            }
        }
        return c;
    }
}
/*
class Solution {

    public int partitions(int[] nums, int limit) {

        int sum = 0;
        int count = 1;

        for(int i=0; i<nums.length; i++) {
            
            if(nums[i] + sum > limit) {
                sum = nums[i];
                count++;
            }
            else {
                sum += nums[i];
            }
        }

        return count;
    }

    public int splitArray(int[] nums, int k) {

        if(nums.length < k) {
            return -1;
        } 
        
        int ans = -1;
        int low = nums[0], high = 0;

        for(int num: nums) {
            low = Math.max(num, low);
            high += num;
        }

        while(low <= high) {
            int mid = (low + high) / 2;

            if(partitions(nums, mid) > k) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return low;
    }
}
*/
