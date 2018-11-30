package edu.ncsu.csc316.rentals.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc316.rentals.data.Rental;
import edu.ncsu.csc316.rentals.graph.AdjacencyList;
import edu.ncsu.csc316.rentals.graph.Edge;
import edu.ncsu.csc316.rentals.graph.Vertex;

/**
 * Reads in the file from the user and constructs a graph
 * Based on the provided file reader and loosely based on Project 2
 * @author Ryan Alexander
 *
 */
public class RentalReaderIO {
	
	/** Line to be discarded from files */
	private static final String DISCARD = "START_DAY,END_DAY,COST,MAKE,MODEL";
	
	/**
	 * Reads in the rentals from file
	 * @param filename the filepath
	 * @return resumes a graph of resumes
	 * @throws FileNotFoundException
	 */
	public static AdjacencyList readRentals(String filename) throws FileNotFoundException {
		AdjacencyList rentals = new AdjacencyList();
		int start = 0;
		int end = 0;
		int cost = 0;
		String make = "";
		String model = "";
		Scanner linescan = null;
		try(Scanner scan = new Scanner(new FileInputStream(filename), "UTF8"))
		{
		    while(scan.hasNextLine())
		    {
		    	String line = scan.nextLine().trim();
		    	if (line.isEmpty())
	        		break;
		        if(line.contains(DISCARD)) {
		        	line = scan.nextLine().trim();
		        }
		        
		        linescan = new Scanner(line);
		        while(linescan.hasNext()) {
		        	if(line.isEmpty())
			        	break;
		        	linescan.useDelimiter(",");
		        	if(linescan.hasNextInt())
		        		start = linescan.nextInt();
		        	if(linescan.hasNextInt())
		        		end = linescan.nextInt();
		        	if(linescan.hasNextInt())
		        		cost = linescan.nextInt();
		        	if(linescan.hasNext())
		        		make = linescan.next().trim();
		        	if(linescan.hasNext()) {
		        		model = linescan.next().trim();
		        		Rental rental = new Rental(start, end, cost, make, model);
		        		Vertex first = new Vertex(start);
		        		Vertex second = new Vertex(end);
		        		if(start > rentals.getGreatest()) {
		        			rentals.insertVertex(first);
		        			first = rentals.getVertex(start);
		        		} else if(rentals.getVertex(start) == null) {
		        			rentals.insertVertex(first);
		        			first = rentals.getVertex(start);
		        		} else {
		        			first = rentals.getVertex(start);
		        		}
		        		if(end > rentals.getGreatest()) {
		        			rentals.insertVertex(second);
		        			second = rentals.getVertex(end);
		        		} else if(rentals.getVertex(end) == null) {
		        			rentals.insertVertex(second);
		        			second = rentals.getVertex(end);
		        		} else {
		        			second = rentals.getVertex(end);
		        		}
		        		Edge edge = new Edge(rental, first, second);
		        		rentals.insertEdge(edge);
		        		break;
		        	}
		        }
		        linescan.close();
		    }
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("No Element");
		}
		return rentals;
	}
}
