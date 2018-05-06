import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class AlternatingCharacters {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int cnt = scanner.nextInt();
		while(cnt-- > 0) {
			String str = scanner.next();
			int deletionCnt = 0;
			for(int i=0; i<str.length() - 1; i++) {
				if(str.charAt(i) == str.charAt(i+1)) {
					deletionCnt++;
				}
			}
			System.out.println(deletionCnt);
		}
	}
}
