import java.util.Arrays;
import java.util.Scanner;


public class SherlockAndArray {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int t = scanner.nextInt();
		while(t-- > 0) {
			int n = scanner.nextInt();
			int[] arr = new int[n];
			int[] sumFromLeft = new int[n];
			for(int i=0; i<n; i++) {
				arr[i] = scanner.nextInt();
				if( i>0 ) {
					sumFromLeft[i] = sumFromLeft[i-1] + arr[i];
				} else {
					sumFromLeft[i] = arr[i];
				}
			}
			boolean isOkay = sumFromLeft.length == 1 ? true : false;
			for(int i=1; i<n; i++) {
				int sumFromRight = sumFromLeft[sumFromLeft.length - 1]
						- sumFromLeft[i - 1];
				if(i >= 2 && sumFromLeft[i-2] == sumFromRight) {
					isOkay = true;
					break;
				} else if(i == 1 && sumFromRight == 0) {
					isOkay = true;
					break;
				}
			}
			System.out.println(isOkay? "YES":"NO");
		}
	}

}
