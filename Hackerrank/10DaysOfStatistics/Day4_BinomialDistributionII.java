import java.math.BigDecimal;
import java.util.Scanner;


public class Day4_BinomialDistributionII {
	
	public static void main(String[] args) {
		double noMoreThan2 = getBinomialRate(10, 2, 0.12) + getBinomialRate(10, 1, 0.12) + getBinomialRate(10, 0, 0.12);
		BigDecimal result1 = BigDecimal.valueOf(noMoreThan2).setScale(3, BigDecimal.ROUND_HALF_UP);
		System.out.println(result1);
		
		double atLeast2 = 0;
		for(int i=2; i<=10; i++) {
			atLeast2 += getBinomialRate(10, i, 0.12);
		}
		BigDecimal result2 = BigDecimal.valueOf(atLeast2).setScale(3, BigDecimal.ROUND_HALF_UP);
		System.out.println(result2);
	}

	public static double getBinomialRate(int total, int success, double success_rate) {
		double result = c(total, success)
				* Math.pow(success_rate, success) 
				* Math.pow(1 - success_rate, total - success); 
		return result;
	}
	
	public static long c(long n1, long n2){
		long result = 1;
		for(long i = n2 + 1; i <= n1; i++) {
			result = result * i;
		}
		result = result / fact(n1 - n2);
		return result;
	}
	
	public static long fact(long num) {
		if(num <= 1)
			return 1;
		return fact(num - 1) * num;
	}
}
