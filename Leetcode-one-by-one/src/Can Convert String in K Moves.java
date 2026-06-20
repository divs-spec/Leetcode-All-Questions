class Solution {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] cnt = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int diff = (t.charAt(i) - s.charAt(i) + 26) % 26;

            if (diff == 0) {
                continue;
            }

            int requiredMove = diff + 26 * cnt[diff];

            if (requiredMove > k) {
                return false;
            }

            cnt[diff]++;
        }

        return true;
    }
}
