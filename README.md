# DSA Implementations.

This repository contains implementations of common algorithms and data structures.

## Project Structure

The code is organized into packages by concept:

### Data Structures

- **Arrays**
  - Two Pointers Technique
  - Sliding Window Technique

- **Heaps**
  - Priority Queue operations
  - K-th Largest Element
  - Merge K Sorted Lists
  - Find Median from Data Stream

- **Trees**
  - Binary Tree Traversals (Inorder, Preorder, Postorder, Level Order)
  - Binary Tree Operations (Height, Balance, LCA)
  - Tree Serialization

- **Graphs**
  - Graph Representations
  - Depth-First Search (DFS)
  - Breadth-First Search (BFS)
  - Dijkstra's Algorithm
  - Cycle Detection
  - Topological Sort

- **Trie (Prefix Tree)**
  - Basic Operations (Insert, Search, StartsWith)
  - Word Search Applications

- **Disjoint Set (Union-Find)**
  - Path Compression
  - Union by Rank
  - Connected Components

### Algorithms

- **Dynamic Programming**
  - Fibonacci
  - Knapsack Problem
  - Coin Change
  - Longest Common Subsequence
  - Longest Increasing Subsequence
  - Edit Distance

- **Sorting**
  - Merge Sort
  - Quick Sort
  - Heap Sort
  - Counting Sort
  - Radix Sort
  - Bucket Sort

- **Searching**
  - Linear Search
  - Binary Search
  - Binary Search Variations
  - Search in Rotated Sorted Array
  - Find Peak Element

### Design Patterns

- **LRU Cache**
  - Constant Time Operations
  - Doubly Linked List + HashMap Implementation

- **Min Stack**
  - Constant Time Operations
  - Two-Stack Implementation
  - Single-Stack with Pairs Implementation

## Time and Space Complexity

Each implementation includes detailed comments about:

- Time complexity analysis
- Space complexity analysis
- Algorithm characteristics
- Edge cases handling

## How to Use

1. Clone the repository
2. Navigate to the specific algorithm or data structure you want to study
3. Each class contains detailed comments explaining the implementation
4. Run the Main class to see a summary of available implementations

## Compilation

This project uses Java 21 and Maven. To compile and run:

```
mvn clean package
java -jar target/dsa-1.0-SNAPSHOT.jar
```

