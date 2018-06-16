import java.util.Scanner;


public class LonelyInteger {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int result = scanner.nextInt();
		while(n-- > 1) result = result ^ scanner.nextInt();
		System.out.println(result);
		
		
	}

}
