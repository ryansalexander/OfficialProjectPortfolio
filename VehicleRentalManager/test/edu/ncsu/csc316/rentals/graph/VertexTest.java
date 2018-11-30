package edu.ncsu.csc316.rentals.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.rentals.data.Rental;
import edu.ncsu.csc316.rentals.graph.Edge;
import edu.ncsu.csc316.rentals.graph.Vertex;

/**
 * Tests the vertex class
 * @author Ryan Alexander
 *
 */
public class VertexTest {

	/**
	 * Tests entire vertex class
	 */
	@Test
	public void testVertex() {
		Vertex test1 = new Vertex(1);
		assertEquals(1, test1.getDay());
		assertNull(test1.getParent());
		assertNull(test1.getNext());
		assertFalse(test1.isFound());
		assertNull(test1.getEdge());
		assertEquals(Integer.MAX_VALUE, test1.getWeight());
		assertEquals(-1, test1.getIndex());
		Rental rental = new Rental(1, 3, 85, "Chevy", "Camaro");
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(3);
		Edge test = new Edge(rental, v1, v2);
		v1.setEdge(test);
		v1.setNext(v2);
		v2.setParent(v1);
		assertEquals(test, v1.getEdge());
		assertEquals(v2, v1.getNext());
		assertEquals(v1, v2.getParent());
	}

}
