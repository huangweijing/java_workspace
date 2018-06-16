import java.util.Scanner;


public class WhichSection {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int t = scanner.nextInt();
		while(t-- > 0) {
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			int m = scanner.nextInt();
			int[] a = new int[m];
			for(int i=0; i<m; i++) {
				a[i] = scanner.nextInt();
			}
			int result = 0;
			for(int i=0; i<m; i++) {
				k = k - a[i];
				if(k <= 0) {
					result = i + 1;
					break;
				}
			}
			System.out.println(result);
		}
	}

}
