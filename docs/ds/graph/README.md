# Graph Data Structures
# Graph Data Structures and Algorithms

## Overview

Graphs are versatile data structures that represent connections between objects. They consist of vertices (nodes) and edges that connect these vertices. Graphs can model a wide range of real-world scenarios, from social networks and maps to computer networks and dependency relationships.

## Types of Graphs

1. **Directed vs. Undirected**
   - **Directed Graph**: Edges have a direction
   - **Undirected Graph**: Edges have no direction (bidirectional)

2. **Weighted vs. Unweighted**
   - **Weighted Graph**: Edges have associated costs or weights
   - **Unweighted Graph**: All edges have the same importance

3. **Cyclic vs. Acyclic**
   - **Cyclic Graph**: Contains at least one cycle
   - **Acyclic Graph**: Contains no cycles

4. **Connected vs. Disconnected**
   - **Connected Graph**: There is a path between every pair of vertices
   - **Disconnected Graph**: Some vertices cannot be reached from others

5. **Special Types**
   - **Complete Graph**: Every vertex is connected to every other vertex
   - **Bipartite Graph**: Vertices can be divided into two sets with edges only between sets
   - **Tree**: Connected acyclic undirected graph
   - **DAG**: Directed Acyclic Graph

## Graph Representations

### Adjacency Matrix

A 2D array where matrix[i][j] represents an edge from vertex i to vertex j.

- **Pros**: Quick edge lookup, simple for dense graphs
- **Cons**: Space inefficient (O(V²)), slower to add/remove vertices

```java
public class AdjacencyMatrix {
    private boolean[][] matrix;
    private int vertices;

    public AdjacencyMatrix(int vertices) {
        this.vertices = vertices;
        matrix = new boolean[vertices][vertices];
    }

    public void addEdge(int source, int destination) {
        matrix[source][destination] = true;
        // For undirected graph: matrix[destination][source] = true;
    }

    public void removeEdge(int source, int destination) {
        matrix[source][destination] = false;
        // For undirected graph: matrix[destination][source] = false;
    }

    public boolean isEdge(int source, int destination) {
        return matrix[source][destination];
    }
}
```

### Adjacency List

An array of lists where each list contains the neighbors of the corresponding vertex.

- **Pros**: Space efficient for sparse graphs, faster to iterate over edges
- **Cons**: Slower edge lookup

```java
public class AdjacencyList {
    private List<Integer>[] adjList;

    @SuppressWarnings("unchecked")
    public AdjacencyList(int vertices) {
        adjList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjList[source].add(destination);
        // For undirected graph: adjList[destination].add(source);
    }

    public void removeEdge(int source, int destination) {
        adjList[source].remove(Integer.valueOf(destination));
        // For undirected graph: adjList[destination].remove(Integer.valueOf(source));
    }

    public boolean isEdge(int source, int destination) {
        return adjList[source].contains(destination);
    }

    public List<Integer> getNeighbors(int vertex) {
        return adjList[vertex];
    }
}
```

## Basic Graph Algorithms

### Traversal Algorithms

#### Breadth-First Search (BFS)

```java
public void bfs(int start) {
    boolean[] visited = new boolean[vertices];
    Queue<Integer> queue = new LinkedList<>();

    visited[start] = true;
    queue.offer(start);

    while (!queue.isEmpty()) {
        int vertex = queue.poll();
        System.out.print(vertex + " ");

        for (int neighbor : getNeighbors(vertex)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }
    }
}
```

#### Depth-First Search (DFS)

```java
public void dfs(int start) {
    boolean[] visited = new boolean[vertices];
    dfsRecursive(start, visited);
}

private void dfsRecursive(int vertex, boolean[] visited) {
    visited[vertex] = true;
    System.out.print(vertex + " ");

    for (int neighbor : getNeighbors(vertex)) {
        if (!visited[neighbor]) {
            dfsRecursive(neighbor, visited);
        }
    }
}
```

### Shortest Path Algorithms

#### Dijkstra's Algorithm (Single-Source Shortest Path for weighted graphs)

```java
public int[] dijkstra(int start) {
    int[] dist = new int[vertices];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [vertex, distance]
    pq.offer(new int[]{start, 0});

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int u = current[0];
        int distance = current[1];

        if (distance > dist[u]) continue;

        for (int[] edge : getWeightedEdges(u)) { // [neighbor, weight]
            int v = edge[0];
            int weight = edge[1];

            if (dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                pq.offer(new int[]{v, dist[v]});
            }
        }
    }

    return dist;
}
```

