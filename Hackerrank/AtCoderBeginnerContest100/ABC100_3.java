import java.util.Scanner;


public class ABC100_3 {

    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {

		int t = scanner.nextInt();
		int result = 0;
		while(t-- > 0) {
			int n = scanner.nextInt();
			while((n & 1) == 0) {
				n = n >> 1;
				result++;
			}
		}
		System.out.println(result);
	}

}
