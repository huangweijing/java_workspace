import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class SherlockAndTheValidString {
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String s = scanner.next();
		int[] charCnt = new int[26];
		for(int i=0; i<s.length(); i++) {
			charCnt[s.charAt(i) - 'a']++;
		}
		boolean isOkay = true;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<charCnt.length; i++) {
			if(charCnt[i] == 0)
				continue;
			Integer cnt = map.get(charCnt[i]);
			if(cnt == null) 
				cnt = 0;
			map.put(charCnt[i], ++cnt);
			if(map.size() > 2) {
				isOkay = false;
				break;
			}
		}
		Integer[] arr = map.keySet().toArray(new Integer[0]);
		if(arr.length == 2 && (Math.abs(arr[0] - arr[1]) != 1
				|| map.get(Math.max(arr[0], arr[1])) != 1))
			isOkay = false;
		if(arr.length == 2 && (arr[0] == 1 && map.get(arr[0]) == 1 
				|| arr[1] == 1 && map.get(arr[1]) == 1))
			isOkay = true;
		System.out.println(isOkay? "YES" : "NO");
	}

}
