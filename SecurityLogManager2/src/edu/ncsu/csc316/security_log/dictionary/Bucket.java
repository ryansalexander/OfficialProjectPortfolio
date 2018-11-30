package edu.ncsu.csc316.security_log.dictionary;

/**
 * Used to hold data for separate chaining
 * @author Ryan Alexander
 *
 * @param <E>
 */
public class Bucket<E> {
	/** The object */
	private E data;
	/** The next bucket */
	private Bucket<E> next;
	/**
	 * Constructs a new bucket
	 * @param data the object
	 * @param next the next bucket
	 */
	public Bucket(E data, Bucket<E> next) {
		setData(data);
		setNext(next);
	}

	/**
	 * Gets the data
	 * @return data
	 */
	public E data() {
		return data;
	}

	/**
	 * Sets the data
	 * @param data the data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Gets the next bucket
	 * @return next
	 */
	public Bucket<E> next() {
		return next;
	}

	/**
	 * Sets the next bucket
	 * @param next the next
	 */
	public void setNext(Bucket<E> next) {
		this.next = next;
	}
	
}
