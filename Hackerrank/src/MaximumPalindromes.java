import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaximumPalindromes {

	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String s = scanner.next();
		SegmentTree tree = new SegmentTree(s);
		
		BigInteger[] f = new BigInteger[10000];
//		BigInteger[] f = new BigInteger[s.length() / 2 + 1];
		f[0] = BigInteger.ONE;
		f[1] = BigInteger.ONE;
		for(int i=2; i<f.length; i++) {
//			System.out.println(i);
			f[i] = f[i-1].multiply(BigInteger.valueOf(i));
		}
		System.out.println(Arrays.toString(tree.getAlp(0, s.length())));
		
		Integer q = scanner.nextInt();
		while(q-- > 0) {
			Integer l = scanner.nextInt();
			Integer r = scanner.nextInt();
//			String subs = s.substring(l-1, r);
			int[] alp = tree.getAlp(l-1, r-1);

//			System.out.println(Arrays.toString(alp));
//			System.out.println(Arrays.toString(alp2));
			
			int even = 0;
			int odd = 0;
			BigInteger divider = BigInteger.ONE;
			for(int i=0; i<alp.length; i++) {
				if(alp[i] == 0)
					continue;
				if((alp[i]&1 )== 0) {
					even += alp[i];
					divider = divider.multiply(fact(f, alp[i] / 2));
				}
				else {
					odd++;
					even += (alp[i] - 1);
					if(alp[i] - 1 != 0)
						divider = divider.multiply(fact(f, (alp[i] - 1) / 2));
				}
			}
//			System.out.println(subs);
//			System.out.println(Arrays.toString(alp));
//			System.out.println(even);
//			System.out.println(divider);
//			System.out.println(odd);
			
			
			BigInteger cnt = fact(f, even / 2).divide(divider).multiply(BigInteger.valueOf(odd > 1 ? odd : 1));
			System.out.println(cnt.mod(BigInteger.valueOf(1000000007)));
		}
	}
	
//	public static Map<Integer, BigInteger> BigIntegerResult = new HashMap<>(); 
//	public static BigInteger fact(int n) {
//		if(!BigIntegerResult.containsKey(n)) {
//			BigIntegerResult.put(n, n > 1 ? fact(n-1).multiply(BigInteger.valueOf(n)) : BigInteger.ONE);
//		}
//		return BigIntegerResult.get(n);
//	}
	
	public static BigInteger fact(BigInteger[]f, int n) {
		if(n >= f.length)
			return fact(f, n - 1).multiply(BigInteger.valueOf(n));  
		return f[n];
	}
	
	
	public static class SegmentTree {

		public class Node {
			public int from;
			public int to;
			public int sum;
			public int[] alp;
			public Node(int from, int to) {
				this.from = from;
				this.to = to;
				this.alp = new int[26];
			}
			public String toString() {
				return String.format("[from=%s, to=%s]=%s", from, to, sum);
			}
		}
		
		private Node[] segmentNodeArr;
		private String str;
		
		public SegmentTree(String str) {
			this.str = str;
			
			int MaxleftCnt = Integer.highestOneBit(str.length() - 1) << 1;
			segmentNodeArr = new Node[(MaxleftCnt << 1) - 1];
			build(0, 0, this.str.length());
		}
		
		public Node build(int v, int start, int size) {
//			System.out.println("start=" + start + ",size=" + size);
			Node node = new Node(start, start + size - 1);
			segmentNodeArr[v] = node;
			if(size == 1) {
				node.alp[this.str.charAt(start) - 'a'] = 1;
			} else {
				int subSize = (size + 1)/ 2;
				Node node1 = build(v * 2 + 1, start, subSize);
				Node node2 = build(v * 2 + 2, start + subSize, size - subSize);
				for(int i=0; i<node.alp.length; i++) {
					node.alp[i] = node1.alp[i] + node2.alp[i]; 
				}
			}
			return node;
		}
		
		public int[] getAlp(int v, int from, int to) {
			if(from <= segmentNodeArr[v].from && segmentNodeArr[v].to <= to ) {
				return segmentNodeArr[v].alp; 
			} else if(segmentNodeArr[v].to < from || to < segmentNodeArr[v].from) {
				return new int[26];
			} else {
				int[] alp1 = getAlp(v * 2 + 1, from, to);
				int[] alp2 = getAlp(v * 2 + 2, from, to);
				int[] newAlp = new int[26];
				for(int i=0; i<alp1.length; i++) {
					newAlp[i] = alp1[i] + alp2[i]; 
				}
				return newAlp;
			}
		}
		
		public int[] getAlp(int from, int to) {
			return getAlp(0, from, to);
		}
		
	}

}
