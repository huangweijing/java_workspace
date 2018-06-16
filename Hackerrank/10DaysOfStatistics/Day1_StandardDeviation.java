import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;


public class Day1_StandardDeviation {

    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		Double[] arr = new Double[n];
		for(int i=0; i<n; i++) {
			arr[i] = scanner.nextDouble();
		}
		Double result = calcStandardDeviation(arr);
		System.out.println(BigDecimal.valueOf(result).setScale(1, RoundingMode.HALF_UP));
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
