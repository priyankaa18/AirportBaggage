package com.baggage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.baggage.exception.GenericException;
import com.baggage.vo.Edge;
import com.baggage.vo.Node;

/*
 * This class contains the logic to instantiate graph which contains vertices 
 * and its metadata like neighbours and minimum time to travel to other node
 * and contains actual routing logic based on edge relaxation using heap 
 */
public class RoutingService {

	private  Map<String,Node> graph;

	/*
	 * constructor to instantiate graph and establish each node with its neighbours
	 */
	public RoutingService(List<Edge> edges) {
		
		this.graph = new HashMap<String, Node>();
		
		for(Edge e : edges){
			
			if(!graph.containsKey(e.getSource().getId())){
				graph.put(e.getSource().getId(), new Node(e.getSource().getId()));
			}
			if(!graph.containsKey(e.getDestination().getId())){
				graph.put(e.getDestination().getId(), new Node(e.getDestination().getId()));
			}
		}
		
		for(Edge e : edges){
			Node node = graph.get(e.getSource().getId());
			Map<Node, Integer> neighbouringNodes = node.getNeighbouringNodes();
			neighbouringNodes.put(graph.get(e.getDestination().getId()), e.getTime());
		}
	}
	
	/*
	 * Constructs Treeset based on the source node 
	 * and adds it the queue
	 */
	public void routing(String source){
		if(!graph.containsKey(source)){
			throw new GenericException("The graph does not contain vertex named "+source); 
		}
		
		Node node = graph.get(source);
		NavigableSet<Node> queue = new TreeSet<>();
		
		for(Node n : graph.values()){
			n.setParentVertex(n == node ? node : null);
			n.setMinTimeToReachThisNode(n == node ? 0 : Integer.MAX_VALUE);
			queue.add(n);
		}
		
		shortestPath(queue);
	}
	
	/*
	 * This is the method which relaxes all the edges from the source
	 * which is placed at the beginning of the treeset
	 * and gives the shortest rout value and path
	 */
	private void shortestPath(NavigableSet<Node> queue){
		Node source,neighbour;
		
		while(!queue.isEmpty()){
			source = queue.pollFirst();
			
			if(source.getMinTimeToReachThisNode() == Integer.MAX_VALUE){
				break;
			}
			
			for(Map.Entry<Node, Integer> nodes : graph.get(source.getId()).getNeighbouringNodes().entrySet()){
				neighbour = nodes.getKey();
				
				int anothertime = source.getMinTimeToReachThisNode() + nodes.getValue();
				if(anothertime < neighbour.getMinTimeToReachThisNode()){
					queue.remove(neighbour);
					neighbour.setMinTimeToReachThisNode(anothertime);
					neighbour.setParentVertex(source);
					queue.add(neighbour);
				}
			}
		}
	}
	
	/*
	 * This method calls the getShortestPathTo method present in node class 
	 * and constructs the required path 
	 */
	public List<Node> getShortestPath(String endName){
		if(!graph.containsKey(endName)){
			throw new GenericException("Graph do not have end vertex "+endName);
		}
		return graph.get(endName).getShortestPathTo();
	}
	
}
