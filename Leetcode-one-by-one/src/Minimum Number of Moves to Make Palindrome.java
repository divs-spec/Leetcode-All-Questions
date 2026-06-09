class Solution {
    public int minMovesToMakePalindrome(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        int ans = 0;

        int l = 0, r = n - 1;

        while (l < r) {
            int k = r;

            while (k > l && a[k] != a[l]) {
                k--;
            }

            if (k == l) {
                char temp = a[k];
                a[k] = a[k + 1];
                a[k + 1] = temp;
                ans++;
            } else {
                while (k < r) {
                    char temp = a[k];
                    a[k] = a[k + 1];
                    a[k + 1] = temp;
                    ans++;
                    k++;
                }
                l++;
                r--;
            }
        }

        return ans;
    }
}
