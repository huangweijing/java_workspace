import java.util.Arrays;
import java.util.Scanner;


public class SherlockAndAnagrams {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main2(String[] args) {
//		System.out.println(countAnagrams(dic, length, s))
	}
	
	public static void main(String[] args) {
//		System.out.println(isAnagrams("aabc", "bdaa"));
//		System.exit(0);
		
		int cnt = scanner.nextInt();
		while(cnt-- > 0) {
			String str = scanner.next();
//			int[] dic = new int[26];
			int result = 0;
			for(int i=0; i<str.length(); i++) {
				for(int j=i+1; j<=str.length(); j++) {
					String substr = str.substring(i, j);
//					System.out.println("---" + substr);
					for(int k=i+1; k<=str.length()-substr.length(); k++) {
						String substr2 = str.substring(k, k+substr.length());
						if(isAnagrams(substr, substr2)) {
//							System.out.println(substr + "," + substr2);
							result++;
						}
					}
				}
				
			}
			System.out.println(result);
		}
	}
	
	public int solve(String str) {
		
		return 0;
	}
	
	public static boolean isAnagrams(String s1, String s2) {
		if(s1.length() != s2.length())
			return false;
		
		int[] dic = new int[26];
		for(int i=0; i<s1.length(); i++) {
			dic[s1.charAt(i)-'a']++;
		}
		for(int i=0; i<s2.length(); i++) {
			//dic[s2.charAt(i)-'a']--;
			if(--dic[s2.charAt(i)-'a'] < 0)
				return false;
		}
		return true;
	}
	
	public static int countAnagrams(int dic[], int length, String s) {
		int result = 0;
		for(int i=0; i<s.length() - length - 1; i++) {
			boolean isAnagrams = true;
			int[] dic_backup = new int[26];
			for(int j=i; j < length; j++) {
				dic_backup[s.charAt(j)-'a']++;
				if(dic_backup[s.charAt(j)-'a'] > dic[s.charAt(j)-'a']) {
					isAnagrams = false;
					break;
				}
			}
			if(isAnagrams)
				result++;
		}
		return result;
	}
}
