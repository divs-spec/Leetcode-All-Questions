/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public var val: Int
 *     public var left: TreeNode?
 *     public var right: TreeNode?
 *     public init() { self.val = 0; self.left = nil; self.right = nil; }
 *     public init(_ val: Int) { self.val = val; self.left = nil; self.right = nil; }
 *     public init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
 *         self.val = val
 *         self.left = left
 *         self.right = right
 *     }
 * }
 */
class Solution {
    func sumRootToLeaf(_ root: TreeNode?) -> Int {
        return dfs(root, 0)
    }

    private func dfs(_ node: TreeNode?, _ curr: Int) -> Int {
        guard let node = node else { return 0 }

        // Shift left and add current bit
        let value = (curr << 1) | node.val

        // If leaf node, return the computed value
        if node.left == nil && node.right == nil {
            return value
        }

        return dfs(node.left, value) + dfs(node.right, value)
    }
}
