import java.util.Arrays;
import java.util.Scanner;


public class ABC100_4 {

    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {

		int n = scanner.nextInt();
		int m = scanner.nextInt();
		long[][] totalValue = new long[8][n];
		for(int i=0; i<n; i++) {
			long x = scanner.nextLong();
			long y = scanner.nextLong();
			long z = scanner.nextLong();
			for(int j=0; j<8; j++) {
//				System.out.println((4&j) + "," + (2&j)+ "," + (1&j));
				if((4 & j) == 4)
					totalValue[j][i] += -x;
				else
					totalValue[j][i] += x;
				if((2 & j) == 2)
					totalValue[j][i] += -y;
				else
					totalValue[j][i] += y;
				if((1 & j) == 1)
					totalValue[j][i] += -z;
				else
					totalValue[j][i] += z;
			}
		}
		long max = Long.MIN_VALUE; 
		for(int i=0; i<8; i++) {
//			System.out.println(Arrays.toString(totalValue[i]));
			Arrays.sort(totalValue[i]);
//			System.out.println(Arrays.toString(totalValue[i]));
			long sum = 0l;
			for(int j=n-m; j<n; j++) {
				sum += totalValue[i][j];
			}
//			System.out.println(sum);
			if(sum > max)
				max = sum;
		}
		System.out.println(max);
	}
}
