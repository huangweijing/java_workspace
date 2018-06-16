import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class MinuteToWinIt {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		long n = scanner.nextLong();
		long k = scanner.nextLong();
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		for(long i=0; i<n; i++) {
			long a = scanner.nextLong();
			long b = a - i * k;
			Integer count = map.get(b);
			if(count == null)
				count = 0;
			map.put(b, count + 1);
		}
		List<Integer> list = new ArrayList<Integer>(map.values());
		Collections.sort(list);
		System.out.println(n - list.get(list.size() - 1));
				
	}

}
