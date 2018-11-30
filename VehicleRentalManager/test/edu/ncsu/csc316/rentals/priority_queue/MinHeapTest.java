package edu.ncsu.csc316.rentals.priority_queue;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.rentals.graph.Vertex;

/**
 * Tests the MinHeap class
 * @author Ryan Alexander
 *
 */
public class MinHeapTest {

	/**
	 * Tests the entirety of the minheap class
	 */
	@Test
	public void testMinHeap() {
		Vertex v1 = new Vertex(1);
		v1.setWeight(7);
		Vertex v2 = new Vertex(2);
		v2.setWeight(9);
		Vertex v3 = new Vertex(3);
		v3.setWeight(4);
		Vertex v4 = new Vertex(4);
		v4.setWeight(6);
		Vertex v5 = new Vertex(5);
		v5.setWeight(1);
		Vertex v6 = new Vertex(6);
		v6.setWeight(8);
		MinHeap mh = new MinHeap();
		mh.insert(v1);
		mh.insert(v2);
		mh.insert(v3);
		mh.insert(v4);
		mh.insert(v5);
		mh.insert(v6);
		assertEquals(v1, mh.getHeap().get(2));
		assertEquals(v2, mh.getHeap().get(3));
		assertEquals(v3, mh.getHeap().get(1));
		assertEquals(v4, mh.getHeap().get(4));
		assertEquals(v5, mh.getHeap().get(0));
		assertEquals(v6, mh.getHeap().get(5));
		assertEquals(v5, mh.deleteMin());
		assertEquals(v3, mh.deleteMin());
		assertEquals(v4, mh.deleteMin());
		assertEquals(v1, mh.deleteMin());
		assertEquals(v6, mh.deleteMin());
		assertEquals(v2, mh.deleteMin());
		assertTrue(mh.isEmpty());
		mh.insert(v1);
		mh.insert(v2);
		mh.insert(v3);
		mh.insert(v4);
		mh.insert(v5);
		mh.insert(v6);
		mh.updatePriority(v3, 0);
		assertEquals(v3, mh.getHeap().get(0));
		assertEquals(v4.getDay(), mh.getHeap().get(1).getDay());
		assertEquals(v5.getDay(), mh.getHeap().get(2).getDay());
		assertEquals(v2.getDay(), mh.getHeap().get(3).getDay());
		assertEquals(v1.getDay(), mh.getHeap().get(4).getDay());
		assertEquals(v6.getDay(), mh.getHeap().get(5).getDay());
	}

}
