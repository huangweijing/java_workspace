import java.util.Scanner;


public class MakingAnagrams {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		String str1 = scanner.next();
		String str2 = scanner.next();
		
		int[] a1 = new int[26];
		int[] a2 = new int[26];
		
		for(int i=0; i<str1.length(); i++) {
			char ch = str1.charAt(i);
			a1[ch-'a']++;
		}
		
		for(int i=0; i<str2.length(); i++) {
			char ch = str2.charAt(i);
			a2[ch-'a']++;
		}
		
		int result = 0;
		for(int i=0; i<a1.length; i++) {
			result += Math.abs(a1[i] - a2[i]);
		}
		
		System.out.println(result);
	}
}
