import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class DownToZeroII3 {

	private static int[] result = new int[1000001]; 
	public static void prepare() {
		//preparation
		for(int i=0; i<result.length; i++) {
			if( i <= 3)
				result[i] = i;
			for(int j=2; j<=i; j++) {
				if(j * i >= 1000001)
					break;
				int val = result[j * i];
				if(val == 0 || result[i] + 1 < val )
					result[j*i] = result[i] + 1;
			}
			if(i+1 >= 1000001)
				break;
			int val = result[i+1];
			if(val == 0 || val > result[i] + 1)
				result[i+1] = result[i] + 1; 
		}
				
	}

	private static final Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {

		prepare();
		
		int q = Integer.parseInt(scanner.nextLine().trim());

		for (int qItr = 0; qItr < q; qItr++) {
			int n = Integer.parseInt(scanner.nextLine().trim());
			System.out.println(result[n]);
		}

	}
}
