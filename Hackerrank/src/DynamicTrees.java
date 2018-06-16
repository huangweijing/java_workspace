import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class DynamicTrees {
	
	public static int getLCA(int[] tree, int n1, int n2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		if(n1 == n2)
			return n1;
		int current1 = n1;
		int current2 = n2;
		set1.add(n1);
		set2.add(n2);

		while(current1 != tree[current1] || current2 != tree[current2]) {
			current1 = tree[current1];
			set1.add(current1);
			if(set2.contains(current1)) {
				return current1;
			}
			
			current2 = tree[current2];
			set2.add(current2);
			if(set1.contains(current2)) {
				return current2;
			}
		}
		
		return -1;
	}
	
	public static int getkthBlack(int[] tree, int[] color, int n1, int n2, int k) {
		int lca = getLCA(tree, n1, n2);
		while(n1 != lca) {
			if(color[n1]==1)
				k--;
			if(k==0)
				return n1;
			n1 = tree[n1];
		}
		List<Integer> n2List = new ArrayList<>();
		while(n2 != lca) {
			if(color[n2] == 1)
				n2List.add(n2);
			n2 = tree[n2];
		}
		if(k > n2List.size())
			return -1;
		else
			return n2List.get(n2List.size() - k);
	}
	
	public static int getkth(int[] tree, int[] color, int n1, int n2, int k) {
		Map<Integer, Integer> map1 = new HashMap<>();  
		Map<Integer, Integer> map2 = new HashMap<>();

		//save n2 from n2 to lca
		List<Integer> n2List = new ArrayList<Integer>();
		
		int distance1 = 0;
		int distance2 = 0;
		//if the first ball is black
		if(color[n1] == 1)
			distance1 = 1;
		if(color[n2] == 1) {
			distance2 = 1;
			n2List.add(n2);
		}
		//if k == 1, then return n1 
		if(distance1 == k)
			return n1;
		//if n1 and n2 are the same, return -1
		if(n1==n2) {
			return -1;
		}
		map1.put(n1, distance1);
		map2.put(n2, distance2);
		
		
		while(n1 != tree[n1] || n2 != tree[n2]) {
			if(n1 != tree[n1]) {
				n1 = tree[n1];
				if(color[n1] == 1)
					distance1++;
				//if reached kth, reutrn n1
				if(distance1 == k)
					return n1;
				map1.put(n1, distance1);
				
				//if n1 has reached the lowest common ancestor
				//lca detected due to the change of n1  
				if(map2.containsKey(n1)) {
					distance2 = map2.get(n1);
					break;
				}
				//if n2 has reached the lowest common ancestor
				//lca detected due to the change of map1
				if(map1.containsKey(n2)) {
					distance1 = map1.get(n2);
					break;
				}
			}
			
			if(n2 != tree[n2]) {
				n2 = tree[n2];
				
				if(color[n2] == 1) {
					distance2++;
					n2List.add(n2);
				}
				map2.put(n2, distance2);
				//if n2 has reached the lowest common ancestor
				if(map1.containsKey(n2)) {
					distance1 = map1.get(n2);
					break;
				}
				//if n1 has reached the lowest common ancestor
				if(map2.containsKey(n1)) {
					distance2 = map2.get(n1);
					break;
				}
			}
		}
		
		
		
//		if(n1 != n2 && (n1 == tree[n1]) && (n2 == tree[n2]))
//			return -1;
		
		return distance1 + distance2;
	}
	
	
    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
//		int[] tree = {0, 3, 0, 0, 2, 2, 5, 4, 5};
//		System.out.println(getLCA(tree, 5, 8));
//		
//		System.exit(0);
//		
		int n = scanner.nextInt();
		int q = scanner.nextInt();
		int[] color = new int[n+1];
		int[] tree = new int[n+1];
		for(int i=1; i<=n; i++) {
			color[i] = scanner.nextInt();
		}
		tree[1] = 1;
		for(int i=2; i<=n; i++) {
			tree[i] = scanner.nextInt();
		}
//		System.out.println(Arrays.toString(color));
//		System.out.println(Arrays.toString(root));
		while(q-- > 0) {
//			System.out.println(q);
			String queryType = scanner.next();
			if(queryType.equals("K")) {
				int u = scanner.nextInt();
				int v = scanner.nextInt();
				int k = scanner.nextInt();
				
//				int result = getLCA(tree, u, v);
//				int result = getkthBlack(tree, color, u, v, k);
//				System.out.println(result);
				
			} else if(queryType.equals("T")) {
				int x = scanner.nextInt();
				color[x] = color[x] == 0 ? 1 : 0;
				
			} else if(queryType.equals("C")) {
				int u = scanner.nextInt();
				int v = scanner.nextInt();
				tree[u] = v;
			} 
		}
	}

}
