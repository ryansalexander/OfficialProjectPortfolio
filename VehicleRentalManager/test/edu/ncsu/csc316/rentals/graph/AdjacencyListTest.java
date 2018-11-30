package edu.ncsu.csc316.rentals.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.rentals.data.Rental;
import edu.ncsu.csc316.rentals.graph.AdjacencyList;
import edu.ncsu.csc316.rentals.graph.Edge;
import edu.ncsu.csc316.rentals.graph.Vertex;

/**
 * Tests the adjacency list class
 * @author Ryan Alexander
 *
 */
public class AdjacencyListTest {

	/**
	 * Tests the entirety of adjacency list
	 */
	@Test
	public void testAdjacencyList() {
		AdjacencyList list = new AdjacencyList();
		Rental tahoe = new Rental(1, 2, 85, "Chevrolet", "Tahoe");
		Rental prius = new Rental(1, 4, 255, "Toyota", "Prius");
		Rental explorer = new Rental(2, 5, 220, "Ford", "Explorer");
		Rental accord = new Rental(4, 5, 50, "Honda", "Accord");
		Vertex first = new Vertex(1);
		Vertex second = new Vertex(2);
		Vertex fourth = new Vertex(4);
		Vertex fifth = new Vertex(5);
		list.insertVertex(first);
		list.insertVertex(second);
		Edge t = new Edge(tahoe, first, second);
		list.insertEdge(t);
		assertEquals(first, list.getVertex(1));
		assertEquals(second, list.getVertex(2));
		assertEquals(t, list.getIncidentEdges(first).get(0));
		list.insertVertex(fourth);
		assertEquals(null, list.getVertex(4).getNext());
		Edge p = new Edge(prius, first, fourth);
		list.insertEdge(p);
		assertEquals(null, list.getVertex(4).getNext());
		assertEquals(fourth, list.getVertex(4));
		assertEquals(p, list.getIncidentEdges(list.getVertex(1)).get(0));
		assertEquals(t, list.getIncidentEdges(list.getVertex(1)).get(1));
		list.insertVertex(fifth);
		Edge e = new Edge(explorer, second, fifth);
		list.insertEdge(e);
		assertEquals(fifth, list.getVertex(5));
		assertEquals(e, list.getIncidentEdges(list.getVertex(2)).get(0));
		Edge a = new Edge(accord, list.getVertex(4), fifth);
		list.insertEdge(a);
		assertEquals(null, list.getVertex(4).getNext().getNext());
		assertEquals(a, list.getIncidentEdges(list.getVertex(4)).get(0));
		assertEquals(a, list.getEdge(fourth, fifth, 50));
		assertEquals(fifth, list.getOpposite(fourth, a));
	}

}
