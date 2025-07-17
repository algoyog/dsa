package org.algoyog.algos.ds.union_find;

/**
 * Disjoint Set (Union-Find) Data Structure
 * Used for grouping elements into disjoint sets and efficiently checking
 * if two elements belong to the same set
 * 
 * Optimizations:
 * 1. Path Compression: Flattens the tree when find is called
 * 2. Union by Rank: Keeps the tree balanced by attaching smaller tree to larger tree
 */
public class DisjointSet {
    private int[] parent;
    private int[] rank;
    private int count; // Number of components/sets

    /**
     * Initialize with n elements where each element is in its own set
     * Time Complexity: O(n)
     * Space Complexity: O(n)
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
     * Time Complexity: O(α(n)) - effectively constant time
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
     * Time Complexity: O(α(n)) - effectively constant time
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
     * Time Complexity: O(α(n)) - effectively constant time
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * Get the number of disjoint sets
     * Time Complexity: O(1)
     */
    public int getCount() {
        return count;
    }

    /**
     * Example application: Number of Islands problem
     * Illustrates how to use Union-Find for grid problems
     * Time Complexity: O(m*n) where m, n are grid dimensions
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int numIslands = 0;

        // Directions: right and down
        int[][] directions = {{0, 1}, {1, 0}};

        DisjointSet ds = new DisjointSet(m * n);

        // First pass: count all land cells and perform initial unions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    numIslands++; // Each land cell is initially its own island

                    // Check adjacent cells (right and down)
                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];

                        // If adjacent cell is within bounds and is land
                        if (ni < m && nj < n && grid[ni][nj] == '1') {
                            // Convert 2D coordinates to 1D index
                            int current = i * n + j;
                            int neighbor = ni * n + nj;

                            // If not already connected, union and decrement island count
                            if (!ds.isConnected(current, neighbor)) {
                                ds.union(current, neighbor);
                                numIslands--; // Merging two islands decreases count
                            }
                        }
                    }
                }
            }
        }

        return numIslands;
    }
}
