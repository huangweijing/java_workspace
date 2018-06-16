import java.math.BigDecimal;


public class Day5_PoissonDistributionII {

	public static void main(String[] args) {
		double result = expectationOfRepairs(0.88) * 40 + 160;
		BigDecimal bd = BigDecimal.valueOf(result);
		System.out.println(bd.setScale(3, BigDecimal.ROUND_HALF_UP));
		
		result = expectationOfRepairs(1.55) * 40 + 128;
		bd = BigDecimal.valueOf(result);
		System.out.println(bd.setScale(3, BigDecimal.ROUND_HALF_UP));
	}
	
	public static double expectationOfRepairs(double avg) {
		return avg * avg + avg;
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
