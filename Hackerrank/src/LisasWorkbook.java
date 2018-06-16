import java.util.Arrays;
import java.util.Scanner;


public class LisasWorkbook {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int n=scanner.nextInt();
		int k=scanner.nextInt();
		int[]arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = scanner.nextInt();
		}
//		System.out.println("n=" + n + ",k=" + k + ", arr=" + Arrays.toString(arr));
		
		int page = 1;
		int special = 0;
		for(int i=0; i<arr.length; i++) {
			int pageSpace = k;
			for(int p=1; p<=arr[i]; p++) {
				pageSpace--;
				if(page == p) {
					special++;
				}
//				System.out.println("page=" + page + ", problem=" + p);
				if(pageSpace == 0) {
					page++;
					pageSpace = k;
				}
			}
			if(pageSpace != 0 && pageSpace != k)
				page++;
		}
		System.out.println(special);
	}

}
