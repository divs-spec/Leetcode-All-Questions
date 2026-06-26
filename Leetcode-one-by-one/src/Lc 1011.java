class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int minwc = Arrays.stream(weights).max().getAsInt();
        int maxwc = Arrays.stream(weights).sum() * 2/days;
        while(minwc < maxwc){
            int mid = minwc + (maxwc - minwc)/2;
            int sum = 0,d = 0,i = 0;
            while (i< weights.length){
            sum = sum+weights[i];
            if(sum==mid) {
                d++;sum=0;
            }
            else if(sum>mid) {
                sum = 0;
                i--;d++;
            }
            else if (i == weights.length - 1 && sum!=0 && sum < mid) d++;
            i++;
        }
            if(d > days)
            minwc = mid + 1;
            else
            maxwc = mid;
        }
        return minwc;
    }
}
