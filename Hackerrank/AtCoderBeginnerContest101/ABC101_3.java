import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class ABC101_3 {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		
		Graph g = new Graph(n);
		
		for(int i=0; i<m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			g.addEdge(a, b);
		}
		int island = g.countIsland();
		if(island == 1) {
			System.out.println(m - 1);
		} else if(island == 2) {
			System.out.println(m);
		} else if(island > 2) {
			System.out.println(-1);
		}
//		g.bridge();
	}
	// This class represents a undirected graph using adjacency list
	// representation
	static class Graph
	{
	    private int V;   // No. of vertices
	 
	    // Array  of lists for Adjacency List Representation
	    private LinkedList<Integer> adj[];
	    int time = 0;
	    static final int NIL = -1;
	 
	    // Constructor
	    Graph(int v)
	    {
	        V = v;
	        adj = new LinkedList[v];
	        for (int i=0; i<v; ++i)
	            adj[i] = new LinkedList();
	    }
	    
	    
		public void dfs(int from, boolean[] visited) {
			Stack<Integer> nodeStack = new Stack<Integer>();
			nodeStack.add(from);
			visited[from] = true;
			
			while(!nodeStack.isEmpty()) {
				Integer node = nodeStack.pop();
				List<Integer>adjacentEdgeList = adj[node];
				for(Integer adjacentEdge : adjacentEdgeList) {
					if(!visited[adjacentEdge]) {
						nodeStack.add(adjacentEdge);
						visited[adjacentEdge] = true;
					}
				}
			}
		}
		
		
		public int countIsland() {
			int count = 0;
			boolean[] visited = new boolean[V];
			for(int i=0; i<visited.length; i++) {
				if(!visited[i]) {
					this.dfs(i, visited);
					count++;
				}
			}
			return count;
		}
	    
	    
	 
	    // Function to add an edge into the graph
	    void addEdge(int v, int w)
	    {
	        adj[v].add(w);  // Add w to v's list.
	        adj[w].add(v);    //Add v to w's list
	    }
	 
	    // A recursive function that finds and prints bridges
	    // using DFS traversal
	    // u --> The vertex to be visited next
	    // visited[] --> keeps tract of visited vertices
	    // disc[] --> Stores discovery times of visited vertices
	    // parent[] --> Stores parent vertices in DFS tree
	    void bridgeUtil(int u, boolean visited[], int disc[],
	                    int low[], int parent[])
	    {
	 
	        // Count of children in DFS Tree
	        int children = 0;
	 
	        // Mark the current node as visited
	        visited[u] = true;
	 
	        // Initialize discovery time and low value
	        disc[u] = low[u] = ++time;
	 
	        // Go through all vertices aadjacent to this
	        Iterator<Integer> i = adj[u].iterator();
	        while (i.hasNext())
	        {
	            int v = i.next();  // v is current adjacent of u
	 
	            // If v is not visited yet, then make it a child
	            // of u in DFS tree and recur for it.
	            // If v is not visited yet, then recur for it
	            if (!visited[v])
	            {
	                parent[v] = u;
	                bridgeUtil(v, visited, disc, low, parent);
	 
	                // Check if the subtree rooted with v has a
	                // connection to one of the ancestors of u
	                low[u]  = Math.min(low[u], low[v]);
	 
	                // If the lowest vertex reachable from subtree
	                // under v is below u in DFS tree, then u-v is
	                // a bridge
	                if (low[v] > disc[u])
	                    System.out.println(u+" "+v);
	            }
	 
	            // Update low value of u for parent function calls.
	            else if (v != parent[u])
	                low[u]  = Math.min(low[u], disc[v]);
	        }
	    }
	 
	 
	    // DFS based function to find all bridges. It uses recursive
	    // function bridgeUtil()
	    void bridge()
	    {
	        // Mark all the vertices as not visited
	        boolean visited[] = new boolean[V];
	        int disc[] = new int[V];
	        int low[] = new int[V];
	        int parent[] = new int[V];
	 
	 
	        // Initialize parent and visited, and ap(articulation point)
	        // arrays
	        for (int i = 0; i < V; i++)
	        {
	            parent[i] = NIL;
	            visited[i] = false;
	        }
	 
	        // Call the recursive helper function to find Bridges
	        // in DFS tree rooted with vertex 'i'
	        for (int i = 0; i < V; i++)
	            if (visited[i] == false)
	                bridgeUtil(i, visited, disc, low, parent);
	    }
	 
	}
}
