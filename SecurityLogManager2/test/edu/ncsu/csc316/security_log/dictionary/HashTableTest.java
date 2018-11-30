package edu.ncsu.csc316.security_log.dictionary;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the hashtable class
 * @author Ryan Alexander
 *
 */
public class HashTableTest {

	/**
	 * Tests the entirety of the hash table class
	 */
	@Test
	public void testHashtable() {
		HashTable<String> test = new HashTable<String>(6);
		String test1 = "Tegan";
		String test2 = "May";
		String test3 = "Sullivan";
		String test4 = "Tegan";
		test.insert(test1);
		test.insert(test2);
		test.insert(test3);
		assertEquals(test1, test.lookUp("Tegan"));
		assertEquals(test3, test.lookUp("Sullivan"));
		assertEquals(test2, test.lookUp("May"));
		test.insert(test4);
		assertEquals(test4, test.lookUp("Tegan"));
		String test5 = "pls";
		test.insert(test5);
		assertEquals(13, test.getHashTableLength());
	}

}
