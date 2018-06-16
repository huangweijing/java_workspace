import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class RoadsAndLibraries {
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
	
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int q = scanner.nextInt();
		while(q-- > 0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			int lc = scanner.nextInt();
			int rc = scanner.nextInt();
			
			Graph g = new Graph(n);
			int einput = m;
			while(einput-- > 0) {
				int from = scanner.nextInt();
				int to = scanner.nextInt();
				g.addEdge(from - 1, to - 1, 1);
			}

			if(lc <= rc) {
				System.out.println((long)n * lc);
				continue;
			}
			
			long islandCount = g.countIsland();
			System.out.println(rc * (n - 1 - (islandCount - 1)) + lc * islandCount);
			
		}
	}
}
