package com.weijinglab.lib.math;

import java.math.BigInteger;

public class Factor {
	
	public static BigInteger F1(BigInteger n) {
		if(n.and(BigInteger.ONE) == BigInteger.ZERO) {
			
		}
		return n;
	}
	
	static long[] smallFact = {
		1L
		, 1L
		, 2L
		, 6L
		, 24L
		, 120L
		, 720L
		, 5040L
		, 40320L
		, 362880L
		, 3628800L
		, 39916800L
		, 479001600L
		, 6227020800L
		, 87178291200L
		, 1307674368000L
		, 20922789888000L
		, 355687428096000L
		, 6402373705728000L
		, 121645100408832000L
		, 2432902008176640000L
	}; 
	
//	public static 
	public static BigInteger f(BigInteger n) {
		if(n.compareTo(BigInteger.valueOf(20)) < 0)
			return BigInteger.valueOf(smallFact[n.intValue()]);
		if(n.and(BigInteger.ONE).intValue() == 0) {
			System.out.println("f(" + n + ")=f(" + n.divide(BigInteger.valueOf(2)) + ")^2 * 2^"+ n.divide(BigInteger.valueOf(2)));
			BigInteger x = n.shiftRight(1);
			BigInteger fx = f(x);
			return fx.multiply(fx).multiply(BigInteger.ONE.shiftLeft(x.intValue()));
		} else {
			System.out.println("f(" + n + ")=f(" + n.subtract(BigInteger.ONE) + ") * " + n);
			return f(n.subtract(BigInteger.ONE)).multiply(n);
		}
	}
	
	public static BigInteger f2(BigInteger n) {
		if(n.compareTo(BigInteger.valueOf(20)) < 0)
			return BigInteger.valueOf(smallFact[n.intValue()]);
		return f2(n.subtract(BigInteger.ONE)).multiply(n);
		
	}

	public static void main(String[] args) {
//		BigInteger num = BigInteger.TEN.multiply(BigInteger.TEN);

		BigInteger num = BigInteger.valueOf(22);
		System.out.println(f(num));
		System.out.println(f2(num));
	}

}
