import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class DownToZeroII2 {

	/*
	 * Complete the downToZero function below.
	 */
	public static int downToZero(int n) {
		int cnt = 0;
		while(n > 0) {
			int divider = getMiddleDivider(n);
			
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
		int minMaxDivider = n;
		for(int i=2; i<n; i++) {
			int divider = 0;
			if(n%i == 0) {
				divider = n / i;
			} else {
				continue;
			}
			if(i > divider)
				divider = i;
			if(divider < minMaxDivider)
				minMaxDivider = divider;
		}
		return minMaxDivider;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main2(String[] args) throws IOException {
		System.out.println(getMiddleDivider(15));
	}
	
	
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
