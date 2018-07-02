package com.baggage.service;

import java.util.List;

import com.baggage.vo.Edge;

public interface Algo {
	
	public String shortestPath(String start, String destination, List<Edge> edgelist); 

}
