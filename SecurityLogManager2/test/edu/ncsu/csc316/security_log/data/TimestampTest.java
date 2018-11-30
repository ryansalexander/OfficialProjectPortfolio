package edu.ncsu.csc316.security_log.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the timestamp class
 * @author Ryan Alexander
 *
 */
public class TimestampTest {

	/**
	 * Tests the entirety of the timestamp class
	 */
	@Test
	public void testTime() {
		Timestamp test = new Timestamp("1/18/2018 1:22:21PM");
		assertEquals(1, test.getMonth());
		assertEquals(18, test.getDay());
		assertEquals(2018, test.getYear());
		assertEquals(1, test.getHour());
		assertEquals(22, test.getMinute());
		assertEquals(21, test.getSecond());
		assertEquals("PM", test.getSuffix());
		assertEquals("1/18/2018 1:22:21PM", test.toString());
		Timestamp test2 = new Timestamp("1/18/2018 1:23:47PM");
		assertEquals(-1, test.compareTo(test2));
		Timestamp test3 = new Timestamp("1/18/2018 1:22:21PM");
		assertTrue(test.equals(test3));
		assertEquals(0, test.compareTo(test3));
	}

}
