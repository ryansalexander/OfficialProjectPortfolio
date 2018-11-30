package edu.ncsu.csc316.rentals.priority_queue;

import edu.ncsu.csc316.rentals.graph.Vertex;

/**
 * Interface for the MinHeap used to find a MST
 * @author Ryan Alexander
 */
public interface PriorityQueue {

	/**
	 * Inserts an object E
	 * @param v the object to be added
	 */
	void insert(Vertex v);
	
	/**
	 * Deletes the object with the minimum value
	 * @return the object
	 */
	Vertex deleteMin();
	
	/**
	 * Updates the object e with a new priority
	 * @param v the object to be updated
	 * @param priority the priority to give the object
	 */
	void updatePriority(Vertex v, int priority);
	
	/**
	 * Determines if the Queue is empty
	 * @return true if so, false otherwise
	 */
	boolean isEmpty();
	
}
