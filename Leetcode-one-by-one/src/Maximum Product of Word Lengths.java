// https://leetcode.com/problems/maximum-product-of-word-lengths/solutions/6927852/java-solution-beats-9864-full-explained-v01fp/
class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        int[] lens = new int[n];

        // Step 1: Precompute bitmasks and lengths
        for (int i = 0; i < n; i++) {
            int mask = 0;
            for (char c : words[i].toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            masks[i] = mask;
            lens[i] = words[i].length();
        }

        // Step 2: Try all word pairs
        int maxProduct = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxProduct = Math.max(maxProduct, lens[i] * lens[j]);
                }
            }
        }

        return maxProduct;
    }
}
