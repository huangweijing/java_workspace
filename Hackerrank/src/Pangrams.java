import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Pangrams {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		String str = scanner.nextLine();
		str = str.toLowerCase();
		Set<Character>set = new HashSet<>();
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == ' ')
				continue;
			set.add(str.charAt(i));
		}
//		System.out.println(set.size());
//		System.out.println(set);
		System.out.println(set.size() == 26 ? "pangram" : "not pangram");
		
	}
		
		

}
