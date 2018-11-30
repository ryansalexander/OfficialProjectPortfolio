package edu.ncsu.csc316.security_log.dictionary;
import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

/**
 * HashTable that uses golden ratio compression as seen in slides
 * and separate chaining (v2) as seen in slides
 * @author Ryan Alexander
 *
 * @param <E>
 */
public class HashTable<E> {
	/** The number of elements */
	private int size;
	/** The max cap */
	private int capacity;
	/** Array which contains the elements */
	private Bucket<E>[] table;
	/** Initial capacity */
	private static final int INITIAL = 300;
	/** Maximum load factor */
	private static final double LOAD = .55;
	/** Factor used for golden ratio */
	private static final double PHI = (1 + sqrt(5)) / 2;
	
	/**
	 * Constructs a new generic HashTable with
	 * some initial default capacity
	 */
	public HashTable()
	{
	   this(INITIAL);
	}
	
	/**
	 * Used if a different capacity is needed
	 * @param cap different cap
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int cap) {
		 table = (Bucket<E>[])new Bucket[cap];
		 size = 0;
		 setCapacity(cap);
	}


	/**
	 * Inserts the generic value E into the hash table
	 *
	 * @param value - the value to insert into the hash table
	 */
	public void insert(E value)
	{
	    int idx = (int) floor(capacity * (value.hashCode() * 1 / PHI - floor(value.hashCode() * 1 / PHI)));
	    if(table[idx] == null) {
	    	table[idx] = new Bucket<E>(value, null);
	    } else {
	    	Bucket<E> temp = new Bucket<E>(value, table[idx]);
	    	table[idx] = temp;
	    }
	    size++;
	    double tempSize = this.size();
	    double tempCap = this.getHashTableLength();
	    if ((tempSize / tempCap) > LOAD)
	    	this.rehash();
	}


	/**
	 * Finds the value E in
	 * the hash table. Returns the value E
	 * if the value was found in the hash table.
	 * If the value is not in the hash table, return null.
	 *
	 * @param value - the value to search for in the hash table
	 * @return the reference to the value in the hash table, or null if the value 
	 *              is not in the hash table
	 */
	public E lookUp(E value)
	{
		int idx = (int) floor(capacity * (value.hashCode() * 1 / PHI - floor(value.hashCode() * 1 / PHI)));
		Bucket<E> current = table[idx];
		while(current != null) {
			if (current.data().equals(value))
				return current.data();
			current = current.next();
		}
		return null;
	}

	/**
	 * Returns the number of values in the hash table
	 * 
	 * @return the number of values in the hash table
	 */
	public int size()
	{
	    return size;
	}

	/**
	 * Returns the length/capacity of the hash table
	 * 
	 * @return the length/capacity of the hash table
	 */
	public int getHashTableLength()
	{
	    return capacity;
	}
	
	/**
	 * Rehashes the table if it surpasses load factor
	 */
	@SuppressWarnings("unchecked")
	public void rehash() {
		Bucket<E>[] temp = table;
		this.setCapacity(this.getHashTableLength() * 2 + 1);
		table = (Bucket<E>[])new Bucket[this.getHashTableLength()];
		size = 0;
		for (int i = 0; i < temp.length; i++) {
			if(temp[i] != null) {
				Bucket<E> current = temp[i];
				this.insert(current.data());
				current = current.next();
				while(current != null) {
					this.insert(current.data());
					current = current.next();
				}
			}
		}
	}

	/**
	 * Sets the capacity
	 * @param capacity the max
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
