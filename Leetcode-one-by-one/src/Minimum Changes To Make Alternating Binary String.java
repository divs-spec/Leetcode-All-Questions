class Solution {
    public int minOperations(String s) {
        int changesStartWith0 = 0;
        int changesStartWith1 = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Pattern: 010101...
            char expected0 = (i % 2 == 0) ? '0' : '1';
            if (c != expected0) {
                changesStartWith0++;
            }

            // Pattern: 101010...
            char expected1 = (i % 2 == 0) ? '1' : '0';
            if (c != expected1) {
                changesStartWith1++;
            }
        }

        return Math.min(changesStartWith0, changesStartWith1);
    }
}
