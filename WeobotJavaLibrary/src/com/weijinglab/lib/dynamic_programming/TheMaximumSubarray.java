package com.weijinglab.lib.dynamic_programming;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/maxsubarray/problem
 * @author HuangWeijing
 *
 */
public class TheMaximumSubarray {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int t = scanner.nextInt();
		while(t-- > 0) {
			int n = scanner.nextInt();
			int[]a = new int[n+1]; 
			for(int i=1; i<=n; i++) {
				a[i] = scanner.nextInt();
			}

			//Maximum SubArray
			//solutions[n]=maximum sum of SubArray
			int[] solutions = new int[n+1];
			//maxArra[n] = max{a[n], a[n]+a[n-1],...,a[n]+a[n-1]+...a[1]}
			int[] maxArray = new int[n+1];
			solutions[1] = a[1];
			maxArray[1] = a[1];
			for(int i=1; i<=n-1; i++) {
				if(maxArray[i] > 0) {
					maxArray[i+1] = maxArray[i] + a[i+1];
				} else {
					maxArray[i+1] = a[i+1];
				}
				//solutions[n] = Max{solutions[n-1], max{a[n], a[n]+a[n-1],...,a[n]+a[n-1]+...a[1]}}
				solutions[i+1] = Math.max(solutions[i], maxArray[i+1]); 
			}
			
			//Maximum SubSequence
			//If there is any positive integer, just sum them all.
			//Else the result is the biggest negative integer.
			int result = 0;
			boolean foundFlg = false;
			int max = Integer.MIN_VALUE;
			for(int i=1; i<=n; i++) {
				if(a[i] > 0) {
					result += a[i];
					foundFlg = true;
				}
				if(a[i] > max) {
					max = a[i];
				}
			}
			if(!foundFlg) {
				result = max;
			}
			
			System.out.println(solutions[n] + " " + result);
		}
		
	}
}
