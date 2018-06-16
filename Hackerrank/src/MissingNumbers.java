import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class MissingNumbers {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, Integer> map2 = new HashMap<>();
		int n = scanner.nextInt();
		while(n-- > 0) {
			int num = scanner.nextInt();
			Integer count = map.get(num);
			if(count == null)
				count = 0;
			count++;
			map.put(num, count);
		}
		int m = scanner.nextInt();
		while(m-- > 0) {
			int num = scanner.nextInt();
			Integer count = map2.get(num);
			if(count == null)
				count = 0;
			count++;
			map2.put(num, count);
		}
		Set<Integer> diffNum = new HashSet<Integer>();
		Set<Integer> keySet = map2.keySet();
		for(Integer key : keySet) {
			if(map2.get(key).intValue() != map.get(key).intValue()) {
				diffNum.add(key);
			}
		}
		
		Integer[] resultArr = diffNum.toArray(new Integer[0]);
		Arrays.sort(resultArr);
		for(Integer num : resultArr) {
			System.out.print(num + " ");
		}
	}

}
