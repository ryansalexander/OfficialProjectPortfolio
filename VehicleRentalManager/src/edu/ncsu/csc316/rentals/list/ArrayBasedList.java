package edu.ncsu.csc316.rentals.list;

/**
 * Data structure for storing ordered values
 * Based on the ArrayList class which can be found on pages 260, 261, and 264
 * of the textbook
 * @author Ryan Alexander
 *
 * @param <E>
 */
public class ArrayBasedList<E> {
	/** Initial capacity */
	public static final int CAPACITY = 10;
	/** Stores the data of the list */
	private E[] data;
	/** size of the list */
	private int size;
	
	/**
	 * Constructs the list if not capacity given
	 */
	public ArrayBasedList() {
		this(CAPACITY);
	}
	
	/**
	 * Constructs list with given capacity
	 * @param capacity provided initial capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) new Object[capacity];
		size = 0;
	}
	
	/**
	 * Returns object an index
	 * @param idx index of the object
	 * @return E object at index
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public E get(int idx) throws IndexOutOfBoundsException {
		checkIndex(idx, size);
		return data[idx];
	}
	
	/**
	 * Adds an object at the specified index
	 * @param idx the given index
	 * @param e the object
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public void add(int idx, E e) throws IndexOutOfBoundsException {
		checkIndex(idx, size + 1);
		if (size == data.length)
			growArray();
		for(int i = size - 1; i >= idx; i--) {
			data[i + 1] = data[i];
		}
		data[idx] = e;
		size++;
	}
	
	/**
	 * Sets an object at the specified index
	 * @param idx the index
	 * @param e the object
	 * @return E the object
	 * @throws IndexOutOfBoundsException
	 */
	public E set(int idx, E e) throws IndexOutOfBoundsException {
		checkIndex(idx, size);
		E temp = data[idx];
		data[idx] = e;
		return temp;
	}
	
	/**
	 * Removes an object at the specified index
	 * @param idx the index
	 * @return E the removed object
	 * @throws IndexOutOfBoundsException
	 */
	public E remove(int idx) throws IndexOutOfBoundsException {
		checkIndex(idx, size);
		E temp = data[idx];
		for(int i = idx; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		data[size - 1] = null;
		size--;
		return temp;
	}

	/**
	 * size of the list
	 * @return size the list's size
	 */
	public int size() {
		return size;
	}

	/**
	 * Checks if list is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Adds object to next available index
	 * @param e the object
	 */
	public void insert(E e) {
		checkIndex(size, size + 1);
		if (size == data.length)
			growArray();
		data[size] = e;
		size++;
	}
	


	/**
	 * Check if index is valid
	 * @param i the index
	 * @param n max index
	 * @throws IndexOutOfBoundsException
	 */
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Invalid Index");
	}

	/**
	 * Grows the array when size is maxed
	 */
	protected void growArray() {
		int n = size * 2;
		@SuppressWarnings("unchecked")
		E[] temp = (E[]) new Object[n];
		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}
}

