import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class DownToZeroII {

	/*
	 * Complete the downToZero function below.
	 */
	public static int downToZero(int n) {
		int cnt = 0;
		while(n > 0) {
			int divider = getMiddleDivider(n);
//			System.out.println("divider=" + divider);
			
			if(divider < n) {
				n = divider;
				cnt++;
			} else {
				n--;
				cnt++;
			}
			
			System.out.println("n:" + n);
				
		}
		return cnt;
	}
	

	public static  int getMiddleDivider(int n) {
		double r = Math.sqrt(n);
		if((int)r < r) 
			r = r + 1;
		int rootN = (int)r;
		if(rootN==1)
			rootN = 2;
		for(int i=rootN; i<=n; i++) {
			if(n%i==0)
				return i;
		}
		return n;
	}

	private static final Scanner scanner = new Scanner(System.in);

//	public static void main(String[] args) throws IOException {
//		System.out.println(getMiddleDivider(15));
//	}
	
	
	public static void main(String[] args) throws IOException {
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(scanner.nextLine().trim());

		for (int qItr = 0; qItr < q; qItr++) {
			int n = Integer.parseInt(scanner.nextLine().trim());

			int result = downToZero(n);
			System.out.println(result);
//			bufferedWriter.write(String.valueOf(result));
//			bufferedWriter.newLine();
		}

//		bufferedWriter.close();
	}
}
