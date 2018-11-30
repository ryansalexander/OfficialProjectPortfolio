package edu.ncsu.csc316.rentals.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the main class
 * @author Ryan Alexander
 *
 */
public class VehicleRentalManagerTest {

	/**
	 * Tests the rentals on a given day
	 */
	@Test
	public void testRentalPerDay() {
		VehicleRentalManager test = new VehicleRentalManager("input/sample.csv");
		String str = "Available rentals for day 1\n" + 
				"   $85.00 Chevrolet Tahoe for day 1 to day 2\n" + 
				"   $180.00 Chevrolet Silverado for day 1 to day 3\n" + 
				"   $255.00 Toyota Prius for day 1 to day 4\n" + 
				"   $500.00 Honda CRV for day 1 to day 5\n" + 
				"]";
		assertEquals(str, test.getRentalsForDay(1));
		str = "Available rentals for day 5\n" + 
				"   No rentals available.\n" + 
				"]";
		assertEquals(str, test.getRentalsForDay(5));
	}
	
	/**
	 * Tests the getRentals method
	 */
	@Test
	public void testGetRentals() {
		VehicleRentalManager test = new VehicleRentalManager("input/sample.csv");
		String str = "Rental total is $225.00\n" + 
				"[\n" + 
				"   From day 1 to day 2: $85.00, Chevrolet Tahoe\n" + 
				"   From day 2 to day 4: $90.00, Jeep Cherokee\n" + 
				"   From day 4 to day 5: $50.00, Honda Accord\n" + 
				"]";
		assertEquals(str, test.getRentals(1, 5));
	}
}
