package org.algoyog.algos.ds.union_find;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DisjointSetTest {

    @Test
    public void testInitialization() {
        DisjointSet ds = new DisjointSet(5);
        assertEquals(5, ds.getCount());

        // Each element should be in its own set initially
        for (int i = 0; i < 5; i++) {
            assertEquals(i, ds.find(i));
        }
    }

    @Test
    public void testUnionFind() {
        DisjointSet ds = new DisjointSet(5);

        // Initially all elements are in separate sets
        assertFalse(ds.isConnected(0, 1));
        assertFalse(ds.isConnected(2, 3));

        // Union elements 0 and 1
        ds.union(0, 1);
        assertTrue(ds.isConnected(0, 1));
        assertEquals(4, ds.getCount());

        // Union elements 2 and 3
        ds.union(2, 3);
        assertTrue(ds.isConnected(2, 3));
        assertEquals(3, ds.getCount());

        // Union elements 0 and 2 (connecting two sets)
        ds.union(0, 2);
        assertTrue(ds.isConnected(0, 2));
        assertTrue(ds.isConnected(1, 3)); // Transitive connection
        assertEquals(2, ds.getCount());

        // Union elements already in the same set
        ds.union(1, 3);
        assertEquals(2, ds.getCount()); // Count shouldn't change
    }

    @Test
    public void testPathCompression() {
        DisjointSet ds = new DisjointSet(5);

        // Create a chain: 0 -> 1 -> 2 -> 3 -> 4
        ds.union(0, 1);
        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(3, 4);

        // All should be in the same set
        assertTrue(ds.isConnected(0, 4));
        assertEquals(1, ds.getCount());

        // Find operation should compress paths
        int root = ds.find(0);
        assertEquals(root, ds.find(4)); // Both should have same root
    }

    @Test
    public void testNumIslands() {
        // Test case 1: No islands
        char[][] grid1 = {
            {'0', '0', '0'},
            {'0', '0', '0'},
            {'0', '0', '0'}
        };
        assertEquals(0, DisjointSet.numIslands(grid1));

        // Test case 2: One island
        char[][] grid2 = {
            {'1', '1', '1'},
            {'1', '1', '1'},
            {'1', '1', '1'}
        };
        assertEquals(1, DisjointSet.numIslands(grid2));

        // Test case 3: Multiple islands
        char[][] grid3 = {
            {'1', '0', '1'},
            {'0', '1', '0'},
            {'1', '0', '1'}
        };
        assertEquals(5, DisjointSet.numIslands(grid3));

        // Test case 4: Complex island shapes
        char[][] grid4 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, DisjointSet.numIslands(grid4));

        // Test case 5: Empty grid
        char[][] grid5 = {};
        assertEquals(0, DisjointSet.numIslands(grid5));
    }
}