### Minimum Spanning Tree Algorithms

#### Prim's Algorithm

```java
public void primMST() {
    // Implementation details omitted for brevity
}
```

#### Kruskal's Algorithm

```java
public void kruskalMST() {
    // Implementation details omitted for brevity
}
```

### Topological Sort (for DAGs)

```java
public int[] topologicalSort() {
    boolean[] visited = new boolean[vertices];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < vertices; i++) {
        if (!visited[i]) {
            topologicalSortUtil(i, visited, stack);
        }
    }

    int[] result = new int[vertices];
    for (int i = 0; i < vertices; i++) {
        result[i] = stack.pop();
    }

    return result;
}

private void topologicalSortUtil(int vertex, boolean[] visited, Stack<Integer> stack) {
    visited[vertex] = true;

    for (int neighbor : getNeighbors(vertex)) {
        if (!visited[neighbor]) {
            topologicalSortUtil(neighbor, visited, stack);
        }
    }

    stack.push(vertex);
}
```

## Advanced Graph Algorithms

- **Strongly Connected Components**: Kosaraju's, Tarjan's algorithms
- **Articulation Points and Bridges**: Finding critical nodes/edges
- **Maximum Flow**: Ford-Fulkerson, Edmonds-Karp algorithms
- **Bipartite Matching**: Hungarian algorithm
- **All-Pairs Shortest Path**: Floyd-Warshall algorithm

## Applications of Graphs

- **Social Networks**: Friend connections, influence analysis
- **Maps and Navigation**: Road networks, route planning
- **Web Crawling**: Web page connections
- **Dependency Resolution**: Package dependencies, build systems
- **Network Routing**: Computer network topology
- **Recommendation Systems**: Item-to-item relationships

## Complexity Analysis

| Operation | Adjacency Matrix | Adjacency List |
|-----------|------------------|----------------|
| Add Vertex | O(V²) | O(1) |
| Add Edge | O(1) | O(1) |
| Remove Vertex | O(V²) | O(V + E) |
| Remove Edge | O(1) | O(E) |
| Query Edge | O(1) | O(degree) |
| BFS/DFS | O(V²) | O(V + E) |
| Dijkstra's | O(V²) | O((V + E) log V) with heap |
| Prim's | O(V²) | O(E log V) with heap |
| Kruskal's | O(E log E) | O(E log E) |

## Considerations for Graph Implementation

- **Memory Usage**: Adjacency matrices use O(V²) space, adjacency lists use O(V + E)
- **Graph Density**: Dense graphs favor adjacency matrices, sparse graphs favor adjacency lists
- **Operation Frequency**: Consider which operations will be performed most frequently
- **Graph Size**: Very large graphs may require specialized implementations or distributed processing
- **Graph Mutability**: Whether the graph structure will change frequently

## Java's Built-in Libraries

Java doesn't have built-in graph data structures, but libraries like JGraphT offer comprehensive implementations.
## Overview

Graphs are versatile data structures that represent a collection of nodes (vertices) connected by edges. They are used to model relationships between objects and are fundamental to many algorithms and real-world applications. This section covers various graph representations, traversal methods, and common graph algorithms.

## Graph Types

1. **Undirected Graph**: Edges have no direction
2. **Directed Graph (Digraph)**: Edges have direction
3. **Weighted Graph**: Edges have associated weights or costs
4. **Unweighted Graph**: All edges have equal weight
5. **Cyclic Graph**: Contains at least one cycle
6. **Acyclic Graph**: Contains no cycles
7. **Connected Graph**: There is a path between every pair of vertices
8. **Disconnected Graph**: Some vertices cannot be reached from others
9. **Complete Graph**: Every vertex is connected to every other vertex
10. **Bipartite Graph**: Vertices can be divided into two disjoint sets

## Graph Representations

### Adjacency Matrix

- 2D array where matrix[i][j] represents an edge from vertex i to vertex j
- Space Complexity: O(V²) where V is the number of vertices
- Time Complexity:
  - Edge existence check: O(1)
  - Finding all neighbors: O(V)
  - Adding/removing an edge: O(1)
  - Adding/removing a vertex: O(V²)

### Adjacency List

- Array of lists where each list contains the neighbors of a vertex
- Space Complexity: O(V + E) where V is vertices and E is edges
- Time Complexity:
  - Edge existence check: O(degree(v))
  - Finding all neighbors: O(degree(v))
  - Adding/removing an edge: O(1) amortized
  - Adding a vertex: O(1)
  - Removing a vertex: O(V + E)

