package com.weijinglab.mathproblem;

import java.util.ArrayList;
import java.util.List;

public class MathLab {

	public static void main(String[] args) {
		
		System.out.println(Math.pow(3, 2));
		
		Integer num1 = 102420, num2 = 2122155;
		System.out.println(String.format("The greatest common divisor between %1$s and %2$s is %3$s.", 
				num1, num2, getGreatestCommonDivisor(num1, num2)));
		
		num1 = 420;
		num2 = 2125;
		System.out.println(String.format("The lowest common multiple between %1$s and %2$s is %3$s.", 
				num1, num2, getLowestCommonMultiple(num1, num2)));
		
		num1 = 1249922172;
		Integer[] primeFactIntegers = getPrimeFactorization(num1);
		System.out.print("The prime factoization of " + num1 + " is ");
		boolean first = true;
		for(Integer i : primeFactIntegers) {
			if(first) {
				first = false;
			} else {
				System.out.print(" x ");
			}
			System.out.print(i);
		}

		System.out.println();
//		List<Integer> primeList = getAllPrimeNumbers(1000000);
//		System.out.println("There are " + primeList.size() + " prime numbers from 1 to 1000000");
//		Integer count = 1;
//		for(Integer i : primeList) {
//			System.out.print(i + " ");
//			if(count % 30 == 0) {
//				System.out.println();
//			}
//			count++;
//		}
		
		List<Integer> narcissisticNumbersList = getAllNarcissisticNumbers(100000000);
		System.out.println("There are " + narcissisticNumbersList.size() + " narcissistic numbers from 100 to 100000000");
		Integer count = 1;
		for(Integer i : narcissisticNumbersList) {
			System.out.print(i + " ");
			if(count % 30 == 0) {
				System.out.println();
			}
			count++;
		}
		
	}
	
	/**
	 * 求最小公倍数
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static Integer getLowestCommonMultiple(Integer num1, Integer num2) {
		Integer biggerOne = num1 < num2? num2 : num1;
		for(int i=biggerOne + 1; i < num1 * num2; i++) {
			if(i % num1 == 0 && i % num2 == 0) {
				return i;
			}
		}
		return num1 * num2;
	}
	
	/**
	 * 求最大公约数
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static Integer getGreatestCommonDivisor(Integer num1, Integer num2) {
		Integer minorOne = num1 < num2? num1 : num2;
		for(int i=minorOne - 1; i > 1; i--) {
			if(num1 % i == 0 && num2 % i == 0) {
				return i;
			}
		}
		return 1;
	}
	
	/**
	 * 求因式分解
	 * @param num
	 * @return
	 */
	public static Integer[] getPrimeFactorization(Integer num) {
		List<Integer> result = new ArrayList<Integer>();
		
		for(int i=2; i<=num; i++) {
			while(num % i == 0) {
				result.add(i);
				num = num / i;
			}
		}
		
		return result.toArray(new Integer[]{});
	}
	
	/**
	 * 求质数
	 * @param num
	 */
	public static List<Integer> getAllPrimeNumbers(Integer num) {
		List<Integer> primeList = new ArrayList<Integer>();
		for(int i=2; i<num; i++) {
			boolean isPrime = true;
			for(Integer prime : primeList) {
				if(i % prime == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				primeList.add(i);
			}
		}
		return primeList;
	}

	/**
	 * 求水仙花数
	 * @param num
	 */
	public static List<Integer> getAllNarcissisticNumbers(Integer num) {
		List<Integer> narcissisticNumberList = new ArrayList<Integer>();
		
		for(int i=1; i<=num ;i++) {
			
			if(i < 100) {
				continue;
			}
			
			Integer forDivide = i;
			List<Integer> partsOfI = new ArrayList<Integer>();
			Integer logI = 0;
			
			while(forDivide != 0) {
				partsOfI.add(forDivide % 10);
				forDivide /= 10;
				logI++;
			}
			
			Integer narcissisticSum = 0;
			for(Integer part : partsOfI) {
				narcissisticSum += (int)Math.pow(part, logI); 
			}
			
			if(narcissisticSum == i) {
				narcissisticNumberList.add(i);
			}
		}
		
		return narcissisticNumberList;
	}
	
}
