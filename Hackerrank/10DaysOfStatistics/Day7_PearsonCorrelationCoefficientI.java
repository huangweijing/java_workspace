import java.math.BigDecimal;
import java.util.Scanner;


public class Day7_PearsonCorrelationCoefficientI {
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
		Double result = getPearsonCorrelationCoefficient(x, y);
		BigDecimal bd = BigDecimal.valueOf(result).setScale(3, BigDecimal.ROUND_HALF_UP);
		System.out.println(bd);
	}

	public static Double getPearsonCorrelationCoefficient(Double[] x, Double[] y) {
		Double xMean = getMean(x);
		Double yMean = getMean(y);
		Double xSd = calcStandardDeviation(x);
		Double ySd = calcStandardDeviation(y);
		Double sum = 0d;
		for(int i=0; i<x.length; i++) {
			sum += (x[i] - xMean) * (y[i] - yMean);
		}
		return sum / (xSd * ySd * x.length);
	}

	public static Double calcStandardDeviation(Double[] arr) {
		
		Double mean = getMean(arr);
		Double sum = 0d;
		for(int i=0; i<arr.length; i++) {
			Double d = arr[i] - mean;
			sum += d * d;
		}
		return Math.sqrt(sum / arr.length);
	}
	
	public static Double getMean(Double[] arr) {
		Double sum = 0d;
		for(Double d : arr) {
			sum += d;
		}
		return sum / arr.length;
	}
}
