
import java.util.ArrayList;

public interface Graph { // Graph class ADT
  // Initialize the graph with some number of vertices
  void init(Integer n);

  // Return the number of vertices
  Integer nodeCount();

  // Return the current number of edges. This operation must be a constant time operation.
  Integer edgeCount();

  // Adds a new edge from node v to node w with weight wgt.
  void addEdge(Integer v, Integer w, Integer wgt);

  // Get the weight value for an edge
  Integer getWeight(Integer v, Integer w);

 // Set the weight of v and w.
  void setWeight(Integer v, Integer w, Integer wgt);

  // Removes the edge from the graph.
  void removeEdge(Integer v, Integer w);

  // Returns true if and only if the graph has an edge between v and w.
  boolean hasEdge(Integer v, Integer w);

  // Returns an array containing vertex id's of the neighbors of v.
  ArrayList<Integer> neighbors(Integer v);

  // Resets the Visited array to all false  (required for BFS).
  void resetVisited();

  // Performs a Breadth-First-Search starting at vertex, v. On PreVisit, the current vertex's label/id should be appended to the end of an ArrayList. Do not perform a PostVisit operation.
  // Once BFS is completed, the ArrayList is returned containing vertex id's stored in BFS PreVisit order.
  ArrayList<Integer> BFS(Integer v);


  // Returns true if there is a path between v and w. Otherwise returns false. You may use the BFS method (above) for this method.
  boolean hasPath(Integer v, Integer w);

  // Performs a topologicalSort if the graph and returns an ArrayList that contains the vertex labels/id in topologically sorted order. You must use a (modified) BFS traversal.
  ArrayList<Integer> topologicalSort();
}