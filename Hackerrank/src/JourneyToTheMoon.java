import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class JourneyToTheMoon {
	public static class Edge implements Comparable<Edge>{
		
		public int from;
		public int to;
		public int weight;
		
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
		
		public int dfs(int from, boolean[] visited) {
			Stack<Integer> nodeStack = new Stack<Integer>();
			nodeStack.add(from);
			int count = 1;
			visited[from] = true;
			
			while(!nodeStack.isEmpty()) {
				Integer node = nodeStack.pop();
				List<Edge>adjacentEdgeList = this.edges.get(node);
				for(Edge adjacentEdge : adjacentEdgeList) {
					if(!visited[adjacentEdge.to]) {
						nodeStack.add(adjacentEdge.to);
						visited[adjacentEdge.to] = true;
						count++;
					}
				}
			}
			
			return count;
		}
		
		public List<Integer> countIsland() {
			List<Integer> countList = new ArrayList<>();
			boolean[] visited = new boolean[this.v];
			for(int i=0; i<visited.length; i++) {
				if(!visited[i]) {
					int nodeCount = dfs(i, visited);
					countList.add(nodeCount);
				}
			}
			return countList;
		}

	}
	
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int p = scanner.nextInt();
		
		Graph g = new Graph(n);
		
		for(int i=0; i<p; i++) {
			int id1 = scanner.nextInt();
			int id2 = scanner.nextInt();
			g.addEdge(id1, id2, 1);
		}
		
		long result = 0;
		List<Integer> countIsland = g.countIsland();
		for(Integer i : countIsland) {
			result += (long)i * (n - i);
		}
		System.out.println(result / 2);
	}

}
