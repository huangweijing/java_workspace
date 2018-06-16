import java.util.Scanner;


public class SumVsXOR {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		long n = scanner.nextLong();
		System.out.println(1l << (64 - Long.numberOfLeadingZeros(n) - Long.bitCount(n)));
	}

}
