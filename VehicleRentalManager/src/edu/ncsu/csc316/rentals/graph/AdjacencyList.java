package edu.ncsu.csc316.rentals.graph;

import edu.ncsu.csc316.rentals.list.ArrayBasedList;

/**
 * Constructs and adjacency list to represent the rental graph
 * @author Ryan Alexander
 *
 */
public class AdjacencyList implements Graph {

	/** Holds the info for the AdjacencyList */
	private ArrayBasedList<Vertex> list;
	
	/** The size */
	private int greatest;
	
	/**
	 * Constructs the adjacency list
	 */
	public AdjacencyList() {
		list = new ArrayBasedList<Vertex>();
		greatest = 0;
	}

	@Override
	public void insertVertex(Vertex v) {
		if(v.getDay() - greatest > 0) {
			for(int i = greatest; i < v.getDay() - 1; i++)
				list.add(i, null);
			greatest = v.getDay();
			list.add(v.getDay() - 1, v);
		}
		list.set(v.getDay() - 1, v);
	}

	@Override
	public void insertEdge(Edge e) {
		Vertex check = e.getLast();
		int val = check.getDay();
		Vertex temp = new Vertex(val);
		Vertex first = e.getFirst();
		int day = first.getDay();
		Vertex next = this.getVertex(day).getNext();
		list.get(e.getFirst().getDay() - 1).setNext(temp);
		list.get(e.getFirst().getDay() - 1).getNext().setNext(next);
		list.get(e.getFirst().getDay() - 1).getNext().setEdge(e);
		list.get(e.getFirst().getDay() - 1).getNext().setWeight(e.getWeight());
	}

	@Override
	public ArrayBasedList<Edge> getIncidentEdges(Vertex v) {
		ArrayBasedList<Edge> edges = new ArrayBasedList<Edge>();
		Vertex temp = list.get(v.getDay() - 1);
		while(temp.getNext() != null) {
			temp = temp.getNext();
			edges.insert(temp.getEdge());
		}
		return edges;
	}

	@Override
	public Vertex getOpposite(Vertex v, Edge e) {
		if ( v.getDay() == e.getFirst().getDay()) {
			return this.getVertex(e.getLast().getDay());
		} else if (v.getDay() == e.getLast().getDay()) {
			return this.getVertex(e.getFirst().getDay());
		} else {
			return null;
		}
	}

	@Override
	public ArrayBasedList<Vertex> vertices() {
		return list;
	}

	@Override
	public Vertex getVertex(int day) {
		return list.get(day - 1);
	}

	@Override
	public Edge getEdge(Vertex v1, Vertex v2, int weight) {
		Vertex start = list.get(v1.getDay() - 1);
		Vertex temp = start;
		while(temp.getNext() != null) {
			temp = temp.getNext();
			if (temp.getDay() == v2.getDay() && temp.getWeight() == weight) {
				return temp.getEdge();
			}
		}
		return null;
	}
	
	/**
	 * Returns greatest day in the list
	 * @return greatest the highest day
	 */
	public int getGreatest() {
		return greatest;
	}
	
}
