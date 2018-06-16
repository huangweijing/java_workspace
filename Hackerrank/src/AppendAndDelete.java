import java.util.Scanner;


public class AppendAndDelete {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String s1 = scanner.next();
		String s2 = scanner.next();
		int k = scanner.nextInt();
		int s = 0;
		for(int i=0; i<s2.length() && i < s1.length(); i++) {
			if(s1.charAt(i) == s2.charAt(i)) {
				s++;
			} else {
				break;
			}
		}
		int d1 = s1.length() - s;
		int d2 = s2.length() - s;
		if( ((k - (d1 + d2)) % 2 == 0 && k >= (d1 + d2))|| d1 + 2*s + d2 <= k ) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

}
