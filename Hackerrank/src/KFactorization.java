import java.util.Scanner;


public class KFactorization {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int a[] = new int[k+1];
		for(int i=1; i<k; i++)
			a[i] = scanner.nextInt();
	}

}
