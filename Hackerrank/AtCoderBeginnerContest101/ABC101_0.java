import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ABC101_0 {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int k = scanner.nextInt();
		List<Long> printSnuke = printSnuke(k);
		for(Long num : printSnuke)
			System.out.println(num);
	}
	
	public static List<Long> printSnuke(int count) {
		int cnt = 0;
		List<Long> snukeList = new ArrayList<>();
		for(Long i=1L; i<=9; i++) {
			snukeList.add(i);
			cnt++;
			if(count == cnt)
				return snukeList;
		}
		for(Long i=1L; i<=100000; i++) {
			long tenpow = (long)Math.pow(10, i);
			for(Long j=1L; j<=10+9*(i-1); j++) {
				if(i==1 && j == 10)
					break;
				long num = tenpow + snukeList.get(snukeList.size() - 1);
				snukeList.add(num);
				
				cnt++;
				if(count == cnt)
					return snukeList;
			}
		}
		return snukeList;
//		
	}

}
