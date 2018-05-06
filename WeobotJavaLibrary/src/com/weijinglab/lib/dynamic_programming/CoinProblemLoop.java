package com.weijinglab.lib.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

public class CoinProblemLoop {
	
	static Map<Integer, Long> resultMap = new HashMap<>();
	
	static long minCoin(int n) {
		if(resultMap.containsKey(n)) {
			return resultMap.get(n);
		}
		long num5 = minCoin(n - 5) + 1;
		long num4 = minCoin(n - 4) + 1;
		long num1 = minCoin(n - 1) + 1;
		long result = Math.min(Math.min(num5, num4), num1);
		resultMap.put(n, result);
		
		return result;
	}

	public static void main(String[] args) {
		
		int max = 1000000;
		resultMap.put(0, 0l);
		resultMap.put(1, 1l);
		resultMap.put(2, 2l);
		resultMap.put(3, 3l);
		resultMap.put(4, 1l);
		resultMap.put(5, 1l);
		
		for(int i=1; i<=max; i++) {
			minCoin(i);
		}
		
		for(int i=0; i<=20; i++) {
			System.out.println("i=" + i + ": " + resultMap.get(i));	
		}

	}

}
