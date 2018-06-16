import java.util.Arrays;
import java.util.Scanner;


public class CommonChild {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String s1 = scanner.next();
		String s2 = scanner.next();
		
		int[] s1Dic = new int[s1.length()];
		int[] maxIdx = new int[26];
		Arrays.fill(maxIdx, -1);
		
		for(int i=0; i<s1.length(); i++) {
			char ch = s1.charAt(i);
			s1Dic[i] = s2.indexOf(ch, maxIdx[ch - 'A'] + 1);
			if(s1Dic[i] != -1)
				maxIdx[ch - 'A'] = s1Dic[i]; 
		}
		
		int max = 0;
		for(int i=0; i<s1Dic.length; i++) {
			int s2Idx = s1Dic[i];
			int count = 0;
			for(int j=i; j<s1Dic.length; j++) {
				if(s2Idx < s1Dic[j]) {
					count++;
				}
			}
			if(max < count) {
				max = count;
			}
		}
		System.out.println(max);
		System.out.println(Arrays.toString(s1Dic));
		System.out.println(Arrays.toString(maxIdx));
		
	}

}
