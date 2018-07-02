package com.baggage.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node>{

	private String id;
	private int minTimeToReachThisNode = Integer.MAX_VALUE;
	private Node parentVertex = null;
	private Map<Node,Integer> neighbouringNodes = new HashMap<>();
	
	public Node(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMinTimeToReachThisNode() {
		return minTimeToReachThisNode;
	}

	public void setMinTimeToReachThisNode(int minTimeToReachThisNode) {
		this.minTimeToReachThisNode = minTimeToReachThisNode;
	}

	public Node getParentVertex() {
		return parentVertex;
	}

	public void setParentVertex(Node parentVertex) {
		this.parentVertex = parentVertex;
	}

	public Map<Node, Integer> getNeighbouringNodes() {
		return neighbouringNodes;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setNeighbouringNodes(Map<Node, Integer> neighbouringNodes) {
		this.neighbouringNodes = neighbouringNodes;
	}

	/*
	 * this comparison is used to arrange nodes based on minTimeToReachThisNode
	 * in treeset
	 */
	@Override
	public int compareTo(Node o) {
		
		if (minTimeToReachThisNode == o.minTimeToReachThisNode)
            return id.compareTo(o.id);

        return Integer.compare(minTimeToReachThisNode, o.minTimeToReachThisNode);
	}
	
	public List<Node> getShortestPathTo(){
		
		List<Node> path = new ArrayList<>();
		path.add(this);
		Node node = this.getParentVertex();
		
		while(node != null && !path.contains(node)){
			path.add(node);
			node = node.getParentVertex();
		}
		
		Collections.reverse(path);
		return path;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", minTimeToReachThisNode=" + minTimeToReachThisNode + "]";
	}
	
}
