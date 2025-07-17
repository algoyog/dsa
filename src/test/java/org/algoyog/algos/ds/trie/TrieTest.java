package org.algoyog.algos.ds.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {

    private Trie trie;

    @BeforeEach
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void testInsertAndSearch() {
        // Insert words
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("banana");

        // Test search for existing words
        assertTrue(trie.search("apple"));
        assertTrue(trie.search("app"));
        assertTrue(trie.search("application"));
        assertTrue(trie.search("banana"));

        // Test search for non-existing words
        assertFalse(trie.search("ap"));
        assertFalse(trie.search("appli"));
        assertFalse(trie.search("apples"));
        assertFalse(trie.search("ban"));
        assertFalse(trie.search("orange"));
    }

    @Test
    public void testStartsWith() {
        // Insert words
        trie.insert("apple");
        trie.insert("application");
        trie.insert("banana");

        // Test startsWith for existing prefixes
        assertTrue(trie.startsWith("a"));
        assertTrue(trie.startsWith("ap"));
        assertTrue(trie.startsWith("app"));
        assertTrue(trie.startsWith("appl"));
        assertTrue(trie.startsWith("apple"));
        assertTrue(trie.startsWith("ban"));

        // Test startsWith for non-existing prefixes
        assertFalse(trie.startsWith("b"));
        assertFalse(trie.startsWith("or"));
        assertFalse(trie.startsWith("applet"));
    }

    @Test
    public void testDelete() {
        // Insert words
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");

        // Delete a word and verify
        trie.delete("app");
        assertFalse(trie.search("app"));
        assertTrue(trie.search("apple")); // Should still exist
        assertTrue(trie.search("application")); // Should still exist
        assertTrue(trie.startsWith("app")); // Prefix should still exist

        // Delete another word and verify
        trie.delete("apple");
        assertFalse(trie.search("apple"));
        assertTrue(trie.search("application")); // Should still exist
        assertTrue(trie.startsWith("app")); // Prefix should still exist

        // Delete the last word with prefix "app"
        trie.delete("application");
        assertFalse(trie.search("application"));
        assertFalse(trie.startsWith("app")); // Prefix should no longer exist
    }

    @Test
    public void testDeleteNonExistingWord() {
        // Insert words
        trie.insert("apple");

        // Try to delete a word that doesn't exist
        trie.delete("app");
        assertTrue(trie.search("apple")); // Should still exist

        // Try to delete a word with a prefix that doesn't exist
        trie.delete("banana");
        assertTrue(trie.search("apple")); // Should still exist

        // Delete the existing word and verify
        trie.delete("apple");
        assertFalse(trie.search("apple"));
    }

    @Test
    public void testEmptyTrie() {
        // Test search and startsWith on empty trie
        assertFalse(trie.search(""));
        assertFalse(trie.search("any"));
        assertFalse(trie.startsWith(""));
        assertFalse(trie.startsWith("any"));

        // Insert empty string and test
        trie.insert("");
        assertTrue(trie.search(""));
        assertFalse(trie.search("any"));
    }

    @Test
    public void testCaseSensitivity() {
        // Insert lowercase word
        trie.insert("apple");

        // Search should be case sensitive
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("Apple"));
        assertFalse(trie.search("APPLE"));

        // StartsWith should also be case sensitive
        assertTrue(trie.startsWith("app"));
        assertFalse(trie.startsWith("App"));
    }
}
