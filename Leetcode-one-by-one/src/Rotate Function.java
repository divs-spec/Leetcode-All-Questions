class Solution {
    public int maxRotateFunction (int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int sum = 0, F0 = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            sum += A [i];
            F0 += i * A [i];
        }
        int dp [] = new int [A.length];
        dp [0] = F0;
        max = dp [0];
        for (int i = 1; i < A.length; i++) {
            dp [i] = dp [i-1] + sum - A.length * A [A.length - i];
            max = Math.max (max, dp [i]);
        }
        return max;
    }
}
/*
class Solution {
    public int maxRotateFunction(int[] nums) {

      int sum=0;
      int f=0;
      int n=nums.length;

      for(int i=0;i<n;i++)
      {
          sum+=nums[i];
          f+=i*nums[i];

      }  

      int max=f;
      for(int i=n-1;i>0;i--)
      {
          max=Math.max(max,f+sum-(n*nums[i]));
          f=f+sum-(n*nums[i]);

      }

      return max;
    }
}
*/
