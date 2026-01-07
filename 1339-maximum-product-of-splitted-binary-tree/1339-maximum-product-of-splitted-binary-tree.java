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
    long totalSum = 0;
    long maxProduct = 0;
    final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        totalSum = getTotalSum(root);
        computeProduct(root);
        return (int)(maxProduct % MOD);
    }

    private long getTotalSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getTotalSum(node.left) + getTotalSum(node.right);
    }


    private long computeProduct(TreeNode node) {
        if (node == null) return 0;

        long left = computeProduct(node.left);
        long right = computeProduct(node.right);

        long subSum = node.val + left + right;
        long product = subSum * (totalSum - subSum);

        maxProduct = Math.max(maxProduct, product);

        return subSum;
    }
}
