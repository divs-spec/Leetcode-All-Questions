class Solution {
    public long minimalKSum(int[] nums, int k) {
       Arrays.sort(nums);
       long prev=-1;
       long sum=0;
       long x=(long) k;
       for(int num:nums)
       {
        if(num==prev) continue;
        prev=num;
        if(num<=x)
        {
            sum+=num;
            x++;
        }
        else
        {
            break;
        }
       }
       return (x*(x+1)/2)-sum;
    }
}
