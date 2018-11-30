package edu.ncsu.csc316.rentals.graph;

import edu.ncsu.csc316.rentals.data.Rental;

/**
 * An edge that connects two vertices
 * @author Ryan Alexander
 *
 */
public class Edge {

	/** The rental information */
	private Rental rental;
	/** The cost */
	private int weight;
	/** First vertex */
	private Vertex first;
	/** Second vertex */
	private Vertex last;
	
	/**
	 * Constructor used if weight is equivalent to rental cost
	 * @param rental the rental object
	 * @param first the first vertex
	 * @param last the connected vertex
	 */
	public Edge(Rental rental, Vertex first, Vertex last) {
		this(rental, rental.getCost(), first, last);
	}
	
	/**
	 * Constructor used if weight might not be equivalent to rental cost
	 * @param rental the rental object
	 * @param weight weight of the edge
	 * @param first the first vertex
	 * @param last the connected vertex
	 */
	public Edge(Rental rental, int weight, Vertex first, Vertex last) {
		setRental(rental);
		setWeight(weight);
		setFirst(first);
		setLast(last);
	}

	/**
	 * Returns the rental
	 * @return the rental
	 */
	public Rental getRental() {
		return rental;
	}

	/**
	 * Sets the rental
	 * @param rental the rental to set
	 */
	public void setRental(Rental rental) {
		this.rental = rental;
	}

	/**
	 * Returns the weight
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Sets the weight of the edge
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Returns the first vertex
	 * @return the first
	 */
	public Vertex getFirst() {
		return first;
	}

	/**
	 * Sets the first vertex 
	 * @param first the first to set
	 */
	public void setFirst(Vertex first) {
		this.first = first;
	}

	/**
	 * Returned the last vertex
	 * @return the last
	 */
	public Vertex getLast() {
		return last;
	}

	/**
	 * Sets the last vertex
	 * @param last the last to set
	 */
	public void setLast(Vertex last) {
		this.last = last;
	}
	
}
