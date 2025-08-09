class Solution {
    public int maxDepth(TreeNode root) {
        
        if(root == null) return 0;

        return getDepth(root);
    }

    public static int getDepth(TreeNode node){
        if(node==null)  return 0;

        return 1+ Math.max(getDepth(node.left),getDepth(node.right));        
    }
}
