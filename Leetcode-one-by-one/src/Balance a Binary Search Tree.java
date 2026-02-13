/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private List<Integer> inorderList = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        // Step 1: collect nodes in sorted order
        inorder(root);

        // Step 2: build balanced BST
        return build(0, inorderList.size() - 1);
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        inorderList.add(node.val);
        inorder(node.right);
    }

    private TreeNode build(int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(inorderList.get(mid));
        root.left = build(left, mid - 1);
        root.right = build(mid + 1, right);

        return root;
    }
}
