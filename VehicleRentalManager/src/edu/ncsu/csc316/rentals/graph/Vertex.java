package edu.ncsu.csc316.rentals.graph;

/**
 * Constructs vertex nodes for the graph
 * @author Ryan Alexander
 *
 */
public class Vertex {

	/** The day representing the vertex */
	private int day;
	/** The vertex that leads to this one */
	private Vertex parent;
	/** Vertex at the end of the edge of this one */
	private Vertex next;
	/** Whether or not the vertex has been found for Dijkstra's */
	private boolean found;
	/** Edge/Weight of this vertex */
	private Edge edge;
	/** The weight of the edge */
	private int weight;
	/** Index used for Priority Queues */
	private int index;
	
	/**
	 * Constructs the vertex given only the day
	 * @param day the day the rental begins/ends
	 */
	public Vertex(int day) {
		this(day, null, null, false, null, Integer.MAX_VALUE);
	}
	
	/**
	 * Builds a vertex with all fields
	 * @param day the day for the rental
	 * @param parent the parent to this node
	 * @param next the node connected to this by the edge
	 * @param found whether this node has been found for Dijkstra's
	 * @param edge the edge connecting this and the next
	 * @param weight the weight of vertex
	 */
	public Vertex(int day, Vertex parent, Vertex next, boolean found, Edge edge, int weight) {
		setDay(day);
		setParent(parent);
		setNext(next);
		setFound(found);
		setEdge(edge);
		setWeight(weight);
		setIndex(-1);
	}

	/**
	 * Returns the day
	 * @return day the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Sets the day
	 * @param day the day of the rental
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Returns the parent vertex
	 * @return parent the parent vertex
	 */
	public Vertex getParent() {
		return parent;
	}

	/**
	 * Sets the parent vertex
	 * @param parent the parent
	 */
	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	/**
	 * Gets the next vertex
	 * @return next the next vertex
	 */
	public Vertex getNext() {
		return next;
	}

	/**
	 * Sets the next vertex
	 * @param next the next vertex
	 */
	public void setNext(Vertex next) {
		this.next = next;
	}

	/**
	 * Returns whether this vertex has been found
	 * @return true if so, false otherwise
	 */
	public boolean isFound() {
		return found;
	}

	/**
	 * Sets found to true or false
	 * @param found true or false
	 */
	public void setFound(boolean found) {
		this.found = found;
	}

	/**
	 * Gets the edge that connects this to the next
	 * @return edge the edge
	 */
	public Edge getEdge() {
		return edge;
	}

	/**
	 * Sets the edge
	 * @param edge the edge
	 */
	public void setEdge(Edge edge) {
		this.edge = edge;
	}
	
	/**
	 * Returns the weight of the edge
	 * @return weight the edge weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Sets the weight of the vertex
	 * @param weight the weight of the vertex
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * Returns the index for PQ
	 * @return index the index in the PQ
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Sets the index it resides in the PQ
	 * @param i the idx
	 */
	public void setIndex(int i) {
		this.index = i;
	}
	
}
