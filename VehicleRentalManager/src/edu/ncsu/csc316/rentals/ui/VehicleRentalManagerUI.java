package edu.ncsu.csc316.rentals.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.ncsu.csc316.rentals.manager.VehicleRentalManager;

/**
 * Main User view class to accept input
 * @author Ryan Alexander
 *
 */
public class VehicleRentalManagerUI {
	/**
	 * The main program
	 * @param args arguments
	 */
	public static void main(String [] args) {
		//Valid file examples can be found in the input folder
		Scanner scan = new Scanner(System.in);
		VehicleRentalManager program = null;
		while ( program == null ) {
			System.out.print("Input Rental File name: ");
			String rentals = scan.nextLine();
			try { 
				program = new VehicleRentalManager(rentals);
			} catch ( IllegalArgumentException e ) {
				System.out.println("Invalid file name, try something in the form: \"parentfolder/filename.csv\"");
			}
		}
		System.out.println( "Welcome to the Vehicle Rental Manager! To review the commands, please use the command \"help\"");
		System.out.println();
		String command = "";
		while( !command.equals("quit") ) {
			System.out.print("Enter command: ");
			command = scan.nextLine().toLowerCase();
			if(command.equals("rentals")) {
				int start = -1;
				while ( start == -1 ) {
					System.out.print("Enter start day: ");
					try {
						start = scan.nextInt();						scan.nextLine();
					} catch ( InputMismatchException e) {
						System.out.println();
						System.out.println("Invalid day, please enter as an integer number");
						scan.nextLine();
					}
				}
				int end = -1;
				while ( end == -1 ) {
					System.out.print("Enter end day: ");
					try {
						end = scan.nextInt();
						scan.nextLine();
					} catch ( InputMismatchException e) {
						System.out.println();
						System.out.println("Invalid day, please enter as an integer number");
						scan.nextLine();
					}
				}
				System.out.println(program.getRentals(start, end));
			} else if (command.equals("day")) {
				int start = -1;
				while ( start == -1 ) {
					System.out.print("Enter day: ");
					try {
						start = scan.nextInt();
						scan.nextLine();
					} catch ( InputMismatchException e) {
						System.out.println("Invalid day, please enter as an integer number");
						scan.nextLine();
					}
				}
				System.out.println(program.getRentalsForDay(start));
			} else if (command.equals("quit")) {
				System.out.println("Thank you for using the Vehicle Rental Manager!");
			} else if (command.equals("help")) {
				System.out.println("Command Options:");
				System.out.println("Rentals - view the cheapest rental option available from a start to end day");
				System.out.println("Day - view a list of rentals available starting on a given day");
				System.out.println("Quit - exit the Vehicle Rental Manager");
				System.out.println();
			} else {
				System.out.println("Invalid Command");
			}
		}
		scan.close();
	}
}
