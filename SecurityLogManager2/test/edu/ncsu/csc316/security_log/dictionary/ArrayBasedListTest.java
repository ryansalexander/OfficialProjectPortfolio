package edu.ncsu.csc316.security_log.dictionary;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ArrayBasedList class (Based on my test file from Project 3)
 * @author Ryan Alexander
 *
 */
public class ArrayBasedListTest {

	/**
	 * Tests the entirety of ABL
	 */
	@Test
	public void test() {
		ArrayBasedList<String> test = new ArrayBasedList<String>(3);
		assertEquals(0, test.size());
		test.add(0, "A");
		test.add(1, "B");
		assertEquals("A", test.get(0));
		assertEquals("B", test.get(1));
		assertFalse(test.isEmpty());
		test.insert("C");
		test.insert("D");
		assertEquals("C", test.get(2));
		assertEquals("D", test.get(3));
		try {
			test.get(4);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, test.size());
		}
		test.add(3, "Q");
		assertEquals("Q", test.get(3));
		assertEquals("D", test.get(4));
		test.set(3, "Z");
		assertEquals("Z", test.get(3));
		assertEquals(5, test.size());
		test.remove(0);
		test.remove(3);
		test.remove(2);
		test.remove(0);
		test.remove(0);
		assertTrue(test.isEmpty());
	}
}