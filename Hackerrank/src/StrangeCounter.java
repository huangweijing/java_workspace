import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StrangeCounter {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		long t = scanner.nextLong();
		List<Long> list = new ArrayList<Long>();
		for(int i=0;list.size() == 0 || list.get(list.size() - 1) <= 1000000000000l;i++) {
			long num = 3 * (1l << i);
			if(list.size() > 0)
				num = list.get(list.size() - 1) + num;
			list.add(num);
		}
		int range = 0;
		for(range=0; range<list.size(); range++) {
			if(list.get(range) >= t) {
				break;
			}
		}
		if(range > 0)
			t = t - list.get(range - 1);
		System.out.println(3 * (1l << range) - t + 1);
	}

}
