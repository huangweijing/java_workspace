import java.util.Arrays;
import java.util.Scanner;

public class ATimeSavingAffair {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int m = scanner.nextInt();
		
//		int[][] g2 = new int[n][n];
		
		Graph g = new Graph(n);
		g.waitTime = k;
		
		for(int i=0; i<m; i++) {
			int from = scanner.nextInt();
			int to = scanner.nextInt();
			int cost = scanner.nextInt();
			g.addEdge(from - 1, to-1, cost);
		}
		long[] dijkstra = g.dijkstra(0);
		System.out.println(dijkstra[n-1]);
	}

	public static long getWaitTime(long current, int k) {
		if((current / k & 1) == 0) {
			return 0;
		} else {
			long mod = current % k;
			return k - mod;
		}
	}

	public static interface TraverseAction {
		public void doSth(Integer node);
	}
	
	public static class Graph {
		
		Integer v;
		Integer e;
		public int waitTime = 0;
		
		public int[][] matrix;
		
		public Graph(Integer v) {
			this.e = 0;
			this.v = v;
			this.matrix = new int[v][v];
		}
		
		public void addEdge(Integer src, Integer dest, Integer weight) {
			matrix[src][dest] = weight;
			matrix[dest][src] = weight;
			this.e++;
		}

	    int minDistance(long dist[], Boolean sptSet[])
	    {
	        // Initialize min value
	        long min = Integer.MAX_VALUE;
	        int min_index=-1;
	 
	        for (int v = 0; v < this.v; v++)
	            if (sptSet[v] == false && dist[v] <= min)
	            {
	                min = dist[v];
	                min_index = v;
	            }
	 
	        return min_index;
	    }

	    public long[] dijkstra(int src)
	    {
	    	int[][] graph = matrix;
	        long dist[] = new long[this.v]; 
	        
	        Boolean sptSet[] = new Boolean[this.v];
	        
	        Arrays.fill(dist, Integer.MAX_VALUE);
	        Arrays.fill(sptSet, false);

	        dist[src] = 0;
	 
	        for (int count = 0; count < this.v-1; count++)
	        {
	            int u = minDistance(dist, sptSet);
	 
	            sptSet[u] = true;
	 
	            for (int i = 0; i < this.v; i++) {
	            	
	            	if(sptSet[i] || graph[u][i]==0 || dist[u] == Integer.MAX_VALUE)
	            		continue;
	 
	            	long time = dist[u] + graph[u][i] + getWaitTime(dist[u], waitTime);

//	            	if (!sptSet[i] && graph[u][i]!=0 &&
//	                        dist[u] != Integer.MAX_VALUE &&
//	                        dist[u]+graph[u][i] < dist[i])
//	                    dist[i] = dist[u] + graph[u][i];
	            	
	                if (time < dist[i])
	                    dist[i] = time;
	            }
	        }
	 
	        return dist;
	    }
	}
}
