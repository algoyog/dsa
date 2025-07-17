package org.algoyog.algos.ds.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie (Prefix Tree) Implementation
 * 
 * A Trie is a tree-like data structure that is used to store a dynamic set of strings.
 * Tries are efficient for the following operations:
 * 1. Finding all words with a common prefix
 * 2. Searching for a word in a dictionary
 * 3. Autocomplete/prefix-based searches
 */
public class Trie {

    private TrieNode root;

    /**
     * TrieNode class for Trie implementation
     */
    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    /**
     * Initialize Trie data structure
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Insert a word into the trie
     * Time Complexity: O(m) where m is the length of the word
     * Space Complexity: O(m) in worst case
     */
    public void insert(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            // If the character is not in the current node's children, add it
            current.children.putIfAbsent(c, new TrieNode());
            // Move to the next node
            current = current.children.get(c);
        }

        // Mark the end of the word
        current.isEndOfWord = true;
    }

    /**
     * Search for a word in the trie
     * Time Complexity: O(m) where m is the length of the word
     * Space Complexity: O(1)
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        // Word exists if node is not null and it represents the end of a word
        return node != null && node.isEndOfWord;
    }

    /**
     * Check if there is any word in the trie that starts with the given prefix
     * Time Complexity: O(m) where m is the length of the prefix
     * Space Complexity: O(1)
     */
    public boolean startsWith(String prefix) {
        // If we can find the prefix, then there is at least one word with this prefix
        return searchPrefix(prefix) != null;
    }

    /**
     * Helper method to search for a prefix in the trie
     * Returns the node at the end of the prefix, or null if not found
     */
    private TrieNode searchPrefix(String prefix) {
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            // If the character is not in the current node's children, prefix doesn't exist
            if (!current.children.containsKey(c)) {
                return null;
            }
            // Move to the next node
            current = current.children.get(c);
        }

        return current;
    }

    /**
     * Delete a word from the trie
     * Time Complexity: O(m) where m is the length of the word
     * Space Complexity: O(1)
     */
    public void delete(String word) {
        delete(root, word, 0);
    }

    /**
     * Recursive helper method for deletion
     * Returns true if the node can be deleted (i.e., has no children and is not end of another word)
     */
    private boolean delete(TrieNode current, String word, int index) {
        // Base case: we've processed all characters
        if (index == word.length()) {
            // If this is not the end of a word, it doesn't exist in our trie
            if (!current.isEndOfWord) {
                return false;
            }

            // Mark that this is no longer the end of a word
            current.isEndOfWord = false;

            // This node can be deleted if it has no children
            return current.children.isEmpty();
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        // If the character doesn't exist in the trie, the word doesn't exist
        if (node == null) {
            return false;
        }

        // Recursively check if child can be deleted
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        // If we should delete the child node
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            // Current node can be deleted if it's not the end of another word and has no other children
            return !current.isEndOfWord && current.children.isEmpty();
        }

        return false;
    }

    /**
     * Example application: Word Search II (Find all words in a board from a dictionary)
     * 
     * This is a conceptual implementation to demonstrate how a Trie would be used
     * for a complex problem like finding all valid words in a board game.
     */
    public void wordSearchExample() {
        System.out.println("Word Search II using Trie - Conceptual Steps:");
        System.out.println("1. Build a trie with all words from the dictionary");
        System.out.println("2. Perform DFS from each cell in the board:");
        System.out.println("   a. Keep track of the current path/word");
        System.out.println("   b. Use the trie to prune paths that cannot form valid words");
        System.out.println("   c. When reaching a node marked as end of word, add word to results");
        System.out.println("3. Return the list of all found words");
        System.out.println("Time Complexity: O(M * 4^L) where M is the size of the board and L is max word length");
    }
}
