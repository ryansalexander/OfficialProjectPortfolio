package edu.ncsu.csc316.rentals.graph;

import edu.ncsu.csc316.rentals.list.ArrayBasedList;
/**
 * Graph interface to represent the rental availability
 * @author Ryan Alexander
 *
 */
public interface Graph {

	/**
	 * Inserts a vertex into the graph
	 * @param v the new vertex
	 */
	void insertVertex(Vertex v);
	
	/**
	 * Inserts a new edge into the graph
	 * @param e the new edge
	 */
	void insertEdge(Edge e);
	
	/**
	 * Returns a list of the edges connected to a vertex
	 * @param v the vertex to get the edges from
	 * @return list of incident edges
	 */
	ArrayBasedList<Edge> getIncidentEdges(Vertex v);
	
	/**
	 * Returns the vertex at the opposite end of the edge
	 * @param v The vertex that isn't the one being looked for
	 * @param e The connecting edge
	 * @return The vertex at the opposite edge
	 */
	Vertex getOpposite(Vertex v, Edge e);
	
	/**
	 * Returns all the vertices in the graph
	 * @return a list of the vertices in the graph
	 */
	ArrayBasedList<Vertex> vertices();
	
	/**
	 * Gets the vertex at the given index
	 * @param idx The location in the arraylist
	 * @return the vertex at the given index
	 */
	Vertex getVertex(int idx);
	
	/**
	 * Gets the edge that connects two vertices with a given weight/priority
	 * @param v1 the first vertex
	 * @param v2 the second vertex
	 * @param weight the cost of the rental
	 * @return the appropriate edge
	 */
	Edge getEdge(Vertex v1, Vertex v2, int weight);
	
}
