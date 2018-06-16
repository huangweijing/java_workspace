import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CompetitiveTeams2 {

	static int unionCount;
	
	public static int[] init(int size) {
		int[] rootArr = new int[size];
		for(int i=0; i<size; i++) rootArr[i] = i;
		return rootArr;
	}

	private static int union(int src, int dest, int[] rootArr, int[] sizeArr) {
		unionCount--;
		int n1 = getRoot(src, rootArr);
		int n2 = getRoot(dest, rootArr);
		if(sizeArr[n1] < sizeArr[n2]) {
			rootArr[n1] = n2;
			sizeArr[n2] = n1 + n2;
			return n2;
		}
		else {
			rootArr[n2] = n1;
			sizeArr[n1] = n1 + n2;
			return n1;
		}
	}
	
	private static int unionRoot(int n1, int n2, int[] rootArr, int[] sizeArr, Map<Integer, Integer> sizeMap) {
		unionCount--;
		int result = 0;
		if(sizeArr[n1] < sizeArr[n2]) {
			rootArr[n1] = n2;
			sizeArr[n2] = sizeArr[n1] + sizeArr[n2];
			sizeMap.remove(n1);
			sizeMap.put(n2, sizeArr[n2]);
			result = n2;
		}
		else {
			rootArr[n2] = n1;
			sizeArr[n1] = sizeArr[n1] + sizeArr[n2];
			sizeMap.remove(n2);
			sizeMap.put(n1, sizeArr[n1]);
			result = n1;
		}
//		System.out.println(Arrays.toString(rootArr));
//		System.out.println(Arrays.toString(sizeArr));
		return result;
	}
	
	private static int getRoot(int src, int[]rootArr) {
		while(src != rootArr[src]) {
			src = rootArr[src];
		}
		return src;
	}
	
	
	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int q = scanner.nextInt();
		
		int[] group = init(n); 
		Map<Integer, Integer> sizeMap = new HashMap<>();
		int[] sizeArr = new int[n+1];
		Arrays.fill(sizeArr, 1);
		unionCount = n;
		
		while(q-- > 0) {
			int query = scanner.nextInt();
			if(query == 1) {
				int x = scanner.nextInt() - 1;
				int y = scanner.nextInt() - 1;
				int rootX = getRoot(x, group);
				int rootY = getRoot(y, group);
				if(rootX != rootY) {
					unionRoot(rootX, rootY, group, sizeArr, sizeMap);
				}
//				System.out.println(Arrays.toString(sizeArr));
//				System.out.println(sizeMap);
			} else {
				long count = 0;
				int c = scanner.nextInt();
				
				Integer[] array = sizeMap.values().toArray(new Integer[0]);
				Arrays.sort(array);
//				System.out.print(Arrays.toString(array));

				int groupCountGT2 = 0;
				for(int i=0; i<array.length; i++) {
					for(int j=i+1; j<array.length; j++) {
						if(array[j] - array[i] >= c) {
							count = count + array.length - j;
							break;
						}
					}
					groupCountGT2 += array[i];
				}
				
				int oneManGroupCount = n - groupCountGT2;
				for(int i=0; i<array.length; i++) {
					if(array[i] - 1 >= c) {
						count += oneManGroupCount;
					}
				}
				
//				System.out.println("groupCountGT2=" + groupCountGT2);
//				System.out.println("oneManGroupCount=" + oneManGroupCount);
//				System.out.println("count=" + count);
				if(c==0)
					count += (long)oneManGroupCount * (oneManGroupCount - 1) / 2;
				
				System.out.println(count);
			}
			
		}
	}

}
