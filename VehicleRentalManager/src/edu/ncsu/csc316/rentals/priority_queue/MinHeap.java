package edu.ncsu.csc316.rentals.priority_queue;

import edu.ncsu.csc316.rentals.graph.Vertex;
import edu.ncsu.csc316.rentals.list.ArrayBasedList;

/**
 * MinHeap used in Dijkstra's algorithm, most of the methods are based
 * on those which are found in the PriorityQueues slides
 * @author Ryan Alexander
 *
 */
public class MinHeap implements PriorityQueue {

	/** Number of elements in the heap */
	private int size;
	/** The heap as an arraylist implementation */
	private ArrayBasedList<Vertex> heap;
	
	/**
	 * Constructs a new MinHeap
	 */
	public MinHeap() {
		size = 0;
		heap = new ArrayBasedList<Vertex>();
	}
	
	@Override
	public void insert(Vertex v) {
		heap.insert(v);
		v.setIndex(size);
		size++;
		upHeap(size - 1);
	}

	@Override
	public Vertex deleteMin() {
		Vertex v = heap.get(0);
		v.setIndex(-1);
		size--;
		swap(0, size);
		downHeap(0);
		return v;
	}

	@Override
	public void updatePriority(Vertex v, int priority) {
		int index = v.getIndex();
   		heap.get(index).setWeight(priority);
		Vertex parent = heap.get((index - 1) / 2);
		if (parent.getWeight() > priority) {
			upHeap(index);
		} else {
			downHeap(index);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Helper method for insert
	 * @param i position in the heap
	 */
	public void upHeap(int i) {
		if (i > 0 && (heap.get((i - 1) / 2).getWeight() > heap.get(i).getWeight())) {
			swap((i - 1) / 2, i);
			upHeap((i - 1) / 2);
		}
	}
	
	/**
	 * Helper method for deleteMin
	 * @param m position in the heap
	 */
	public void downHeap(int m) {
		int i = 0;
		if ( (2 * m + 2) < size) {
			if ((heap.get(2 * m + 2).getWeight()) <= (heap.get(2 * m + 1).getWeight())) {
				i = 2 * m + 2;
			} else {
				i = 2 * m + 1;
			}
		} else if ((2 * m + 1) < size) {
			i = 2 * m + 1;
		}
		
		if (i > 0 && (heap.get(m).getWeight() > heap.get(i).getWeight())) {
			swap(m, i);
			downHeap(i);
		}
	}
	
	/**
	 * Used to swap two positions in the heap arraylist
	 * @param a the first idx
	 * @param b the second idx
	 */
	public void swap(int a, int b) {
		Vertex temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.get(a).setIndex(a);
		heap.set(b, temp);
		heap.get(b).setIndex(b);
	}
	
	/**
	 * Gets the info in the heap
	 * @return heap the data in the heap
	 */
	public ArrayBasedList<Vertex> getHeap() {
		return heap;
	}

}
