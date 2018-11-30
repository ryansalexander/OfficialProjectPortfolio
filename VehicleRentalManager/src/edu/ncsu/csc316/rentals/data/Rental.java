package edu.ncsu.csc316.rentals.data;
/**
 * The rental period and cost for a vehicle
 * @author Ryan Alexander
 *
 */
public class Rental {
	/** The start day of the rental */
	private int startDay;
	/** The end day of the rental */
	private int endDay;
	/** The cost of the rental */
	private int cost;
	/** The make of the vehicle */
	private String make;
	/** The model of the vehicle */
	private String model;
	
	/**
	 * Creates the rental object
	 * @param start the start day
	 * @param end the end day
	 * @param cost the cost
	 * @param make the vehicle make
	 * @param model the vehicle model
	 */
	public Rental(int start, int end, int cost, String make, String model) {
		setStartDay(start);
		setEndDay(end);
		setCost(cost);
		setMake(make);
		setModel(model);
	}

	/**
	 * Gets the start day
	 * @return the startDay
	 */
	public int getStartDay() {
		return startDay;
	}

	/**
	 * Sets the start day
	 * @param startDay the startDay to set
	 */
	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	/**
	 * Gets the end day
	 * @return the endDay
	 */
	public int getEndDay() {
		return endDay;
	}

	/**
	 * Sets the end day
	 * @param endDay the endDay to set
	 */
	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	/**
	 * Gets the cost
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Sets the cost 
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Gets the make
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Sets the make
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * Gets the model
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
}
