package org.algoyog.algos.ds.tree;

import java.util.*;

/**
 * Binary Tree Operations
 * Binary trees are hierarchical data structures where each node has at most two children
 */
public class BinaryTree {

    // TreeNode definition
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Inorder Traversal (Left, Root, Right)
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;

        // Visit left subtree
        inorderHelper(node.left, result);
        // Visit root
        result.add(node.val);
        // Visit right subtree
        inorderHelper(node.right, result);
    }

    /**
     * Preorder Traversal (Root, Left, Right)
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;

        // Visit root
        result.add(node.val);
        // Visit left subtree
        preorderHelper(node.left, result);
        // Visit right subtree
        preorderHelper(node.right, result);
    }

    /**
     * Postorder Traversal (Left, Right, Root)
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;

        // Visit left subtree
        postorderHelper(node.left, result);
        // Visit right subtree
        postorderHelper(node.right, result);
        // Visit root
        result.add(node.val);
    }

    /**
     * Level Order Traversal (BFS)
     * Time Complexity: O(n)
     * Space Complexity: O(n) in worst case when tree is completely balanced
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    /**
     * Maximum Depth of Binary Tree
     * Time Complexity: O(n)
     * Space Complexity: O(h) for recursion stack
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * Check if a Binary Tree is Balanced
     * A balanced tree is one where the depth of subtrees for every node doesn't differ by more than 1
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1; // Left subtree is unbalanced

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1; // Right subtree is unbalanced

        // Check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        // Return height of current subtree
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Lowest Common Ancestor of two nodes
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case
        if (root == null || root == p || root == q) return root;

        // Search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right are not null, root is the LCA
        if (left != null && right != null) return root;

        // Either left or right has both nodes
        return left != null ? left : right;
    }

    /**
     * Serialize and Deserialize Binary Tree
     * This is a conceptual implementation to understand the approach
     */
    public class Codec {
        // Encodes a tree to a single string using preorder traversal
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }

        private void serializeHelper(TreeNode node, StringBuilder sb) {
            // Use "null" for null nodes
            if (node == null) {
                sb.append("null,");
                return;
            }

            // Preorder: Root, Left, Right
            sb.append(node.val).append(",");
            serializeHelper(node.left, sb);
            serializeHelper(node.right, sb);
        }

        // Decodes your encoded data to tree
        public TreeNode deserialize(String data) {
            String[] values = data.split(",");
            Queue<String> queue = new LinkedList<>(Arrays.asList(values));
            return deserializeHelper(queue);
        }

        private TreeNode deserializeHelper(Queue<String> queue) {
            String value = queue.poll();

            if ("null".equals(value)) {
                return null;
            }

            TreeNode node = new TreeNode(Integer.parseInt(value));
            node.left = deserializeHelper(queue);
            node.right = deserializeHelper(queue);

            return node;
        }
    }
}
