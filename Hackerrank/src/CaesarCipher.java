import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CaesarCipher {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Map<Character, Character> cipherMap = new HashMap<>();
		String original = "abcdefghijklmnopqrstuvwxyz";
		String originalBig = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		
		scanner.nextInt();
		String str = scanner.next();
		int rotateBy = scanner.nextInt();
		rotateBy = rotateBy % 26;
		
		String rotated = original.substring(rotateBy) + original.substring(0, rotateBy);
		String rotatedBig = originalBig.substring(rotateBy) + originalBig.substring(0, rotateBy);
		
		original = original + originalBig;
		rotated = rotated + rotatedBig;

//		System.out.println(original);
//		System.out.println(rotated);
		
		for(int i=0; i<original.length(); i++) {
			cipherMap.put(original.charAt(i), rotated.charAt(i));
		}
		
		String result = "";
		for(int i=0; i<str.length(); i++) {
			if(cipherMap.containsKey(str.charAt(i))) {
				result = result + cipherMap.get(str.charAt(i));
			} else {
				result = result += str.charAt(i);
			}
		}
		System.out.println(result);
		
	}
}
