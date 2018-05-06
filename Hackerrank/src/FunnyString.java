import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class FunnyString {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int cnt = scanner.nextInt();
		while(cnt-- > 0) {
			String str = scanner.next();
			boolean funnyFlg = true;
			for(int i=1; i<str.length(); i++) {
				int n0 = str.charAt(i);
				int n1 = str.charAt(i - 1);
				int n2 = str.charAt(str.length() - 1 - i);
				int n3 = str.charAt(str.length() - i);
				
				if(Math.abs(n2 - n3) != Math.abs(n1 - n0)) {
					funnyFlg = false;
					break;
				}
			}
			System.out.println(funnyFlg? "Funny" : "Not Funny");
		}
		
	}
		
		

}
