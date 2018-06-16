import java.util.Scanner;


public class BeautifulBinaryString {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		scanner.nextInt();
		String line = scanner.next();
		int idx = -1;
		int result = 0;
		while(true) {
			idx = line.indexOf("01010");
			if(idx != -1) {
				line = line.substring(0, idx) + "01110" + 
						line.substring(idx + 5);
				result++;
			} else 
				break;
		}
		while(true) {
			idx = line.indexOf("010");
			if(idx != -1) {
				line = line.substring(0, idx) + "000" + 
						line.substring(idx + 3);
				result++;
			} else 
				break;
		}
		System.out.println(result);
	}

}
