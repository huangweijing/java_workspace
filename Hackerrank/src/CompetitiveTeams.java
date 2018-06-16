import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class CompetitiveTeams {
	public static class Edge implements Comparable<Edge>{
		
		public int from;
		public int to;
		public int weight;
		public boolean disable;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
		public String toString() {
			return "[Edge][from=" + from + ", to=" + to + ", weight=" + weight + "]";  
		}
		
		@Override
		public boolean equals(Object obj) {
			Edge e = (Edge)obj;
			if(e.from == this.from
					&& e.to == this.to)
				return true;
			return false;
		}
	}	
	public static class Graph {
		
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
		
		public long getKruscalMST() {
			long mst = 0;
			
			List<Edge> list = new ArrayList<>();
			for(List<Edge> edgeList : edges) {
				list.addAll(edgeList);
			}
			Collections.sort(list);
			
			int[] rootArr = init(this.v);
			for(Edge edge : list) {
				if(getRoot(edge.from, rootArr) != getRoot(edge.to, rootArr)) {
					union(edge.from, edge.to, rootArr);
					mst = mst + edge.weight;
				}
			}
			return mst;
		}
		
		public void dfs(int from, boolean[] visited) {
			Stack<Integer> nodeStack = new Stack<Integer>();
			nodeStack.add(from);
			visited[from] = true;
			
			while(!nodeStack.isEmpty()) {
				Integer node = nodeStack.pop();
				List<Edge>adjacentEdgeList = this.edges.get(node);
				for(Edge adjacentEdge : adjacentEdgeList) {
					if(!visited[adjacentEdge.to]) {
						nodeStack.add(adjacentEdge.to);
						visited[adjacentEdge.to] = true;
					}
				}
			}
		}
		
		public int[] getTree() {
			int[] tree = this.init(this.v);
			for(List<Edge> edgeList : this.edges) {
				for(Edge e : edgeList) {
					if(tree[e.from] == e.to
							|| tree[e.to] == e.from)
						continue;
					if(tree[e.from] == e.from) {
						tree[e.from] = e.to;
					} else if(tree[e.to] == e.to) {
						tree[e.to] = e.from;
					} else {
						int current = tree[e.from];
						List<Integer> list = new ArrayList<Integer>();
						while(tree[current] != current) {
							current = tree[current];
							list.add(current);
						}
						for(int i=1; i<list.size(); i++) {
							tree[i] = list.get(i-1);
						}
					}
				}
			}
			return tree;
		}

		
		public int bfsDistance(int from, int to) {
			boolean[] visited = new boolean[this.v];
			int[] distance = new int[this.v];
			Queue<Integer> nodeQueue = new LinkedList<Integer>();
			nodeQueue.add(from);
			visited[from] = true;
			
			while(!nodeQueue.isEmpty()) {
				Integer node = nodeQueue.poll();
				List<Edge>adjacentEdgeList = this.edges.get(node);
				for(Edge adjacentEdge : adjacentEdgeList) {
					if(!adjacentEdge.disable && !visited[adjacentEdge.to]) {
						nodeQueue.add(adjacentEdge.to);
						visited[adjacentEdge.to] = true;
						distance[adjacentEdge.to] = distance[adjacentEdge.from] + 1;
						if(adjacentEdge.to == to) {
							return distance[to];
						}
					}
				}
			}
			return -1;
		}
		
		public int countIsland() {
			int count = 0;
			boolean[] visited = new boolean[this.v];
			for(int i=0; i<visited.length; i++) {
				if(!visited[i]) {
					this.dfs(i, visited);
					count++;
				}
			}
			return count;
		}

	}

	public static int distance(int[] tree, int n1, int n2) {
		Map<Integer, Integer> map1 = new HashMap<>();  
		Map<Integer, Integer> map2 = new HashMap<>();
		
		int distance1 = 0;
		int distance2 = 0;
		map1.put(n1, distance1);
		map2.put(n2, distance2);
		while(n1 != tree[n1] || n2 != tree[n2]) {
			if(n1 != tree[n1]) {
				n1 = tree[n1];
				distance1++;
				map1.put(n1, distance1);
				if(map2.containsKey(n1)) {
					distance2 = map2.get(n1);
					break;
				}
//				if(map1.containsKey(n2)) {
//					distance1 = map1.get(n2);
//					break;
//				}
			}
			
			if(n2 != tree[n2]) {
				n2 = tree[n2];
				distance2++;
				map2.put(n2, distance2);
				if(map1.containsKey(n2)) {
					distance1 = map1.get(n2);
					break;
				}
//				if(map2.containsKey(n1)) {
//					distance2 = map2.get(n1);
//					break;
//				}
			}
		}
		
		if(n1 != n2 && (n1 == tree[n1]) && (n2 == tree[n2]))
			return -1;
		
		return distance1 + distance2;
	}

	
	
	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int q = scanner.nextInt();
    	Map<Integer, Set<Integer>> groupInfo = new HashMap<>();
    	
		
		while(q-- > 0) {
			int query = scanner.nextInt();
			if(query == 1) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				Set<Integer> setX = groupInfo.get(x);
				if(setX == null) {
					setX = new HashSet<>();
					setX.add(x);
				}
				Set<Integer> setY = groupInfo.get(y);
				if(setY == null) {
					setY = new HashSet<>();
					setX.add(y);
				}
				setX.addAll(setY);
				for(Integer p : setX) {
					groupInfo.put(p, setX);
				}
				
			} else {
				int count = 0;
				int c = scanner.nextInt();
				Set<Set<Integer>>set = new HashSet<>(groupInfo.values());
				List<Set<Integer>> list = new ArrayList<>(set);
				for(int i=0; i<list.size(); i++)
					for(int j=i+1; j<list.size(); j++) {
						if(Math.abs(list.get(i).size() - list.get(j).size()) >= c) {
							count++;
						}
					}
				
				System.out.println(set);
				System.out.println(count);
			}
			
		}
	}

}
