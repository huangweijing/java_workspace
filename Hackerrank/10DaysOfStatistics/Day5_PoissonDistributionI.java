import java.math.BigDecimal;


public class Day5_PoissonDistributionI {

	public static void main(String[] args) {
		double result = getPossionDistribution(2.5, 5);
		BigDecimal bd = BigDecimal.valueOf(result);
		System.out.println(bd.setScale(3, BigDecimal.ROUND_HALF_UP));
	}

	public static double getPossionDistribution(double avg, int k) {
		return Math.pow(avg, k) * Math.pow(Math.E, -avg) / fact(k); 
	}
	
	public static long fact(long num) {
		if(num <= 1)
			return 1;
		return fact(num - 1) * num;
	}
}
