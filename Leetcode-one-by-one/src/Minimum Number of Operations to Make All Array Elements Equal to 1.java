class Solution {
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public int minOperations(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1)
            count++;
        }
        if(count > 0)
        return (nums.length - count);

        int n = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            int g = nums[i];
            for(int j = i+1; j < nums.length; j++){
                g = gcd(g,nums[j]);
                if(g == 1){
                    n = Math.min(n, j - i);
                    break;
                }
            }
        }
        if(n == Integer.MAX_VALUE)
        return -1;
        else
        return (n + nums.length - 1);
    }
}
