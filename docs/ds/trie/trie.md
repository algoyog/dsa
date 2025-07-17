# Trie (Prefix Tree)

## Problem Statement

A Trie, also called a prefix tree, addresses the efficient storage and retrieval of strings from a large set. It specifically excels at:

1. Finding all words with a common prefix
2. Checking if a string exists in a collection
3. Autocomplete and prefix-based searching
4. Spell checking and word validation

The name "trie" comes from "retrieval" and is pronounced "try" or "tree."

## Data Structure Strategy

A Trie organizes characters of strings in a tree-like structure where:

1. Each node represents a single character
2. Paths from the root to a node form prefixes of strings
3. Paths from the root to marked nodes (end-of-word) represent complete strings in the set
4. Children of a node are typically stored in a hashmap, array, or linked structure
5. Common prefixes are shared, saving space when storing strings with similar beginnings

This structure enables efficient prefix operations and avoids the need to examine every string in the collection.

## Mathematical Foundation

The Trie data structure has several important mathematical properties:

- For a set of strings with average length m, a trie has O(m * n) nodes in the worst case, where n is the number of strings
- The height of a trie is limited by the length of the longest string
- The maximum number of children per node is the size of the alphabet (e.g., 26 for lowercase English letters)
- The time complexity for operations is proportional to the length of the string being processed, not the size of the entire dataset

From a formal language perspective, a trie represents a finite set of strings, and each path from the root to an end-of-word node corresponds to a string in that set.

## Implementation Guide

### Node Structure

```java
private static class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}
```

### Basic Operations

#### Initialization

```java
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Other methods...
}
```

#### Insertion

```java
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
```

#### Search

```java
public boolean search(String word) {
    TrieNode node = searchPrefix(word);
    // Word exists if node is not null and it represents the end of a word
    return node != null && node.isEndOfWord;
}

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
```

#### Prefix Search

```java
public boolean startsWith(String prefix) {
    // If we can find the prefix, then there is at least one word with this prefix
    return searchPrefix(prefix) != null;
}
```

#### Deletion

```java
public void delete(String word) {
    delete(root, word, 0);
}

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
```

## Testing Theory

Effective testing for a Trie implementation should include:

1. **Basic operations**:
   - Inserting strings
   - Searching for existing strings
   - Searching for non-existent strings
   - Checking prefix existence
   - Deleting strings

2. **Edge cases**:
   - Empty strings
   - Very long strings
   - Single character strings
   - Strings with special characters (if supported)
   - Case sensitivity (if relevant)

3. **Complex scenarios**:
   - Inserting strings with common prefixes
   - Deleting a string that is a prefix of another string
   - Inserting/deleting/searching large numbers of strings
   - Strings that are prefixes of other strings

4. **Performance testing**:
   - Measuring operation times with increasing dataset sizes
   - Memory consumption as the trie grows

## Unique Properties

- **Prefix sharing**: Common prefixes are stored only once, saving space
- **Predictable performance**: Operations depend on string length, not dataset size
- **Lexicographical ordering**: Strings are inherently stored in alphabetical order
- **Incremental search**: Efficiently supports searching as characters are typed
- **Character-by-character processing**: Works well for streaming or partial input

## When to Use Tries

Tries are particularly useful when:

- You need to store a dictionary of words for quick lookup
- Prefix matching is a common operation
- You're implementing autocomplete functionality
- You need to check if strings exist in a set frequently
- Memory usage for storing strings can be optimized by sharing prefixes
- Lexicographical ordering of strings is important
- You're working with a well-defined character set (e.g., letters, digits)

## Real-world Applications

1. **Autocomplete Systems**: Suggesting words as users type in search engines, messaging apps, and code editors

2. **Spell Checkers**: Efficiently verifying if words exist in a dictionary

3. **IP Routing**: Using tries to store routing tables for efficient lookups

4. **Contact Lists**: Fast lookup and prefix-based searching in address books

