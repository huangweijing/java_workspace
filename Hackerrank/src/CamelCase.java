import java.util.Scanner;


public class CamelCase {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		String str = scanner.next();
		int wordCnt = 1;
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if(ch >= 'A' && ch <= 'Z') {
				wordCnt++;
			}
		}
		System.out.println(wordCnt);
	}

}
