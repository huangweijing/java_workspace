package com.weijinglab.lib.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	
	Integer v;
	Integer e;
	
	public List<List<Edge>> edges;
	
	public interface TraverseAction {
		public void doSth(Integer node);
	}

	public Graph(Integer v) {
		this.e = 0;
		this.v = v;
		edges = new ArrayList<List<Edge>>();
		for(int i=0; i<v; i++) {
			edges.add(new ArrayList<Edge>());
		}
	}
	
	public void addEdge(Integer src, Integer dest, Integer weight) {
		this.edges.get(src).add(new Edge(src, dest, weight));
		this.edges.get(dest).add(new Edge(dest, src, weight));
		this.e++;
	}
	
	public void addEdge(Edge e) {
		this.edges.get(e.from).add(new Edge(e.from, e.to, e.weight));
		this.edges.get(e.to).add(new Edge(e.to, e.from, e.weight));
		this.e++;
	}
	
	public void bfs(TraverseAction action, int from) {
		Queue<Integer> nodeQueue = new LinkedList<Integer>();
		boolean visited[] = new boolean[this.v];
		nodeQueue.add(from);
		visited[from] = true;
		
		while(!nodeQueue.isEmpty()) {
			Integer node = nodeQueue.poll();
			action.doSth(node);
			List<Edge>adjacentEdgeList = this.edges.get(node);
			for(Edge adjacentEdge : adjacentEdgeList) {
				if(!visited[adjacentEdge.to]) {
					nodeQueue.add(adjacentEdge.to);
					visited[adjacentEdge.to] = true;
				}
			}
		}
	}
	
	public void dfs(TraverseAction action, int from) {
		Stack<Integer> nodeStack = new Stack<Integer>();
		boolean visited[] = new boolean[this.v];
		nodeStack.add(from);
		visited[from] = true;
		
		while(!nodeStack.isEmpty()) {
			Integer node = nodeStack.pop();
			action.doSth(node);
			List<Edge>adjacentEdgeList = this.edges.get(node);
			for(Edge adjacentEdge : adjacentEdgeList) {
				if(!visited[adjacentEdge.to]) {
					nodeStack.add(adjacentEdge.to);
					visited[adjacentEdge.to] = true;
				}
			}
		}
	}
	
	public Graph getKruscalMST() {
		Graph g = new Graph(this.v);
		
		List<Edge> list = new ArrayList<>();
		for(List<Edge> edgeList : edges) {
			list.addAll(edgeList);
		}
		Collections.sort(list);
		
		int[] rootArr = init(this.v);
		for(Edge edge : list) {
			if(getRoot(edge.from, rootArr) != getRoot(edge.to, rootArr)) {
				union(edge.from, edge.to, rootArr);
				g.addEdge(edge);
				System.out.println(Arrays.toString(rootArr));
			}
		}
		
		return g;
	}
	
	public Graph getPrimMST() {
		Graph g = new Graph(this.v);
		boolean[] visited = new boolean[this.v];
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(0, 0, 0));

		while(!q.isEmpty()) {
			Edge e = q.poll();
			if(visited[e.to]) {
				continue;
			}
			visited[e.to] = true;
			g.addEdge(e);
			List<Edge> list = this.edges.get(e.to);
			for(Edge adjEdge : list) {
				if(visited[adjEdge.to])
					continue;
				q.add(adjEdge);
			}
		}
		
		return g;
	}
	
	static class Distance implements Comparable<Distance> {
		
		public int vertex;
		public int distance;
		
		public Distance(int v, int d) {
			this.vertex = v;
			this.distance = d;
		}

		@Override
		public int compareTo(Distance o) {
			return this.distance - o.distance;
		}
	}
	
	/**
	 * Dijkstra Shortest Path Algorithm
	 * @param source
	 * @return
	 */
	public int[] getDijkstraSP(int source) {
		int[] distance = new int[this.v];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[source] = 0;
		Queue<Distance> q = new LinkedList<>();//new PriorityQueue<>();
		q.add(new Distance(source, 0));
		
		boolean[] visited = new boolean[this.v];
				
		while(!q.isEmpty()) {
			Distance e = q.poll();
			if(visited[e.vertex]) {
				continue;
			}
			visited[e.vertex] = true;
			
			List<Edge> list = this.edges.get(e.vertex);
			for(Edge adjEdge : list) {
				if(distance[adjEdge.from] + adjEdge.weight < distance[adjEdge.to]) {
					distance[adjEdge.to] = distance[adjEdge.from] + adjEdge.weight;
					q.add(new Distance(adjEdge.to, distance[adjEdge.to]));
				}	
			}
		}
		
		return distance;
	}
    static String timeConversion(String s) {
        /*
         * Write your code here.
         */
		Integer hour = Integer.valueOf(s.substring(0, 2));
//		Integer minute = Integer.valueOf(s.substring(6, 8));
    	if(s.endsWith("PM")) {
    		if(hour == 12)
    			return s.substring(0, 8);
    		else
    			return (hour + 12) + s.substring(2, 8);
    	} else {
    		if(hour == 12)
    			return "00" + s.substring(2, 8);
    		else
    			return s.substring(0, 8);
    	}
    }
    
	public int[] init(int size) {
		int[] rootArr = new int[size];
		for(int i=0; i<size; i++) rootArr[i] = i;
		return rootArr;
	}
	
	private void union(int src, int dest, int[] rootArr) {
		int n1 = getRoot(src, rootArr);
		int n2 = getRoot(dest, rootArr);
		rootArr[n1] = n2;
	}
	
	private int getRoot(int src, int[]rootArr) {
		while(src != rootArr[src]) {
			src = rootArr[src];
		}
		return src;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(14);
		g.addEdge(0, 1, 1);
		g.addEdge(1, 2, 1);
		g.addEdge(1, 3, 3);
		g.addEdge(1, 4, 3);
		g.addEdge(2, 3, 1);
		g.addEdge(3, 4, 2);
		g.addEdge(4, 13, 2);
		g.addEdge(11, 13, 2);
		g.addEdge(11, 10, 2);
		g.addEdge(12, 10, 1);
		g.addEdge(9, 10, 2);
		g.addEdge(9, 7, 3);
		g.addEdge(8, 7, 1);
		g.addEdge(8, 5, 2);
		g.addEdge(6, 7, 4);
		g.addEdge(6, 5, 3);
		g.addEdge(3, 5, 2);
		
		TraverseAction action = new TraverseAction() {
			@Override
			public void doSth(Integer node) {
				System.out.print(node);
			}
		};
		
		g.bfs(action, 0);
		System.out.println();
		
		g.dfs(action, 0);
		System.out.println();
		
		
		Graph mst = g.getKruscalMST();
		int mstLength = 0;
		for(List<Edge>eList : mst.edges) {
			for(Edge e : eList) {
				mstLength = mstLength + e.weight;
			}
		}
		mstLength = mstLength / 2;
		System.out.println(mstLength);

		
		mst = g.getPrimMST();
		mstLength = 0;
		for(List<Edge>eList : mst.edges) {
			for(Edge e : eList) {
				mstLength = mstLength + e.weight;
			}
		}
		mstLength = mstLength / 2;
		System.out.println(mstLength);
		
		
		int[] dijkstraSP = g.getDijkstraSP(0);
		System.out.println(Arrays.toString(dijkstraSP));
	}

}
