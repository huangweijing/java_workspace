import java.util.Scanner;


public class TaumAndBsday {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int t = scanner.nextInt();
		while(t-- > 0) {
			long b = scanner.nextInt();
			long w = scanner.nextInt();
			long bc = scanner.nextInt();
			long wc = scanner.nextInt();
			long z = scanner.nextInt();
			
			long cost = b * bc + w * wc;
			if(bc + z < wc) {
				cost = b * bc + w * bc + z * w;
			}
			if(wc + z < bc) {
				cost = b * wc + w * wc + z * b;
			}
			
			System.out.println(cost);
		}

	}

}
