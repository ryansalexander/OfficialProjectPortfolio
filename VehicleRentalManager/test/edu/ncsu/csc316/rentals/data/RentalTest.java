package edu.ncsu.csc316.rentals.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the rental class
 * @author Ryan Alexander
 *
 */
public class RentalTest {

	/**
	 * Tests all of rental
	 */
	@Test
	public void testRental() {
		Rental test = new Rental(1, 2, 85, "Chevrolet", "Tahoe");
		assertEquals(1, test.getStartDay());
		assertEquals(2, test.getEndDay());
		assertEquals(85, test.getCost());
		assertEquals("Chevrolet", test.getMake());
		assertEquals("Tahoe", test.getModel());
	}

}
