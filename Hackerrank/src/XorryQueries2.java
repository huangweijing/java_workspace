import java.util.Arrays;
import java.util.Scanner;

public class XorryQueries2 {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int p = scanner.nextInt();
		int[] a = new int[n+1];
		int[] orgArr = new int[n+1];
		for(int i=1; i<=n; i++) {
			a[i] = scanner.nextInt();
		}
		for(int i=0; i<n; i++) {
			orgArr[i] = P(i+1, p, a);
//			System.out.println(i);
		}
		SegmentTree tree = new SegmentTree(orgArr);
//		System.out.println("p3=" + P(3, p, a));
		while(m-- > 0) {
			
			int type = scanner.nextInt();
			
			if(type == 1) {
				int i = scanner.nextInt();
				int x = scanner.nextInt();
				a[i] = a[i] ^ x;
				tree.update(0, i-1, P(i, p, a));
			} else {
				int l = scanner.nextInt();
				int r = scanner.nextInt();
				System.out.println(tree.getSum(l-1, r-1));
			}
			System.out.println(Arrays.toString(a));
		}
	}
	
	public static int P(int i, int p, int[] a) {
		if(i+p>a.length)
			return 0;
		
		int returnVal = a[i];
		for(int idx=i + 1; idx<=i+p-1; idx++) {
			returnVal = returnVal ^ a[idx];
		}
		return returnVal;
	}
	
	
	public static class SegmentTree {

		public class Node {
			public int from;
			public int to;
			public int sum;
			public Node(int from, int to) {
				this.from = from;
				this.to = to;
			}
			public String toString() {
				return String.format("[from=%s, to=%s]=%s", from, to, sum);
			}
		}
		
		private Node[] segmentNodeArr;
		private int[] orgArr;
		
		public SegmentTree(int[] arr) {
			orgArr = Arrays.copyOf(arr, arr.length);
			segmentNodeArr = new Node[arr.length * 2 - 1];
			build(0, 0, orgArr.length);
//			System.out.println(Arrays.toString(segmentNodeArr));
		}
		
		public Node build(int v, int start, int size) {
			Node node = new Node(start, start + size - 1);
			segmentNodeArr[v] = node;
			if(size == 1) {
				node.sum = orgArr[start];
			} else {
				int subSize = (size + 1)/ 2;
				node.sum = build(v * 2 + 1, start, subSize).sum + 
						build(v * 2 + 2, start + subSize, size - subSize).sum;
			}
			return node;
		}
		
		public int getSum(int from, int to) {
			return getSum(0, from, to);
		}
		
		public int getSum(int v, int from, int to) {
			if(from <= segmentNodeArr[v].from && segmentNodeArr[v].to <= to ) {
				return segmentNodeArr[v].sum; 
			} else if(segmentNodeArr[v].to < from || to < segmentNodeArr[v].from) {
				return 0;
			} else {
				return getSum(v * 2 + 1, from, to) + getSum(v * 2 + 2, from, to);
			}
		}
		
		public void update(int v, int index, int value) {
			if(segmentNodeArr[v].from <= index && index <= segmentNodeArr[v].to) {
				segmentNodeArr[v].sum += value - orgArr[index];
				if(segmentNodeArr[v].from  == segmentNodeArr[v].to) {
					orgArr[index] = value;
				} else {
					update(v * 2 + 1, index, value);
					update(v * 2 + 2, index, value);
				}
			} else if(segmentNodeArr[v].to < index || index < segmentNodeArr[v].from) {
				return;
			} else {
				update(v * 2 + 1, index, value);
				update(v * 2 + 2, index, value);
			}
			
		}

	}

}