# Union-Find (Disjoint Set) Data Structure

## Overview

The Union-Find (or Disjoint Set) data structure is designed to track a set of elements partitioned into multiple non-overlapping (disjoint) subsets. It provides near-constant time operations to:

1. Determine which subset an element belongs to
2. Merge two subsets into a single subset

This data structure is particularly useful for problems that involve grouping elements and determining if two elements belong to the same group, such as finding connected components in a graph or detecting cycles.

## Operations

### Core Operations

1. **MakeSet(x)**: Creates a new set containing only element x
2. **Find(x)**: Returns a representative element (usually the root) of the set containing x
3. **Union(x, y)**: Merges the sets containing elements x and y

### Optimization Techniques

1. **Path Compression**: Flattens the structure of the tree during find operations by connecting all traversed nodes directly to the root
2. **Union by Rank/Size**: Attaches the smaller tree to the root of the larger tree to minimize tree height

## Implementation

```java
public class DisjointSet {
    private int[] parent;
    private int[] rank; // Used for union by rank
    private int count; // Number of disjoint sets

    // Initialize with n elements (0 to n-1)
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;

        // Each element starts in its own set
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            // Path compression: make every examined node point to the root
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union with rank
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
            // If ranks are the same, make one the root and increment its rank
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        count--; // Decrease the number of disjoint sets
    }

    // Check if two elements are in the same set
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    // Get the number of disjoint sets
    public int getCount() {
        return count;
    }
}
```

## Time and Space Complexity

| Operation | Time Complexity | 
|-----------|------------------|
| MakeSet | O(1) |
| Find (naive) | O(n) worst case |
| Find (with path compression) | O(α(n)) - effectively constant |
| Union (naive) | O(n) worst case |
| Union (with rank) | O(α(n)) - effectively constant |

Where α(n) is the inverse Ackermann function, which grows extremely slowly and can be considered a constant for all practical purposes.

**Space Complexity**: O(n) where n is the number of elements

## Applications

1. **Connected Components in Undirected Graphs**
   - Determine if two vertices are in the same component
   - Count the number of connected components

2. **Cycle Detection in Undirected Graphs**
   - A cycle exists if we try to union two vertices that are already connected

3. **Minimum Spanning Tree Algorithms**
   - Kruskal's algorithm uses Union-Find to build an MST

4. **Grid/Matrix Problems**
   - Finding islands in a 2D grid
   - Determining if a path exists between two cells

5. **Network Connectivity**
   - Tracking which components of a network are connected

6. **Image Processing**
   - Connected component labeling in binary images

## Example: Number of Islands

```java
public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) return 0;

    int m = grid.length;
    int n = grid[0].length;
    int count = 0;

    // Count initial land cells
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == '1') {
                count++;
            }
        }
    }

    DisjointSet ds = new DisjointSet(m * n);

    // Directions: right and down
    int[][] dirs = {{0, 1}, {1, 0}};

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == '1') {
                int current = i * n + j;

                // Check adjacent cells
                for (int[] dir : dirs) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    if (ni < m && nj < n && grid[ni][nj] == '1') {
                        int neighbor = ni * n + nj;

                        // If not already connected, union and decrease island count
                        if (!ds.isConnected(current, neighbor)) {
                            ds.union(current, neighbor);
                            count--;
                        }
                    }
                }
            }
        }
    }

    return count;
}
```

## Advantages and Limitations

### Advantages

- Extremely efficient operations (near constant time) with optimizations
- Simple implementation and low memory overhead
- Works well with offline algorithms (where all operations are known in advance)

### Limitations

- Doesn't support efficient deletion operations
- Difficult to enumerate all elements in a specific set
- Not suitable for dynamic applications requiring frequent set membership changes
- Primarily designed for undirected relationships

## Variants and Extensions

- **Weighted Union-Find**: Keeps track of the size of each set
- **Persistent Union-Find**: Supports undoing operations
- **Dynamic Connectivity**: Handles both connections and disconnections
- **Randomized Union-Find**: Uses randomization for better average-case performance

The Union-Find data structure remains one of the most elegant and efficient solutions for managing disjoint sets, especially for applications involving connectivity problems.
