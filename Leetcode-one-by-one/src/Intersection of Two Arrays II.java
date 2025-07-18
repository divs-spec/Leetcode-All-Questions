class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int a = Math.min(n, m);

        int[] count = new int[1001]; 
        int[] result = new int[a]; 

        for (int i = 0; i < n; i++) {
            count[nums1[i]]++;
        }

        int k = 0;
        for (int i = 0; i < m; i++) {
            if (count[nums2[i]] > 0) {
                result[k++] = nums2[i];
                count[nums2[i]]--;
            }
        }

        return java.util.Arrays.copyOfRange(result, 0, k);
    }
}
