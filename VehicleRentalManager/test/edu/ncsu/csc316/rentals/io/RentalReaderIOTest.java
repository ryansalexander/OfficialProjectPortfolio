package edu.ncsu.csc316.rentals.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.rentals.graph.AdjacencyList;

/**
 * Tests the Rental Reader class
 * @author Ryan Alexander
 *
 */
public class RentalReaderIOTest {

	/**
	 * Tests the entire reader class
	 */
	@Test
	public void testReader() {
		RentalReaderIO test = new RentalReaderIO();
		try {
			@SuppressWarnings("static-access")
			AdjacencyList graph = test.readRentals("input/sample.csv");
			assertEquals(1, graph.vertices().get(0).getDay());
			assertEquals(2, graph.vertices().get(1).getDay());
			assertEquals(3, graph.vertices().get(2).getDay());
			assertEquals(4, graph.vertices().get(3).getDay());
			assertEquals(5, graph.vertices().get(4).getDay());
		} catch (FileNotFoundException e) {
			fail("Invalid File");
		}
		
		try {
			@SuppressWarnings("static-access")
			AdjacencyList graph = test.readRentals("input/sample2.csv");
			assertEquals(1, graph.vertices().get(0).getDay());
			assertEquals(2, graph.vertices().get(1).getDay());
			assertEquals(3, graph.vertices().get(2).getDay());
			assertEquals(4, graph.vertices().get(3).getDay());
		} catch (FileNotFoundException e) {
			fail("Invalid File");
		}
		
	}

}
