class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String str = s + s;

        int alt1 = 0, alt2 = 0;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c != (i % 2 == 0 ? '0' : '1')) alt1++;
            if (c != (i % 2 == 0 ? '1' : '0')) alt2++;

            if (i >= n) {
                char prev = str.charAt(i - n);

                if (prev != ((i - n) % 2 == 0 ? '0' : '1')) alt1--;
                if (prev != ((i - n) % 2 == 0 ? '1' : '0')) alt2--;
            }

            if (i >= n - 1) {
                res = Math.min(res, Math.min(alt1, alt2));
            }
        }

        return res;
    }
}
