import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class HowManySubstrings {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		scanner.nextInt();
		int q = scanner.nextInt();
		String input = scanner.next();
//		String[][] database = makeDatabase(input);
		while(q-- > 0) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
//			System.out.printf("%s, %s, %s", input, start, end);
//			System.out.println(countOfSubset(input, start, end));
//			System.out.println(countOfSubset(database, start, end));
		}
		System.out.println(input.length());
		
	}
	
	public static String[][] makeDatabase(String str) {
		String[][] database = new String[str.length()][str.length()];
		for(int i=0; i<=str.length()-1; i++) {
			for(int j=0; j<=i; j++) {
				database[i][j] = str.substring(j, i+1);
			}	
//			System.out.println(Arrays.toString(database[i]));
		}
		return database;
	}
	
	public static long countOfSubset(String[][] database, int start, int end) {
		Set<String> set = new HashSet<String>();
		for(int i=start; i<=end; i++) {
			for(int j=start; j<=i; j++) {
				set.add(database[i][j]);
			}
		}
//		System.out.println(set);
		return set.size();
	}
	
	public static long countOfSubset(String str, int start, int end) {
		Set<String> set = new HashSet<String>();
		for(int i=start; i<=end; i++) {
			for(int j=i+1; j<=end+1; j++) {
				String substr = str.substring(i, j);
				set.add(substr);
//				System.out.println(substr);
			}
		}
//		System.out.println(set);
		return set.size();
	}
}
