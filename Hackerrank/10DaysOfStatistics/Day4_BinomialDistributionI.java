import java.math.BigDecimal;
import java.util.Scanner;


public class Day4_BinomialDistributionI {

	public static Scanner scanner = new Scanner(System.in);
	private static double boyRate;
	private static double girlRate;
	private static double totalRate;
	public static void main(String[] args) {
		boyRate = scanner.nextDouble();
		girlRate = scanner.nextDouble();
		totalRate = boyRate + girlRate;
		BigDecimal result = BigDecimal.valueOf(getBoyRate(6, 3) + 
				getBoyRate(6, 4) + getBoyRate(6, 5) + getBoyRate(6, 6));
		result = result.setScale(3, BigDecimal.ROUND_HALF_UP);
		System.out.println(result);
	}
	
	public static double getBoyRate(int children, int boy) {
		double result = c(children, boy) * Math.pow(boyRate / totalRate, boy) * Math.pow(girlRate / totalRate, children - boy); 
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
