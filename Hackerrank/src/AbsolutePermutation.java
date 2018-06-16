import java.util.Scanner;


public class AbsolutePermutation {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int t = scanner.nextInt();
		while(t-- > 0) {
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			
			if(k == 0){
				for(int i=1; i<=n; i++) {
					System.out.print(i + " ");
				}
				System.out.println();
				continue;
			} else if(n % (2 * k) != 0) {
				System.out.println(-1);
				continue;
			} else {
				for(int i=1; i<=n; i++) {
					int mod = i % (2 * k);
					if(mod <= k && mod !=0) {
						System.out.print((i + k) + " ");
					} else {
						System.out.print((i - k) + " ");
					}
				}
				System.out.println();
			}
		}
	}

}
