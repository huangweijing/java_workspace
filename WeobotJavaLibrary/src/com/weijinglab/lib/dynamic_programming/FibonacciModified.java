package com.weijinglab.lib.dynamic_programming;
import java.math.BigDecimal;
import java.util.Scanner;


public class FibonacciModified {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		int t1 = scanner.nextInt();
		int t2 = scanner.nextInt();
		int n = scanner.nextInt();
		BigDecimal[] result = new BigDecimal[n + 1];
		result[1] = BigDecimal.valueOf(t1);
		result[2] = BigDecimal.valueOf(t2);
		
		for(int i=1; i<=n-2; i++) {
			result[i+2] = result[i].add(result[i+1].multiply(result[i+1])); 
		}
		
//		System.out.println(Arrays.toString(result));
		System.out.println(result[n]);
	}
}
