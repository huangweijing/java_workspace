import java.util.Scanner;


public class CounterGame {
	
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int t = scanner.nextInt();
		while(t-- > 0) {
			String player = "Louise";
			long n = scanner.nextLong();
			while(true) {
				long nearest2 = Long.highestOneBit(n);
				if(nearest2 == n) { 
					n = n >> 1;
				} else 
					n = n - Long.highestOneBit(n);
				if(n == 1) break;
				player = player == "Richard" ? "Louise" : "Richard";
			}
			System.out.println(player);
		}
	}

}
