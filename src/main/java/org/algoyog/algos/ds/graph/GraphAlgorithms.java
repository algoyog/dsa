package org.algoyog.algos.ds.graph;

import java.util.*;

/**
 * Graph Algorithms Implementation
 * Covers common graph traversal and pathfinding algorithms
 */
public class GraphAlgorithms {

    /**
     * Graph representation using adjacency list
     */
    public static class Graph {
        private int V; // Number of vertices
        private List<Integer>[] adjList; // Adjacency list

        @SuppressWarnings("unchecked")
        public Graph(int v) {
            this.V = v;
            adjList = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        // Add edge from u to v
        public void addEdge(int u, int v) {
            adjList[u].add(v);
        }

        // Add edge for undirected graph (add both directions)
        public void addUndirectedEdge(int u, int v) {
            adjList[u].add(v);
            adjList[v].add(u);
        }

        public List<Integer>[] getAdjList() {
            return adjList;
        }

        public int getVertexCount() {
            return V;
        }
    }

    /**
     * Breadth-First Search (BFS)
     * Time Complexity: O(V + E) where V is vertices and E is edges
     * Space Complexity: O(V) for queue and visited array
     */
    public void bfs(Graph graph, int start) {
        boolean[] visited = new boolean[graph.getVertexCount()];
        Queue<Integer> queue = new LinkedList<>();

        // Mark the starting vertex as visited and enqueue it
        visited[start] = true;
        queue.offer(start);

        System.out.println("BFS traversal starting from vertex " + start + ":");

        while (!queue.isEmpty()) {
            // Dequeue a vertex and print it
            int current = queue.poll();
            System.out.print(current + " ");

            // Get all adjacent vertices of the dequeued vertex
            // If an adjacent vertex is not visited, mark it visited and enqueue it
            for (int neighbor : graph.getAdjList()[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth-First Search (DFS)
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for recursion stack and visited array
     */
    public void dfs(Graph graph, int start) {
        boolean[] visited = new boolean[graph.getVertexCount()];
        System.out.println("DFS traversal starting from vertex " + start + ":");
        dfsUtil(graph, start, visited);
        System.out.println();
    }

    private void dfsUtil(Graph graph, int vertex, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[vertex] = true;
        System.out.print(vertex + " ");

        // Recur for all adjacent vertices
        for (int neighbor : graph.getAdjList()[vertex]) {
            if (!visited[neighbor]) {
                dfsUtil(graph, neighbor, visited);
            }
        }
    }

    /**
     * Dijkstra's Algorithm for Single Source Shortest Path
     * Time Complexity: O((V + E) log V) with priority queue
     * Space Complexity: O(V)
     */
    public static class WeightedGraph {
        private int V;
        private List<int[]>[] adjList; // [neighbor, weight]

        @SuppressWarnings("unchecked")
        public WeightedGraph(int v) {
            this.V = v;
            adjList = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        // Add weighted edge from u to v with weight w
        public void addEdge(int u, int v, int w) {
            adjList[u].add(new int[]{v, w});
        }

        // Add weighted edge for undirected graph
        public void addUndirectedEdge(int u, int v, int w) {
            adjList[u].add(new int[]{v, w});
            adjList[v].add(new int[]{u, w});
        }

        public List<int[]>[] getAdjList() {
            return adjList;
        }

        public int getVertexCount() {
            return V;
        }
    }

    public int[] dijkstra(WeightedGraph graph, int start) {
        int V = graph.getVertexCount();
        int[] dist = new int[V]; // Distance array
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Min heap based on distances
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [vertex, distance]
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int distance = current[1];

            // If current distance is greater than stored, skip
            if (distance > dist[u]) continue;

            // Check all neighbors
            for (int[] edge : graph.getAdjList()[u]) {
                int v = edge[0]; // neighbor
                int weight = edge[1]; // edge weight

                // If we found a shorter path to v through u
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        return dist;
    }

    /**
     * Detect Cycle in Directed Graph using DFS
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for recursion stack and visited arrays
     */
    public boolean hasCycle(Graph graph) {
        int V = graph.getVertexCount();
        boolean[] visited = new boolean[V];
        boolean[] recursionStack = new boolean[V];

        // Check for cycle in all components
        for (int i = 0; i < V; i++) {
            if (hasCycleUtil(graph, i, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleUtil(Graph graph, int vertex, boolean[] visited, boolean[] recursionStack) {
        // Mark current node as visited and add to recursion stack
        if (recursionStack[vertex]) return true;
        if (visited[vertex]) return false;

        visited[vertex] = true;
        recursionStack[vertex] = true;

        // Check all neighbors
        for (int neighbor : graph.getAdjList()[vertex]) {
            if (hasCycleUtil(graph, neighbor, visited, recursionStack)) {
                return true;
            }
        }

        // Remove vertex from recursion stack when done
        recursionStack[vertex] = false;
        return false;
    }

    /**
     * Topological Sort
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for stack and visited array
     */
    public int[] topologicalSort(Graph graph) {
        int V = graph.getVertexCount();
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        // Call the recursive helper function for all vertices
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(graph, i, visited, stack);
            }
        }

        // Pop all vertices from stack for topological order
        int[] result = new int[V];
        for (int i = 0; i < V; i++) {
            result[i] = stack.pop();
        }

        return result;
    }

    private void topologicalSortUtil(Graph graph, int vertex, boolean[] visited, Stack<Integer> stack) {
        // Mark the current node as visited
        visited[vertex] = true;

        // Recur for all adjacent vertices
        for (int neighbor : graph.getAdjList()[vertex]) {
            if (!visited[neighbor]) {
                topologicalSortUtil(graph, neighbor, visited, stack);
            }
        }

        // Push current vertex to stack after all its adjacent vertices are in stack
        stack.push(vertex);
    }
}
