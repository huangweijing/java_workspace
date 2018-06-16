import java.util.Scanner;


public class TheMostElegantSequence {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int q = scanner.nextInt();
		int[] b = new int[n];
		String[] s = new String[n];
		for(int i=0; i<n; i++) {
			b[i] = scanner.nextInt();
		}
		for(int i=0; i<n; i++) {
			s[i] = scanner.next();
		}
		
	}

}
