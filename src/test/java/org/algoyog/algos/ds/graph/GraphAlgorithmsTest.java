package org.algoyog.algos.ds.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class GraphAlgorithmsTest {

    private GraphAlgorithms graphAlgos;
    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    public void setUp() {
        graphAlgos = new GraphAlgorithms();
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testBFS() {
        // Create a simple graph
        // 0 -- 1 -- 3
        // |    |
        // 2 -- 4
        GraphAlgorithms.Graph graph = new GraphAlgorithms.Graph(5);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 3);
        graph.addUndirectedEdge(1, 4);
        graph.addUndirectedEdge(2, 4);

        // Test BFS from vertex 0
        graphAlgos.bfs(graph, 0);
        String expectedOutput = "BFS traversal starting from vertex 0:\n0 1 2 3 4 \n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());

        // Reset output
        outputStreamCaptor.reset();

        // Test BFS from vertex 3
        graphAlgos.bfs(graph, 3);
        expectedOutput = "BFS traversal starting from vertex 3:\n3 1 0 4 2 \n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testDFS() {
        // Create a simple graph
        // 0 -- 1 -- 3
        // |    |
        // 2 -- 4
        GraphAlgorithms.Graph graph = new GraphAlgorithms.Graph(5);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 3);
        graph.addUndirectedEdge(1, 4);
        graph.addUndirectedEdge(2, 4);

        // Test DFS from vertex 0
        graphAlgos.dfs(graph, 0);
        String expectedOutput = "DFS traversal starting from vertex 0:\n0 1 3 4 2 \n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());

        // Reset output
        outputStreamCaptor.reset();

        // Test DFS from vertex 3
        graphAlgos.dfs(graph, 3);
        expectedOutput = "DFS traversal starting from vertex 3:\n3 1 0 2 4 \n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testDijkstra() {
        // Create a weighted graph
        // 0 --(4)-- 1 --(8)-- 3
        // |         |
        // (2)       (1)
        // |         |
        // 2 --(1)-- 4
        GraphAlgorithms.WeightedGraph graph = new GraphAlgorithms.WeightedGraph(5);
        graph.addUndirectedEdge(0, 1, 4);
        graph.addUndirectedEdge(0, 2, 2);
        graph.addUndirectedEdge(1, 3, 8);
        graph.addUndirectedEdge(1, 4, 1);
        graph.addUndirectedEdge(2, 4, 1);

        // Test shortest paths from vertex 0
        int[] distances = graphAlgos.dijkstra(graph, 0);
        int[] expected = {0, 4, 2, 12, 3};
        assertArrayEquals(expected, distances);

        // Test shortest paths from vertex 3
        distances = graphAlgos.dijkstra(graph, 3);
        expected = new int[]{12, 8, 10, 0, 9};
        assertArrayEquals(expected, distances);
    }

    @Test
    public void testHasCycle() {
        // Create a directed acyclic graph (DAG)
        GraphAlgorithms.Graph dag = new GraphAlgorithms.Graph(4);
        dag.addEdge(0, 1);
        dag.addEdge(0, 2);
        dag.addEdge(1, 3);
        dag.addEdge(2, 3);

        assertFalse(graphAlgos.hasCycle(dag));

        // Create a directed graph with a cycle
        GraphAlgorithms.Graph cyclic = new GraphAlgorithms.Graph(4);
        cyclic.addEdge(0, 1);
        cyclic.addEdge(1, 2);
        cyclic.addEdge(2, 3);
        cyclic.addEdge(3, 1); // Creates a cycle: 1 -> 2 -> 3 -> 1

        assertTrue(graphAlgos.hasCycle(cyclic));

        // Create a graph with self-loop
        GraphAlgorithms.Graph selfLoop = new GraphAlgorithms.Graph(3);
        selfLoop.addEdge(0, 1);
        selfLoop.addEdge(1, 2);
        selfLoop.addEdge(2, 2); // Self-loop

        assertTrue(graphAlgos.hasCycle(selfLoop));
    }

    @Test
    public void testTopologicalSort() {
        // Create a directed acyclic graph (DAG)
        // 5 -> 0 -> 2
        //      |    |
        //      v    v
        //      1 <- 3 -> 4
        GraphAlgorithms.Graph dag = new GraphAlgorithms.Graph(6);
        dag.addEdge(5, 0);
        dag.addEdge(0, 2);
        dag.addEdge(0, 1);
        dag.addEdge(2, 3);
        dag.addEdge(3, 1);
        dag.addEdge(3, 4);

        int[] result = graphAlgos.topologicalSort(dag);

        // Create a helper array to check if result is a valid topological ordering
        boolean[] visited = new boolean[6];
        for (int i = 0; i < result.length; i++) {
            visited[result[i]] = true;
            // Check that all children of the current node are not yet visited
            for (int child : dag.getAdjList()[result[i]]) {
                assertFalse(visited[child], "Violation of topological order at index " + i);
            }
        }
    }
}
