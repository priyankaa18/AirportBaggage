package com.baggage.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.baggage.vo.Edge;
import com.baggage.vo.Node;

public class AlgoImpl implements Algo{
	
	private final String SPACE = " ";
	
	Map<String,RoutingService> visited = new ConcurrentHashMap<>();

	/*
	 * This method actually returns the formatted output according to the requirement
	 * params are source of the baggage point
	 * 			  destination is the destination point to be reached 
	 * 			  edgelist is the list of path between conveyour belt		
	 */
	@Override
	public String shortestPath(String start, String destination, List<Edge> edgelist) {
		
		RoutingService graph;
		if(visited.containsKey(start)){
			graph = visited.get(start);
			System.out.println("this is a class");
		}else{
			graph = new RoutingService(edgelist);
			graph.routing(start);
			visited.put(start, graph);
			
		}
		List<Node> shortestPathList = graph.getShortestPath(destination);
		return generateOutput(shortestPathList);
	}

	/*
	 * used for formatting the output according to the requirement
	 */
	private String generateOutput(List<Node> shortestPathList) {
		
		StringBuffer buf = new StringBuffer();
		for(Node n : shortestPathList){
			buf.append(n.getId()).append(SPACE);
		}
		buf.append(": ").append(shortestPathList.get(shortestPathList.size()-1).getMinTimeToReachThisNode());
		return buf.toString();
	}

}
