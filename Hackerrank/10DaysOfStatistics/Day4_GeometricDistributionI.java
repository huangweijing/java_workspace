import java.math.BigDecimal;


public class Day4_GeometricDistributionI {

	public static void main(String[] args) {
		BigDecimal bd = BigDecimal.valueOf(getGeometricDistribution(5, 1.0/3.0));
		System.out.println(bd.setScale(3, BigDecimal.ROUND_HALF_UP));
	}
	
	public static double getGeometricDistribution(int x, double successRate) {
		return Math.pow(1 - successRate, x - 1) * successRate; 
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
