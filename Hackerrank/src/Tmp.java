import java.util.Arrays;
import java.util.Scanner;

public class Tmp {
	public static void main(String[] args) {
		double[] ksk = ksk();
//		System.out.println(Arrays.toString(ksk));
		loop: for(int i=1; i<ksk.length; i++) {
			if(ksk[i] == 0)
				continue;
			for(int j=i+1; j<ksk.length; j++) {
				if(ksk[j] == 0)
					continue;
				if(ksk[j] < ksk[i])
					continue loop;
			}
			System.out.println(i);
		}
	}

	public static double[] ksk() {
		double[] result = new double[80000000];
		for(int i=1; i<result.length; i++) {
			if(i % 10 != 9)
				continue;
			result[i] = (double)i / s(i);
		}
		return result;
	}
	
	public static boolean isSnuke(long num) {
		if(num % 10 != 9)
			return false;
		for(long i = num + 1; i <= 1000000l; i++) {
		}
		return true;
	}
	
	public static long s(long num) {
		String s = Long.valueOf(num).toString();
		long number = 0;
		for(int i=0; i<s.length(); i++) {
			number = number + Integer.parseInt(s.charAt(i)+"");
		}
		return number;
	}
	
	public static void main2(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		int N = scanner.nextInt();
		
		for(int i=1; i<100000; i++) {
			System.out.println(i + "," + Solution(i));
		}
	}
	static int Solution(int N) {
		int number = 0;
		String s = N+"";
		for(int i=0; i<s.length(); i++) {
			number = number + Integer.parseInt(s.charAt(i)+"");
		}
		return number;
	}
}
