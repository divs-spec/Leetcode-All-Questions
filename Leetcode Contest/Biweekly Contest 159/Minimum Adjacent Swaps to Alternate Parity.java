class Solution {
    public int minSwaps(int[] nums) {
        List<Integer> evenIndices = new ArrayList<>();
        List<Integer> oddIndices = new ArrayList<>();

        // Track indices of even and odd numbers
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) evenIndices.add(i);
            else oddIndices.add(i);
        }

        int evenCount = evenIndices.size();
        int oddCount = oddIndices.size();

        if (Math.abs(evenCount - oddCount) > 1) return -1;

        int res = Integer.MAX_VALUE;
        if (evenCount >= oddCount) {
            res = Math.min(res, calcSwaps(evenIndices, 0));  // even at 0,2,4,...
        }
        if (oddCount >= evenCount) {
            res = Math.min(res, calcSwaps(oddIndices, 0));   // odd at 0,2,4,...
        }
        return res;
    }

    private int calcSwaps(List<Integer> indices, int start) {
        int swaps = 0;
        for (int i = 0; i < indices.size(); i++) {
            swaps += Math.abs(indices.get(i) - (start + 2 * i));
        }
        return swaps;
    }
}
