import java.util.Arrays;
import java.util.Scanner;


public class XorryQueries {

	public static Scanner scanner = new Scanner(System.in);
	public static Integer pArr[];
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int p = scanner.nextInt();
		int[] a = new int[n+1];
		for(int i=1; i<=n; i++) {
			a[i] = scanner.nextInt();
		}
		pArr = new Integer[n+1];
		
//		System.out.println("p3=" + P(3, p, a));
		while(m-- > 0) {
			
			int type = scanner.nextInt();
			
			if(type == 1) {
				int i = scanner.nextInt();
				int x = scanner.nextInt();
				a[i] = a[i] ^ x;
			} else {
				int l = scanner.nextInt();
				int r = scanner.nextInt();
				int result = P(l, p, a);
				for(int i=l+1; i<=r; i++) {
					int pn = P(i, p, a);
					result = result + pn;
				}
				System.out.println(result);
			}
//			System.out.println(Arrays.toString(a));
		}
	}
	
	public static int P(int i, int p, int[] a) {
		if(i+p-1>a.length-1)
			return 0;
		if(pArr[i-1] != null) {
//			System.out.println("i=" + i + ",p=" + p);
			return pArr[i-1] ^ a[i-1] ^ a[i+p-1]; 
		}

//		System.out.println("i=" + i + ",p=" + p);
		int returnVal = a[i];
		for(int idx=i+1; idx<=i+p-1; idx++) {
			returnVal = returnVal ^ a[idx];
		}
		pArr[i] = returnVal;
		return returnVal;
	}

}
