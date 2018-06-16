import java.util.Scanner;


public class MarsExploration {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		String line = scanner.nextLine();
		Integer count = 0;
		for(int i=0; i<line.length(); i++) {
			if(i%3 == 0 && line.charAt(i) != 'S')
				count++;
			else if(i%3 == 1 && line.charAt(i) != 'O')
				count++;
			else if(i%3 == 2 && line.charAt(i) != 'S')
				count++;
		}
		System.out.println(count);
	}

}
