import java.util.Scanner;


public class ThePowerSum {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int x = scanner.nextInt();
		int n = scanner.nextInt();
		int result = count(1, x, n);
		System.out.println(result);
	}
	
	public static int count(int from, int x, int n) {
		int sum = 0;
		for(int i=from; Math.pow(i, n)<=x; i++) {
			int newX = x - (int)Math.pow(i, n);
			if(newX == 0)
				sum++;
			sum += count(i+1, newX, n);
		}
		return sum;
	}

}
