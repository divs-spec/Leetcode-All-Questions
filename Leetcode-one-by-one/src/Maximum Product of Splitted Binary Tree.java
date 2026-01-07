class Solution {
    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // First DFS to compute total sum
        computeTotalSum(root);

        // Second DFS to compute max product
        computeSubtreeSum(root);

        return (int) (maxProduct % MOD);
    }

    private void computeTotalSum(TreeNode node) {
        if (node == null) return;
        totalSum += node.val;
        computeTotalSum(node.left);
        computeTotalSum(node.right);
    }

    private long computeSubtreeSum(TreeNode node) {
        if (node == null) return 0;

        long left = computeSubtreeSum(node.left);
        long right = computeSubtreeSum(node.right);

        long subSum = left + right + node.val;

        long product = subSum * (totalSum - subSum);
        maxProduct = Math.max(maxProduct, product);

        return subSum;
    }
}
