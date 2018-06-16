import java.util.Scanner;


public class SequenceEquation {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int[] p = new int[n+1];
		int[] p2 = new int[n+1];
		for(int i=1; i<=n; i++) {
			p[i] = scanner.nextInt();
			p2[p[i]] = i;
		}

		for(int i=1; i<=n; i++)
			System.out.println(p2[p2[i]]);
	}

}
