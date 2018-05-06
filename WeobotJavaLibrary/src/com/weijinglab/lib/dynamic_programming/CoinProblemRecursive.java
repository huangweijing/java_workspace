package com.weijinglab.lib.dynamic_programming;

public class CoinProblemRecursive {

	public static void main(String[] args) {
		
		int max = 1000000;
		int result[] = new int[max + 1];
		result[0] = 0;
		result[1] = 1;
		result[2] = 2;
		result[3] = 3;
		result[4] = 1;
		result[5] = 1;
		
		for(int i=1; i<=max; i++) {
			
			if(i + 1 <= max &&
					(result[i+1] == 0 || result[i+1] > result[i] + 1)) {
				result[i+1] = result[i] + 1;
			}
			if(i + 4 <= max &&
					(result[i+4] == 0 || result[i+4] > result[i] + 1)) {
				result[i+4] = result[i] + 1;
			}
			if(i + 5 <= max &&
					(result[i+5] == 0 || result[i+5] > result[i] + 1)) {
				result[i+5] = result[i] + 1;
			}
		}
		
		
		for(int i=0; i<=20; i++) {
			System.out.println("i=" + i + ": " + result[i]);	
		}

	}

}
