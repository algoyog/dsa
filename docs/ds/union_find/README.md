# Disjoint Set (Union-Find)

## Problem Statement

The Disjoint Set (Union-Find) data structure efficiently tracks a partition of a set into disjoint subsets. It provides near-constant time operations to check if two elements belong to the same subset and to unite two subsets. This structure is particularly useful for problems involving connected components in graphs, cycle detection, and kruskal's minimum spanning tree algorithm.

## Data Structure Design

A Disjoint Set data structure consists of:

1. **Parent Array**: Each element points to its parent (or itself if it's a representative)
2. **Rank/Size Array**: Used for optimization by attaching smaller trees to larger ones
3. **Operations**: MakeSet, Find, and Union

## Mathematical Foundation

The time complexity is O(α(n)) per operation, where α(n) is the inverse Ackermann function, which grows extremely slowly and is practically constant for all reasonable values of n. This near-constant time complexity is achieved through two key optimizations: path compression and union by rank/size.

## Implementation Details

```java
public class DisjointSet {
    private int[] parent;
    private int[] rank;
    private int count; // Number of components/sets

    /**
     * Initialize with n elements where each element is in its own set
     */
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;

        // Initially, each element is its own parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0; // Initial rank is 0
        }
    }

    /**
     * Find the root/representative of the set containing element x (with path compression)
     */
    public int find(int x) {
        if (parent[x] != x) {
            // Path compression: make every examined node point directly to the root
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * Union the sets containing elements x and y (with union by rank)
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return; // Already in the same set

        // Union by rank: attach smaller rank tree under root of higher rank tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // If ranks are same, make one as root and increment its rank
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        // Decrement the number of components/sets
        count--;
    }

    /**
     * Check if x and y are in the same set
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * Get the number of disjoint sets
     */
    public int getCount() {
        return count;
    }
}
```

## Operations

1. **MakeSet(x)**: Create a new set containing only element x (implicitly done in constructor)
2. **Find(x)**: Return the representative (root) of the set containing x
3. **Union(x, y)**: Merge the sets containing elements x and y
4. **IsConnected(x, y)**: Check if elements x and y belong to the same set

## Optimizations

### Path Compression

When performing `find(x)`, update the parent of each traversed node to point directly to the root. This flattens the tree structure, reducing future traversal time.

### Union by Rank/Size

When performing `union(x, y)`, attach the tree with smaller rank/size to the tree with larger rank/size. This keeps the tree balanced and shallow.

## Testing Methodology

Test Union-Find implementations with various scenarios:
- Single element operations
- Chain of unions (1-2, 2-3, 3-4, etc.)
- Tree-like union patterns
- Large number of elements
- Repeated finds on the same elements to test path compression
- Mixed operations sequence

## Unique Properties

- **Near-constant Time Operations**: O(α(n)) is practically constant
- **Space Efficient**: Requires only O(n) space
- **Online Algorithm**: Can process operations in sequence without knowing future operations
- **Persistent Structure**: Previous state can be preserved with copy-on-write

## Use Cases

1. **Kruskal's Minimum Spanning Tree Algorithm**: Track connected components
2. **Cycle Detection in Undirected Graphs**: Union edges; cycle exists if endpoints already connected
3. **Network Connectivity**: Determine if two nodes are connected
4. **Image Processing**: Connected component labeling
5. **Least Common Ancestor in Trees**: Tarjan's off-line algorithm

## Real-world Applications

- **Social Networks**: Friend circles/communities detection
- **Grid Percolation**: Monte Carlo simulation
- **Maze Generation**: Create perfect mazes
- **Computer Networks**: Network connectivity monitoring
- **Dynamic Graph Algorithms**: Maintain connected components

## Complexity Analysis

| Operation    | Time Complexity | Space Complexity |
|--------------|----------------|------------------|
| MakeSet(n)   | O(n)           | O(n)             |
| Find(x)      | O(α(n))        | O(1)             |
| Union(x, y)  | O(α(n))        | O(1)             |
| IsConnected(x, y) | O(α(n))    | O(1)             |

Where α(n) is the inverse Ackermann function, which is less than 5 for any practical value of n.

## Variations and Extensions

1. **Weighted Union-Find**: Attach smaller tree to larger tree by counting nodes
2. **Path Halving/Splitting**: Alternative to path compression with less recursion
3. **Persistent Union-Find**: Maintain history of operations
4. **Dynamic Union-Find**: Support for insertion and deletion of elements
5. **Union-Find with Undo**: Support for rolling back union operations

## Comparison with Other Data Structures

| Data Structure     | Find Operation | Union Operation | Space       | Key Advantage               |
|--------------------|----------------|----------------|-------------|-----------------------------|
| Union-Find         | O(α(n))        | O(α(n))        | O(n)        | Near-constant time operations|
| Adjacency List     | O(V+E)         | O(1)*          | O(V+E)      | Explicit graph representation|
| Tree               | O(h)           | O(h)           | O(n)        | Hierarchical representation  |

*Assuming direct access to the nodes to connect

## Limitations and Considerations

- **No Efficient Split Operation**: Cannot efficiently split a set
- **Element Range**: Elements are typically represented as integers in a contiguous range
- **Memory Allocation**: Fixed array size requires knowing the maximum number of elements in advance
- **No Direct Access to Set Contents**: Requires additional structures to list all elements in a set
- **Static Structure**: Basic implementation doesn't support dynamic addition/removal of elements
