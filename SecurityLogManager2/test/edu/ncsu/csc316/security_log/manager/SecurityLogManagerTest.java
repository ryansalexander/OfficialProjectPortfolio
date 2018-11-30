package edu.ncsu.csc316.security_log.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the core methods in SLM
 * @author Ryan Alexander
 *
 */
public class SecurityLogManagerTest {
	
	/**
	 * Tests the user report method
	 */
	@Test
	public void testUser() {
		SecurityLogManager test = new SecurityLogManager("input/other");
		assertEquals("Activity Report for jtking[\n" + 
				"   1/18/2018 12:58:14PM - delete demographics information\n" + 
				"   1/18/2018 1:22:21PM - view prescription information\n" + 
				"]", test.getUserReport("jtking"));
	}
	
	/**
	 * Tests the operational profile method
	 */
	@Test
	public void testProfile() {
		SecurityLogManager test = new SecurityLogManager("input/other");
		assertEquals("OperationalProfile[\n" + 
				"   view prescription information: frequency: 2, percentage: 40.0%\n" + 
				"   create immunization order: frequency: 1, percentage: 20.0%\n" + 
				"   delete demographics information: frequency: 1, percentage: 20.0%\n" + 
				"   delete prescription information: frequency: 1, percentage: 20.0%\n" + 
				"]", test.generateOperationalProfile("1/17/2018 12:00:00AM", "1/20/2018 12:00:00PM"));
		
		test = new SecurityLogManager("input/activityLog_small.txt");
		test.generateOperationalProfile("01/04/2014 02:08:40PM", "01/20/2018 11:39:22AM");
		test = new SecurityLogManager("input/activityLog_medium.txt");
		test.generateOperationalProfile("01/04/2014 02:08:40PM", "01/20/2018 11:39:22AM");
	}
}
