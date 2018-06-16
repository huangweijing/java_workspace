import java.util.Scanner;


public class RecursiveDigitSum {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String n = scanner.next();
		int k = scanner.nextInt();
		System.out.println(superDigit(n, k));
	}
	
	public static int superDigit(String n, int k) {
		Long num = 0l;
		for(int i=0; i<n.length(); i++) {
			num += n.charAt(i) - '0';
		}
		num = num * k;
		if(num < 10)
			return num.intValue();
		else
			return superDigit(num.toString(), 1);
	}

}
