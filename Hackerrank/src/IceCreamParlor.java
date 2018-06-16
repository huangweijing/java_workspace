import java.util.Scanner;


public class IceCreamParlor {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int t = scanner.nextInt();
		while(t-- > 0) {
			int m = scanner.nextInt();
			int n = scanner.nextInt();
			int[] c = new int[n+1];
			int r1=0, r2=0;
			boolean found = false;
			for(int i=1; i<=n; i++) {
				c[i] = scanner.nextInt();
				if(found)
					continue;
				for(int j=1; j<i; j++) {
					if(c[i] + c[j] == m) {
						found = true;
						r1 = j;
						r2 = i;
						break;
					}
				}
			}
			System.out.println(r1 + " " + r2);
		}
	}

}
