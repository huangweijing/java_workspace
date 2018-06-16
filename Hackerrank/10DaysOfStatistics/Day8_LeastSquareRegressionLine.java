import java.math.BigDecimal;
import java.util.Scanner;


public class Day8_LeastSquareRegressionLine {

    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		Double[] X = new Double[] {
			95d, 85d, 80d, 70d, 60d
		};
		Double[] Y = new Double[] {
			85d, 95d, 70d, 65d, 70d
		};
		Double[] XY = new Double[X.length];
		Double[] XSqr = new Double[X.length];
		
		Double sumX = 0d;
		Double sumY = 0d;
		Double sumXY = 0d;
		Double sumXSqr = 0d;
		for(int i=0; i<X.length; i++) {
			sumX += X[i];
			sumY += Y[i];
			XY[i] = X[i] * Y[i];
			sumXY += XY[i];
			XSqr[i] = X[i] * X[i];
			sumXSqr += XSqr[i];
		}
		
		Double b = (X.length * sumXY - sumX * sumY) / (X.length * sumXSqr - sumX * sumX);
		Double meanX = getMean(X);
		Double meanY = getMean(Y);
		Double a = meanY - b * meanX;
		
		Double x = 80d;
		Double y = a + b * x;
		
		BigDecimal bd = BigDecimal.valueOf(y).setScale(3, BigDecimal.ROUND_HALF_UP);
		System.out.println(bd);
		
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
