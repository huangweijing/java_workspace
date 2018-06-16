import java.util.Scanner;


public class NonDivisibleSubset {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[] s = new int[k];
		for(int i=0; i<n; i++) {
			s[scanner.nextInt() % k]++;
		}
		int result = 0;
		for(int i=1; 2 * i < k; i++) {
			if(s[i] > s[k-i]) {
				result += s[i];
			} else {
				result += s[k-i];
			}
		}
//		if(s[0] > 0)
//			result++;
		if((k & 1) == 0 && s[k >> 1] > 0)
			result++;
		System.out.println(result);
	}

}
