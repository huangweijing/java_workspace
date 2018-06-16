import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;


public class Day0_MeanMedianAndMode {

    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int[] arr = new int[n];
		int sum = 0;
		for(int i=0; i<n; i++) {
			arr[i] = scanner.nextInt();
			sum += arr[i];
		}
		Arrays.sort(arr);
		Double mean = (double)sum / arr.length;
		Double median;
		if((arr.length & 1) == 1) {
			median = (double)arr[(arr.length - 1) >> 1];
		} else {
			median = ((double)arr[(arr.length - 1) >> 1] 
					+ (double)arr[(arr.length - 1 >> 1 ) + 1]) / 2;
		}
		
		int prev = -1;
		int repeat = 0;
		int mode = -1;
		int repeatMax = -1;
		for(int i=0; i<n; i++) {
			if(arr[i] == prev) {
				repeat++;
			} else {
				repeat = 0;
			}
			if(repeat > repeatMax) {
				repeatMax = repeat;
				mode = arr[i];
			}
			prev = arr[i];
		}
		System.out.println(BigDecimal.valueOf(mean).setScale(1, BigDecimal.ROUND_UP));
		System.out.println(BigDecimal.valueOf(median).setScale(1, BigDecimal.ROUND_UP));
		System.out.println(mode);
	}

}
