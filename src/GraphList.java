import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class GraphList implements Graph {
    private HashMap<Integer, ArrayList<Integer>> adjacencyList;
    private boolean[] visited;

    public GraphList(int numberOfVertices) {
        adjacencyList = new HashMap<>();
        visited = new boolean[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    @Override
    public void init(Integer n) {
        adjacencyList.clear();
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }
/// returns the count of nodes in the graph
    @Override
    public Integer nodeCount() {
        return adjacencyList.size();
    }
// counts the total number of edges in the graph
    @Override
    public Integer edgeCount() {
        int count = 0;
        for (ArrayList<Integer> edges : adjacencyList.values()) {
            count += edges.size();
        }
        return count;
    }
// checks if an edge exists between node v and node w
    @Override
    public void addEdge(Integer v, Integer w, Integer wgt) {
        adjacencyList.get(v).add(w);
    }
    ///placeholder method for future implementation of edge weights
    @Override
    public Integer getWeight(Integer v, Integer w) {
        // Currently, this implementation does not support edge weights
        return null;
    }
///placeholder method for setting edge weights
    @Override
    public void setWeight(Integer v, Integer w, Integer wgt) {
        // Currently, this implementation does not support edge weights
    }
// removes an edge from node v to node w
    @Override
    public void removeEdge(Integer v, Integer w) {
        adjacencyList.get(v).remove(w);
    }
/// checks if an edge exists between node v and node w
    @Override
    public boolean hasEdge(Integer v, Integer w) {
        return adjacencyList.get(v).contains(w);
    }

    @Override
    public ArrayList<Integer> neighbors(Integer v) {
        return new ArrayList<>(adjacencyList.get(v));
    }
    //  above ^^^Retrieves a list of all neighbors (adjacent vertices) of node v



    /// Resets the visited array to false for all vertices

    @Override
    public void resetVisited() {
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
    }

    // Performs a Breadth-First Search starting from vertex v
    @Override
    public ArrayList<Integer> BFS(Integer v) {
        resetVisited();
        ArrayList<Integer> traversalOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        visited[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            traversalOrder.add(current);

            for (int neighbor : adjacencyList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return traversalOrder;
    }

    @Override
    public boolean hasPath(Integer v, Integer w) {
        ArrayList<Integer> bfsTraversal = BFS(v);
        return bfsTraversal.contains(w);
    }

    @Override
    public ArrayList<Integer> topologicalSort() {
        // Assuming the graph is a Directed Acyclic Graph (DAG)
        int vertexCount = nodeCount();
        int[] inDegree = new int[vertexCount];
        ArrayList<Integer> sortedList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // Calculate in-degree for each vertex
        for (int i = 0; i < vertexCount; i++) {
            for (Integer neighbor : adjacencyList.get(i)) {
                inDegree[neighbor]++;
            }
        }

        // Find all vertices with in-degree of 0
        for (int i = 0; i < vertexCount; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Process vertices with in-degree of 0
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sortedList.add(current);

            for (Integer neighbor : adjacencyList.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Check for cycle in the graph
        if (sortedList.size() != vertexCount) {
            return null; // Graph has a cycle
        }

        return sortedList;
    }



    public static void main(String[] args) {
        testInit();
        testAddRemoveEdge();
        testNodeAndEdgeCount();
        testBFS();
        testHasPath();
        testTopologicalSort();
        // Add more tests as needed
        System.out.println("All tests passed.");
    }

    private static void testInit() {
        GraphList graph = new GraphList(5);
        graph.init(3);
        assert graph.nodeCount() == 3 : "Init should reinitialize the graph with 3 nodes";
        System.out.println("testInit passed.");
    }

    private static void testAddRemoveEdge() {
        GraphList graph = new GraphList(5);
        graph.addEdge(0, 1, 1);
        assert graph.hasEdge(0, 1) : "Edge should exist from 0 to 1";
        graph.removeEdge(0, 1);
        assert !graph.hasEdge(0, 1) : "Edge from 0 to 1 should be removed";
        System.out.println("testAddRemoveEdge passed.");
    }

    private static void testNodeAndEdgeCount() {
        GraphList graph = new GraphList(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        assert graph.nodeCount() == 4 : "Graph should have 4 nodes";
        assert graph.edgeCount() == 2 : "Graph should have 2 edges";
        System.out.println("testNodeAndEdgeCount passed.");
    }

    private static void testBFS() {
        GraphList graph = new GraphList(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        ArrayList<Integer> result = graph.BFS(0);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        assert result.equals(expected) : "BFS traversal order does not match expected";
        System.out.println("testBFS passed.");
    }

    private static void testHasPath() {
        GraphList graph = new GraphList(3);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        assert graph.hasPath(0, 2) : "There should be a path from 0 to 2";
        assert !graph.hasPath(2, 0) : "There should not be a path from 2 to 0 in a directed graph";
        System.out.println("testHasPath passed.");
    }

    private static void testTopologicalSort() {
        GraphList graph = new GraphList(3);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        ArrayList<Integer> result = graph.topologicalSort();
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        assert result.equals(expected) : "Topological sort order does not match expected";
        System.out.println("testTopologicalSort passed.");
    }


}
