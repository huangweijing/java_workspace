package com.weijinglab.lib.unionfind;

import java.util.Arrays;
import java.util.Scanner;

public class UnionFind {
	public static int union(int[] arr, int v1, int v2, int[] size) {
		int root1 = find(arr, v1);
		int root2 = find(arr, v2);
		if(root1 == root2)
			return root1;
		if(size[root1] <= size[root2]) {
			arr[root1] = root2;
			size[root2] = size[root1] + size[root2];
		} 
		else if(size[root1] > size[root2]) {
			arr[root2] = root1;
			size[root1] = size[root1] + size[root2];
		}
		return root2;
	}
	
	public static int find(int[] arr, int v) {
		while(arr[v] != v) {
			v = arr[v];
		}
		return v;
	}

	public static int[] init(int n) {
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = i;
		}
		return arr;
	}
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int[] size = new int[n * 2];
		Arrays.fill(size, 1);
		int[] arr = init(n * 2);
		while(n-- > 0) {
			int g = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			union(arr, g, b, size);
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i=0; i<size.length; i++) {
			if(size[i] == 1)
				continue;
			int root = find(arr, i);
			if(min > size[root])
				min = size[root];
			if(max < size[root])
				max = size[root];
		}
		System.out.println(min + " " + max);
	}

}
