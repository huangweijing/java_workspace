import java.util.Scanner;


public class BeautifulDaysAtTheMovies {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		Integer i = scanner.nextInt();
		Integer j = scanner.nextInt();
		Integer k = scanner.nextInt();
		int count = 0;
		for(int idx = i; idx <= j; idx++) {
			Integer revNum = reverse(idx);
			if((revNum - idx) % k == 0) {
				count++;
			}
		}
		System.out.println(count);
	}
	
	public static Integer reverse(Integer num) {
		char[] intSet = num.toString().toCharArray();
		int result = 0;
		for(int i=0; i<intSet.length; i++) {
			result = result * 10 + (intSet[intSet.length - 1 - i] - '0'); 
		}
		return result;
	}

}
