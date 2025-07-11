public class Solution {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n <= first) {
                first = n; // new smallest
            } else if (n <= second) {
                second = n; // new second smallest
            } else {
                return true; // found third > second
            }
        }
        return false;
    }
}
