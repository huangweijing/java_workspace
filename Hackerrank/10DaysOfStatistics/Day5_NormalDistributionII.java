import java.math.BigDecimal;


public class Day5_NormalDistributionII {

	public static void main(String[] args) {
		
		double result = 1 - 0.8413;
		BigDecimal bd = BigDecimal.valueOf(result * 100);
		System.out.println(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
		
		result = 0.8413;
		bd = BigDecimal.valueOf(result * 100);
		System.out.println(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
		
		result = 1 - 0.8413;
		bd = BigDecimal.valueOf(result * 100);
		System.out.println(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	public static double getNormalDistributionCDP(double mean, double standardDeviation, double x) {
		double result = 0.5 * ( 1 + erf( (x - mean) / (standardDeviation * Math.sqrt(2))));
		return result;
	}
	
	public static double erf(double z) {
		double sum = 0;
		for(double x = 0; x <= z; x+= 0.001) {
			double add = Math.pow(Math.E, -x * x);
			sum += add;
		}
		return (2 / Math.sqrt(Math.PI)) *sum;
	}

}
