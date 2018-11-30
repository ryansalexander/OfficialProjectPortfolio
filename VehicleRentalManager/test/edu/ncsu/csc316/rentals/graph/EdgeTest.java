package edu.ncsu.csc316.rentals.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.rentals.data.Rental;
import edu.ncsu.csc316.rentals.graph.Edge;
import edu.ncsu.csc316.rentals.graph.Vertex;

/**
 * Tests the edge class
 * @author Ryan Alexander
 *
 */
public class EdgeTest {

	/**
	 * Tests the entire edge class
	 */
	@Test
	public void testEdge() {
		Rental rental = new Rental(1, 3, 85, "Chevy", "Camaro");
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(3);
		Edge test = new Edge(rental, v1, v2);
		assertEquals(rental, test.getRental());
		assertEquals(85, test.getWeight());
		assertEquals(v1, test.getFirst());
		assertEquals(v2, test.getLast());
	}

}
