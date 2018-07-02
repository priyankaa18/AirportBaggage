package com.baggage.vo;

public class Edge {

	private Node source;
	private Node destination;
	private int time;//time to reach from source to destination

	public Edge(Node source, Node destination, int time) {
		this.source = source;
		this.destination = destination;
		this.time = time;
	}
	
	public Edge(String sourceName, String destinationName, Integer time) {
        this.source = new Node(sourceName);
        this.destination = new Node(destinationName);
        this.time = time;
    }
	

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
}
