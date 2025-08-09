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
    public List<List<Integer>> levelOrder(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.add(root);
        if(root==null) return result;
        while(queue.size()>0)
        {
            List<Integer> row = new ArrayList<>();
            int size = queue.size();
            for(int i=0;i<size;i++)
            {
                root=queue.remove();
                row.add(root.val);
                if(root.left!=null)
                {
                    queue.add(root.left);
                }
                if(root.right!=null)
                {
                    queue.add(root.right);
                }
            } 
            result.add(row);
        }
        return result;
    }
}
