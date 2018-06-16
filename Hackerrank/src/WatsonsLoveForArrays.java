import java.util.Scanner;


public class WatsonsLoveForArrays {

	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		
		int t = scanner.nextInt();
		while(t-- > 0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			int k = scanner.nextInt();
			int[] a = new int[n+1];
			//b=a%m
//			int[][] b = new int[n+1][n+1];
			int[] c = new int[n+1];
			int result = 0;
			for(int i=0; i<n; i++) {
				a[i] = scanner.nextInt();
				c[i] = a[i] % m;
				if(c[i] == k){
					result++;
				}
				for(int j=0; j<i; j++) {
					c[j] = (c[j] * c[i]) % m;
					if(c[j] == k) {
						result++;
					}
				}
			}
			System.out.println(result);
		}
		

	}

}
