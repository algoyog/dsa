package org.algoyog.algos.ds.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class BinaryTreeTest {

    private BinaryTree tree;
    private BinaryTree.TreeNode root;

    @BeforeEach
    public void setUp() {
        tree = new BinaryTree();

        // Create a sample binary tree:
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        root = new BinaryTree.TreeNode(1);
        root.left = new BinaryTree.TreeNode(2);
        root.right = new BinaryTree.TreeNode(3);
        root.left.left = new BinaryTree.TreeNode(4);
        root.left.right = new BinaryTree.TreeNode(5);
    }

    @Test
    public void testInorderTraversal() {
        List<Integer> expected = Arrays.asList(4, 2, 5, 1, 3);
        List<Integer> result = tree.inorderTraversal(root);
        assertEquals(expected, result);

        // Test with null root
        result = tree.inorderTraversal(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testPreorderTraversal() {
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3);
        List<Integer> result = tree.preorderTraversal(root);
        assertEquals(expected, result);

        // Test with null root
        result = tree.preorderTraversal(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testPostorderTraversal() {
        List<Integer> expected = Arrays.asList(4, 5, 2, 3, 1);
        List<Integer> result = tree.postorderTraversal(root);
        assertEquals(expected, result);

        // Test with null root
        result = tree.postorderTraversal(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testLevelOrderTraversal() {
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(2, 3),
            Arrays.asList(4, 5)
        );
        List<List<Integer>> result = tree.levelOrder(root);
        assertEquals(expected, result);

        // Test with null root
        result = tree.levelOrder(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMaxDepth() {
        assertEquals(3, tree.maxDepth(root));

        // Test with null root
        assertEquals(0, tree.maxDepth(null));

        // Test with single node tree
        BinaryTree.TreeNode singleNode = new BinaryTree.TreeNode(1);
        assertEquals(1, tree.maxDepth(singleNode));
    }

    @Test
    public void testIsBalanced() {
        // Initial tree is balanced
        assertTrue(tree.isBalanced(root));

        // Add nodes to make it unbalanced
        root.left.left.left = new BinaryTree.TreeNode(6);
        root.left.left.left.left = new BinaryTree.TreeNode(7);
        assertFalse(tree.isBalanced(root));

        // Test with null root (should be balanced)
        assertTrue(tree.isBalanced(null));
    }

    @Test
    public void testLowestCommonAncestor() {
        // LCA of nodes 4 and 5 should be 2
        BinaryTree.TreeNode p = root.left.left;  // Node with value 4
        BinaryTree.TreeNode q = root.left.right; // Node with value 5
        BinaryTree.TreeNode lca = tree.lowestCommonAncestor(root, p, q);
        assertEquals(2, lca.val);

        // LCA of nodes 4 and 3 should be 1
        p = root.left.left;  // Node with value 4
        q = root.right;      // Node with value 3
        lca = tree.lowestCommonAncestor(root, p, q);
        assertEquals(1, lca.val);

        // LCA of node and itself should be the node
        p = root.left; // Node with value 2
        lca = tree.lowestCommonAncestor(root, p, p);
        assertEquals(p.val, lca.val);
    }

    @Test
    public void testSerializeDeserialize() {
        BinaryTree.Codec codec = tree.new Codec();

        // Serialize and then deserialize
        String serialized = codec.serialize(root);
        BinaryTree.TreeNode deserialized = codec.deserialize(serialized);

        // Verify the trees are identical by checking traversals
        List<Integer> originalInorder = tree.inorderTraversal(root);
        List<Integer> deserializedInorder = tree.inorderTraversal(deserialized);
        assertEquals(originalInorder, deserializedInorder);

        List<Integer> originalPreorder = tree.preorderTraversal(root);
        List<Integer> deserializedPreorder = tree.preorderTraversal(deserialized);
        assertEquals(originalPreorder, deserializedPreorder);

        // Test with null tree
        serialized = codec.serialize(null);
        deserialized = codec.deserialize(serialized);
        assertNull(deserialized);
    }
}
