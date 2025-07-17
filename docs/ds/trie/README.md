# Trie (Prefix Tree)

## Problem Statement

A Trie, also called a prefix tree, is a specialized tree-like data structure that efficiently stores and retrieves keys in a dataset of strings. Unlike binary search trees, Tries allow for fast prefix lookups and are ideal for implementing dictionaries, spell checkers, autocomplete features, and IP routing tables.

## Data Structure Design

A Trie consists of:

1. **Nodes**: Each node contains a map/array of child nodes, one for each possible character
2. **End-of-word markers**: Flags indicating where words end
3. **Root**: The starting point, typically containing no character itself

## Mathematical Foundation

The time complexity for operations in a Trie depends on the key length (m) rather than the number of keys (n), making it efficient for certain string operations. The space complexity is related to the total characters across all keys, with optimizations possible for common prefixes.

## Implementation Details

```java
public class Trie {

    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }

        current.isEndOfWord = true;
    }

    // Search for a word in the trie
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }

    // Check if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    // Helper method to search for a prefix in the trie
    private TrieNode searchPrefix(String prefix) {
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return null;
            }
            current = current.children.get(c);
        }

        return current;
    }

    // Delete a word from the trie
    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;
            return current.children.isEmpty();
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        if (node == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return !current.isEndOfWord && current.children.isEmpty();
        }

        return false;
    }
}
```

## Operations

1. **Insert**: Add a word to the trie
2. **Search**: Check if a word exists in the trie
3. **StartsWith**: Check if any word starts with a given prefix
4. **Delete**: Remove a word from the trie

## Testing Methodology

Test Trie implementations with various scenarios:
- Empty strings
- Single character strings
- Words with common prefixes
- Case sensitivity handling
- Special characters and numbers
- Deletion with shared prefixes
- Very long strings

## Unique Properties

- **Prefix Matching**: Efficiently finds all words with a common prefix
- **Lexicographical Ordering**: Words are naturally ordered
- **Space Efficiency**: Shares common prefixes among words
- **Predictable Performance**: Operations depend on key length, not dataset size

## Use Cases

1. **Autocomplete Systems**: Suggest completions as users type
2. **Spell Checkers**: Verify if words exist in a dictionary
3. **IP Routing Tables**: Store and look up network addresses
4. **Word Games**: Find valid words on a board (e.g., Boggle)
5. **Text Mining**: Extract patterns from text data

## Real-world Applications

- **Search Engines**: Query autocompletion
- **Dictionaries**: Fast word lookup
- **Contact Lists**: Phone number or name lookups
- **Network Routers**: IP address routing
- **Natural Language Processing**: Word frequency analysis

## Complexity Analysis

| Operation   | Time Complexity | Space Complexity |
|-------------|----------------|------------------|
| Insert      | O(m)           | O(m)             |
| Search      | O(m)           | O(1)             |
| StartsWith  | O(m)           | O(1)             |
| Delete      | O(m)           | O(1)             |

Where m is the length of the key.

## Optimizations

1. **Array Instead of HashMap**: Use character index for direct access when alphabet is limited
2. **Compressed Trie**: Merge nodes with single children (also called Patricia Trie)
3. **Ternary Search Tree**: Combine trie and binary search tree for space efficiency
4. **Level Compression**: Reduce depth by increasing branching factor
5. **Path Compression**: Similar to Union-Find optimization

## Comparison with Other Data Structures

| Data Structure        | Search       | Insert       | Delete       | Space                | Key Advantage           |
|-----------------------|--------------|--------------|--------------|----------------------|-------------------------|
| Trie                  | O(m)         | O(m)         | O(m)         | O(ALPHABET_SIZE * n * m) | Fast prefix operations    |
| Hash Table            | O(1) avg     | O(1) avg     | O(1) avg     | O(n)                 | Fast key-value retrieval |
| Binary Search Tree    | O(log n) avg | O(log n) avg | O(log n) avg | O(n)                 | Ordered operations       |
| Red-Black Tree        | O(log n)     | O(log n)     | O(log n)     | O(n)                 | Guaranteed balancing     |

Where n is the number of keys and m is the length of the key.

## Limitations and Considerations

- **Memory Usage**: Can be memory-intensive for large datasets with few common prefixes
- **Character Set Size**: Performance depends on alphabet size
- **Case Sensitivity**: Requires careful handling for case-insensitive operations
- **Serialization**: More complex to serialize than other data structures
- **Delete Operation**: Complex to implement correctly
- **Not Suitable for**: Approximate string matching without modifications