## Graph Traversal Algorithms

### Breadth-First Search (BFS)

```java
public void bfs(Graph graph, int start) {
    boolean[] visited = new boolean[graph.getVertexCount()];
    Queue<Integer> queue = new LinkedList<>();

    visited[start] = true;
    queue.offer(start);

    while (!queue.isEmpty()) {
        int current = queue.poll();
        System.out.print(current + " ");

        for (int neighbor : graph.getAdjList()[current]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }
    }
}
```

### Depth-First Search (DFS)

```java
public void dfs(Graph graph, int start) {
    boolean[] visited = new boolean[graph.getVertexCount()];
    dfsUtil(graph, start, visited);
}

private void dfsUtil(Graph graph, int vertex, boolean[] visited) {
    visited[vertex] = true;
    System.out.print(vertex + " ");

    for (int neighbor : graph.getAdjList()[vertex]) {
        if (!visited[neighbor]) {
            dfsUtil(graph, neighbor, visited);
        }
    }
}
```

## Common Graph Algorithms

### Shortest Path Algorithms

1. **Dijkstra's Algorithm**: For graphs with non-negative edge weights
2. **Bellman-Ford Algorithm**: Handles negative edge weights
3. **Floyd-Warshall Algorithm**: All-pairs shortest paths
4. **A* Search Algorithm**: Optimized path finding using heuristics

### Minimum Spanning Tree Algorithms

1. **Prim's Algorithm**: Builds MST by adding the minimum weight edge that connects a vertex in the tree to a vertex outside
2. **Kruskal's Algorithm**: Builds MST by adding edges in order of increasing weight

### Connectivity Algorithms

1. **Finding Connected Components**: Using DFS or BFS
2. **Strongly Connected Components**: Using Kosaraju's or Tarjan's algorithm
3. **Biconnected Components**: Finding articulation points

### Cycle Detection

1. **In Directed Graphs**: Using DFS and tracking recursion stack
2. **In Undirected Graphs**: Using DFS or Union-Find

### Topological Sorting

```java
public int[] topologicalSort(Graph graph) {
    int V = graph.getVertexCount();
    boolean[] visited = new boolean[V];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < V; i++) {
        if (!visited[i]) {
            topologicalSortUtil(graph, i, visited, stack);
        }
    }

    int[] result = new int[V];
    for (int i = 0; i < V; i++) {
        result[i] = stack.pop();
    }

    return result;
}

private void topologicalSortUtil(Graph graph, int vertex, boolean[] visited, Stack<Integer> stack) {
    visited[vertex] = true;

    for (int neighbor : graph.getAdjList()[vertex]) {
        if (!visited[neighbor]) {
            topologicalSortUtil(graph, neighbor, visited, stack);
        }
    }

    stack.push(vertex);
}
```

## Applications of Graphs

1. **Social Networks**: Modeling relationships between users
2. **Web Crawling**: Web pages as vertices and hyperlinks as edges
3. **Network Routing**: Finding optimal paths in computer networks
4. **Dependency Resolution**: Package dependencies in software
5. **Recommendation Systems**: User-item graphs
6. **Geographic Information Systems**: Maps and navigation
7. **Compiler Design**: Control flow and data flow analysis
8. **Scheduling Problems**: Task dependencies and resource allocation

## Complexity Analysis

| Algorithm                  | Time Complexity | Space Complexity |
|----------------------------|----------------|------------------|
| BFS                        | O(V+E)         | O(V)             |
| DFS                        | O(V+E)         | O(V)             |
| Dijkstra's Algorithm       | O((V+E)log V)* | O(V)             |
| Bellman-Ford Algorithm     | O(V*E)         | O(V)             |
| Floyd-Warshall Algorithm   | O(V³)          | O(V²)            |
| Prim's Algorithm           | O((V+E)log V)* | O(V)             |
| Kruskal's Algorithm        | O(E log E)     | O(V)             |
| Topological Sort           | O(V+E)         | O(V)             |
| Strongly Connected Components | O(V+E)      | O(V)             |

*Using priority queue/binary heap implementation

## Advanced Graph Concepts

- **Flow Networks**: Maximum flow, minimum cut problems
- **Bipartite Matching**: Assignment problems
- **Eulerian and Hamiltonian Paths**: Special path problems
- **Planar Graphs**: Graphs that can be drawn without edge crossings
- **Graph Coloring**: Assigning colors to vertices with constraints
- **Dynamic Graphs**: Graphs that change over time
- **Spectral Graph Theory**: Using eigenvalues and eigenvectors of graph matrices
