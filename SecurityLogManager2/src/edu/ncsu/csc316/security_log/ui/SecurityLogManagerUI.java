package edu.ncsu.csc316.security_log.ui;

import java.util.Scanner;

import edu.ncsu.csc316.rentals.manager.VehicleRentalManager;
import edu.ncsu.csc316.security_log.manager.SecurityLogManager;

/**
 * Handles the user interface
 * @author Ryan Alexander
 *
 */
public class SecurityLogManagerUI {
	
	/**
	 * Main method that runs on execution
	 * @param args commands
	 */
	public static void main(String args[]) {
		System.out.print("Please input a valid file: ");
		while ( instance == null ) {
			System.out.print("Input Rental File name: ");
			String rentals = scan.nextLine();
			try { 
				instance = new VehicleRentalManager(rentals);
			} catch ( IllegalArgumentException e ) {
				System.out.println("Invalid file name, try something in the form: \"parentfolder/filename.csv\"");
			}
		}
		try {
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			SecurityLogManager instance = new SecurityLogManager(input);
			System.out.println("Enter command:");
			String command = scan.nextLine();
			if (command.equals("g")) {
				System.out.println("Input start time and end time");
				String start = scan.nextLine();
				String end = scan.nextLine();
				System.out.println(instance.generateOperationalProfile(start, end));
			} else if (command.equals("u")) {
				System.out.println("Input username");
				String username = scan.nextLine();
				System.out.println(instance.getUserReport(username));
			}
			scan.close();
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid File");
		}
	}
}
