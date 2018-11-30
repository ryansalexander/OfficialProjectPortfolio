package edu.ncsu.csc316.security_log.ui;

import java.util.Scanner;

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
		//Examples of valid files can be found in the input folder
		Scanner scan = new Scanner(System.in);
		SecurityLogManager instance = null;
		while ( instance == null ) {
			System.out.print("Input Rental File name: ");
			String input = scan.nextLine();
			try { 
				instance = new SecurityLogManager(input);
			} catch ( IllegalArgumentException e ) {
				System.out.println("Invalid file name, try something in the form: \"parentfolder/filename.txt\"");
			}
		}
		System.out.println( "Welcome to the Security Log Manager. To review the commands, please use the command \"help\"");
		System.out.println();
		String command = "";
		while( !command.equals("quit") ) {
			System.out.print("Enter command: ");
			command = scan.nextLine().toLowerCase();
			if(command.equals("profile")) {
				String start = "";
				String end = "";
				while ( start.equals("") && end.equals("") ) {
					try {
						System.out.print("Enter start time: ");
						start = scan.nextLine();	
						System.out.print("Enter end time: ");
						end = scan.nextLine();			
						System.out.println(instance.generateOperationalProfile(start, end));
					} catch ( IllegalArgumentException e) {
						System.out.println();
						System.out.println("Invalid time, please enter in the form: MM/DD/YYYY HH:MM:SSXM");
						start = "";
						end = "";
					} catch (StringIndexOutOfBoundsException e) {
						System.out.println();
						System.out.println("Invalid time, please enter in the form: MM/DD/YYYY HH:MM:SSXM");
						start = "";
						end = "";
					}
				}
			} else if (command.equals("report")) {
				String username = "";
				while ( username.equals("") ) {
					System.out.print("Enter username: ");
					try {
						username = scan.nextLine();
						System.out.println(instance.getUserReport(username));
					} catch ( IllegalArgumentException e) {
						System.out.println("Invalid username, please try again");
						username = "";
					}
				}
			} else if (command.equals("quit")) {
				System.out.println("Thank you for using the Security Log Manager.");
			} else if (command.equals("help")) {
				System.out.println("Command Options:");
				System.out.println("Profile - generate an Operational Profile between two times");
				System.out.println("Report - generate a User Report for a particular username");
				System.out.println("Quit - exit the Security Log Manager");
				System.out.println();
			} else {
				System.out.println("Invalid Command");
			}
		}
		scan.close();
	}
}

