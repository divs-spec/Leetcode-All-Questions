class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k;                 // total binary codes = 2^k
        boolean[] seen = new boolean[need];
        int mask = need - 1;               // to keep last k bits
        int hash = 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // build rolling hash
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');

            // start checking once window size reaches k
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    count++;
                    if (count == need) return true;
                }
            }
        }
        return false;
    }
}
