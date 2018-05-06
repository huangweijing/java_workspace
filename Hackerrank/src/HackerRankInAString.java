import java.util.Scanner;


public class HackerRankInAString {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int cnt = scanner.nextInt();
		String hackerranker = "hackerrank";
		
		while(cnt-- > 0) {
			int idx = 0;
			String str = scanner.next();
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i) == hackerranker.charAt(idx)) {
					idx++;
					if(idx == hackerranker.length())
						break;
				}
			}
			System.out.println(idx == hackerranker.length()?"YES" : "NO");
		}
		
		
	}

}