5. **Word Games**: Validating words in games like Scrabble or Boggle

6. **DNA Sequence Analysis**: Storing and searching DNA sequences efficiently

7. **Text Editors**: Supporting find/replace operations and syntax highlighting

8. **Natural Language Processing**: For tasks like text prediction and word frequency analysis

## Complexity Analysis

- **Insertion**: O(m) where m is the length of the string
- **Search**: O(m) where m is the length of the string
- **Prefix Search**: O(p) where p is the length of the prefix
- **Deletion**: O(m) where m is the length of the string
- **Space Complexity**: O(ALPHABET_SIZE * m * n) where n is the number of strings

## Variants and Optimizations

### Compressed Trie (Radix Tree)

A space-optimized version that merges nodes with single children:

```java
public class CompressedTrieNode {
    Map<String, CompressedTrieNode> children;
    boolean isEndOfWord;

    public CompressedTrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}
```

### Ternary Search Tree

A more memory-efficient variant with three links per node:

```java
public class TSTNode {
    char character;
    TSTNode left, middle, right;
    boolean isEndOfWord;

    public TSTNode(char c) {
        character = c;
        isEndOfWord = false;
    }
}
```

### Array-based Implementation

For fixed character sets like lowercase English letters:

```java
public class ArrayTrieNode {
    ArrayTrieNode[] children;
    boolean isEndOfWord;

    public ArrayTrieNode() {
        // For lowercase English letters
        children = new ArrayTrieNode[26];
        isEndOfWord = false;
    }
}
```

## Advanced Applications

### Word Suggestions/Autocomplete

```java
public List<String> suggestWords(String prefix, int limit) {
    List<String> suggestions = new ArrayList<>();
    TrieNode prefixNode = searchPrefix(prefix);

    if (prefixNode != null) {
        collectWords(prefixNode, prefix, suggestions, limit);
    }

    return suggestions;
}

private void collectWords(TrieNode node, String prefix, List<String> suggestions, int limit) {
    if (suggestions.size() >= limit) return;

    if (node.isEndOfWord) {
        suggestions.add(prefix);
    }

    for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
        collectWords(entry.getValue(), prefix + entry.getKey(), suggestions, limit);
    }
}
```

### Solving Word Search Problems

Tries are extremely useful for solving problems like finding all valid words in a board game:

```java
public List<String> findWords(char[][] board, String[] words) {
    // Build trie from the dictionary
    Trie trie = new Trie();
    for (String word : words) {
        trie.insert(word);
    }

    // Find words in the board using the trie
    Set<String> result = new HashSet<>();
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] visited = new boolean[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            dfs(board, visited, i, j, trie.root, "", result);
        }
    }

    return new ArrayList<>(result);
}

private void dfs(char[][] board, boolean[][] visited, int i, int j, TrieNode node, String path, Set<String> result) {
    // Check boundaries and if already visited
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
        return;
    }

    char currentChar = board[i][j];
    if (!node.children.containsKey(currentChar)) {
        return;
    }

    visited[i][j] = true;
    node = node.children.get(currentChar);
    path += currentChar;

    if (node.isEndOfWord) {
        result.add(path);
    }

    // Explore in all four directions
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    for (int[] dir : directions) {
        dfs(board, visited, i + dir[0], j + dir[1], node, path, result);
    }

    // Backtrack
    visited[i][j] = false;
}
```

## Limitations and Considerations

- **Memory Overhead**: Basic tries can consume significant memory for large datasets
- **Implementation Complexity**: More complex to implement than simple data structures like arrays or hash maps
- **Character Set Dependency**: Efficiency depends on the size of the character set
- **Non-string Data**: Primarily designed for string operations, less suitable for other data types
- **Serialization Challenges**: Can be complex to serialize/deserialize efficiently

By understanding these characteristics and considerations, developers can effectively leverage tries to solve string-related problems with optimal performance.
