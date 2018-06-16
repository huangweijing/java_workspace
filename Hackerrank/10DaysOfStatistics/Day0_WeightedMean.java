import java.math.BigDecimal;
import java.util.Scanner;


public class Day0_WeightedMean {

    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int weightSum = 0;
		int sum = 0;
		int[] weight = new int[n];
		int[] x = new int[n];
		for(int i=0; i<n; i++) {
			x[i] = scanner.nextInt();
		}
		for(int i=0; i<n; i++) {
			weight[i] = scanner.nextInt();
			sum += weight[i] * x[i];
			weightSum += weight[i];
		}
		System.out.println(BigDecimal.valueOf((double)sum / weightSum)
			.setScale(1, BigDecimal.ROUND_HALF_UP));
	}

}
