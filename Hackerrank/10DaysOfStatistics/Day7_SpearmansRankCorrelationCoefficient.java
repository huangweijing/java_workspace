import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;


public class Day7_SpearmansRankCorrelationCoefficient {
    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		Double[] x = new Double[n];
		Double[] y = new Double[n];
		for(int i=0; i<n; i++) {
			x[i] = scanner.nextDouble();
		}
		for(int i=0; i<n; i++) {
			y[i] = scanner.nextDouble();
		}
		int[] rX = rank(x);
		int[] rY = rank(y);
		
//		System.out.println(Arrays.toString(rX));
//		System.out.println(Arrays.toString(rY));
		
		Double result = 0d;
		for(int i=0; i<n; i++) {
			result += Math.pow(rX[i] - rY[i], 2);
		}
		result = 1 - result * 6 / ( n * (n * n - 1));
		BigDecimal bd = BigDecimal.valueOf(result).setScale(3, BigDecimal.ROUND_HALF_UP);
		System.out.println(bd);
	}
	
	public static int[] rank(Double[] x) {
		int[] f = new int[x.length];
		for(int i=0; i<x.length; i++) {
			for(int j=0; j<x.length; j++) {
				if(j <= i && x[i] <= x[j])
					f[i]++;
				if(j > i && x[i] < x[j])
					f[i]++;
			}
		}
		return f;
	}

}
