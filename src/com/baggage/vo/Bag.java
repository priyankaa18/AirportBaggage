package com.baggage.vo;

/*
 * Class to represent Bag entity
 */
public class Bag {

	private String id;
	private String entryPointOfBag;
	private String flightToBeSent;
	
	public Bag(String id, String entryPointOfBag, String flightToBeSent) {
		super();
		this.id = id;
		this.entryPointOfBag = entryPointOfBag;
		this.flightToBeSent = flightToBeSent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntryPointOfBag() {
		return entryPointOfBag;
	}

	public void setEntryPointOfBag(String entryPointOfBag) {
		this.entryPointOfBag = entryPointOfBag;
	}

	public String getFlightToBeSent() {
		return flightToBeSent;
	}

	public void setFlightToBeSent(String flightToBeSent) {
		this.flightToBeSent = flightToBeSent;
	}
	
}
