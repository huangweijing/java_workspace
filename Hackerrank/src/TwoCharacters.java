import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class TwoCharacters {

	private static Scanner scanner = new Scanner(System.in);

	static boolean isAlternating(String str) {
		for(int i=1; i<str.length(); i++) {
			if(str.charAt(i-1)==str.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		scanner.nextInt();
		String str = scanner.next();
		
		Set<Character> set = new HashSet<>();
		for(int i=0; i<str.length(); i++) {
			set.add(str.charAt(i));
		}
		
		if(set.size() < 2) {
			System.out.println(0);
		} else {
			int maximum = 0;
			List<Character> list = new ArrayList<>(set);
			for(int i=0; i<list.size(); i++) {
				for(int j=i+1; j<list.size(); j++) {
					String tmpStr = str.replaceAll( "[^" + list.get(i) + list.get(j) + "]", "");
					if(isAlternating(tmpStr)) {
						if(tmpStr.length() > maximum)
							maximum = tmpStr.length();
					}
				}
			}
			System.out.println(maximum);
		}
		
	}

}
