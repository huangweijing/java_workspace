import java.util.Scanner;


public class ABC101_2 {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String s = scanner.next();
		int mod = 0;
		for(int i=0; i<s.length(); i++) {
			int n = ((int)(s.charAt(i) - '0'));
			mod += n;
		}
		
		if(Long.valueOf(s) % mod == 0)
			System.out.println("Yes");
		else
			System.out.println("No");
	}

}
