class Solution {
    public int binaryGap(int n) {
        int lastOne = -1;   // position of previous 1
        int position = 0;   // current bit position
        int maxGap = 0;

        while (n > 0) {
            if ((n & 1) == 1) {
                if (lastOne != -1) {
                    maxGap = Math.max(maxGap, position - lastOne);
                }
                lastOne = position;
            }
            position++;
            n >>= 1;
        }

        return maxGap;
    }
}
