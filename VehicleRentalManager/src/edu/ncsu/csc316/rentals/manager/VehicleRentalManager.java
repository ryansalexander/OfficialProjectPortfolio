package edu.ncsu.csc316.rentals.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.rentals.graph.AdjacencyList;
import edu.ncsu.csc316.rentals.graph.Edge;
import edu.ncsu.csc316.rentals.graph.Vertex;
import edu.ncsu.csc316.rentals.io.RentalReaderIO;
import edu.ncsu.csc316.rentals.list.ArrayBasedList;
import edu.ncsu.csc316.rentals.priority_queue.MinHeap;
import edu.ncsu.csc316.rentals.priority_queue.PriorityQueue;

/**
 * The main class that handles the program
 * @author Ryan Alexander
 *
 */
public class VehicleRentalManager {
	
	/** The main graph for the project */
	private AdjacencyList rentals;
	
	/**
	 * Constructs a new Rental manager with the given input files
	 * 
	 * @param pathToFile
	 *            - the path to the employee input file
	 */
	public VehicleRentalManager(String pathToFile) {
	    try {
			rentals = RentalReaderIO.readRentals(pathToFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Invalid file name");
		}
	}
	
	/**
	 * Returns the String representation of the rentals that
	 * minimize the total cost from the start day to the end day
	 * (or for as many days from the start day while rentals are possible).
	 * 
	 * @param start - the start day as an integer
	 * @param end - the end day as an integer
	 * @return the String representation of the rentals
	 */
	public String getRentals(int start, int end) {
		dijkstras(rentals.getVertex(start));
		Vertex current = null;
		int count = end;
		String none = "";
		if(end > rentals.getGreatest()) {
			current = rentals.getVertex(rentals.getGreatest());
			count = rentals.getGreatest();
			none = "   No rentals available on day " + current.getDay() + "\n";
		} else {
			current = rentals.getVertex(end);
		}
		while(current.getParent() == null) {
			current = rentals.getVertex(count--);
			if (current.getParent() != null)
				none = "   No rentals available on day " + current.getDay() + "\n";
		}
		int total = rentals.getVertex(current.getDay()).getWeight();
		String str = "Rental total is $" + total + ".00\n[\n";
		String temp = "";
		while(current.getParent() != null) {
			String line = "   From day " + current.getParent().getDay() +
					" to day " + current.getDay() + ": $" + current.getEdge().getWeight() +
					".00, " + current.getEdge().getRental().getMake() + " " +
					current.getEdge().getRental().getModel() + "\n";
			line += temp;
			temp = line; 
			current = current.getParent();
		}
		str += temp + none + "]";
	    return str;
	}
	/**
	 * Helper method for finding the shortest path
	 * Based on Dijkstra's algorithm found in the Shortest path slides
	 * @param s the start vertex in the graph
	 */
	public void dijkstras(Vertex s) {
		PriorityQueue q = new MinHeap();
		for (int i = 0; i < rentals.getGreatest(); i++) {
			Vertex v = rentals.vertices().get(i);
			if (v.equals(s)) {
				v.setWeight(0);
			} else {
				v.setWeight(100000);
			}
			v.setFound(false);
			v.setParent(null);
			q.insert(v);
		}
		while (!q.isEmpty()) {
			Vertex u = q.deleteMin();
			u.setFound(true);
			for (int i = 0; i < rentals.getIncidentEdges(u).size(); i++) {
				Edge e = rentals.getIncidentEdges(u).get(i);
				Vertex z = rentals.getOpposite(u, e);
				int r = e.getWeight() + u.getWeight();
				if (r < z.getWeight()) {
					z.setEdge(e);
					z.setWeight(r);
					z.setParent(u);
					q.updatePriority(z, r);
				}
			}
		}
	}
	
	/**
	 * Returns the String representation of the rentals 
	 * that are available for the requested day.
	 * 
	 * @param day - the day for which to retrieve available rentals
	 * @return the String representation of the rentals
	 */
	public String getRentalsForDay(int day) {
		Vertex v = new Vertex(day);
		ArrayBasedList<Edge> edges = rentals.getIncidentEdges(v);
		edges = mergeSort(edges);
		String str = "Available rentals for day " + day + "\n";
		if(edges.isEmpty()) {
			str += "   No rentals available.\n";
		}
		for(int i = 0; i < edges.size(); i++) {
			str += "   $" + edges.get(i).getWeight() + ".00 " + edges.get(i).getRental().getMake() +
					" " + edges.get(i).getRental().getModel() + " for day " + day + " to day " +
					edges.get(i).getLast().getDay() + "\n";
		}
		str += "]";
	    return str;
	}
	
	/**
	 * Sorter for ordering output
	 * @param edges list of edges to be sorted
	 * @return edges sorted list
	 */
	public ArrayBasedList<Edge> mergeSort(ArrayBasedList<Edge> edges) {
		if(edges.size() > 1) {
			int mid = edges.size() / 2 - 1;
			ArrayBasedList<Edge> left = new ArrayBasedList<Edge>();
			ArrayBasedList<Edge> right = new ArrayBasedList<Edge>();
			//left
			for(int i = 0; i <= mid; i++) {
				left.add(i, edges.get(i));
			}
			//right
			for(int i = mid + 1; i < edges.size(); i++) {
				right.add(i - (mid + 1), edges.get(i));
			}
			left = mergeSort(left);
			right = mergeSort(right);
			edges = merge(edges, left, right);
		}
		return edges;
	}
	
	/**
	 * Helper method for mergesort
	 * @param edges the main list
	 * @param left the left half to merge 
	 * @param right the right half to merge
	 * @return the new merged list
	 */
	public ArrayBasedList<Edge> merge(ArrayBasedList<Edge> edges, ArrayBasedList<Edge> left, ArrayBasedList<Edge> right) {
		int l = 0;
		int r = 0;
		for(int i = 0; i < edges.size(); i++) {
			if(r >= right.size() || (l < left.size() && left.get(l).getWeight() <= right.get(r).getWeight())) {
				edges.set(i, left.get(l));
				l += 1;
			} else {
				edges.set(i, right.get(r));
				r += 1;
			}
		}
		return edges;
	}
	
}
