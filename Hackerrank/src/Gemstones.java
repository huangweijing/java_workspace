import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Gemstones {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int cnt = scanner.nextInt();
		Set<Character> gemStoneSet = new HashSet<>();
		char[] alphbetTable = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for(char ch : alphbetTable) {
			gemStoneSet.add(ch);
		}
		while(cnt-- > 0) {
			String str = scanner.next();
			Set<Character> set = new HashSet<>();
			for(int i=0; i<str.length(); i++) {
				set.add(str.charAt(i));
			}
			gemStoneSet.retainAll(set);
		}
		
		System.out.println(gemStoneSet.size());
		
	}
		
		

}
