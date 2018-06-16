import java.util.Scanner;


public class SherlockAndCost {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int t = scanner.nextInt();
		while(t-- > 0) {
			int n = scanner.nextInt();
			
			int[] low_solution = new int[n+1];
			int[] high_solution = new int[n+1];
			int[] solution = new int[n+1];
			
			int b[] = new int[n+1];
			for(int i=1; i<=n; i++) {
				b[i] = scanner.nextInt();
			}
			
			for(int i=1; i<=n; i++) {
				solution[i] = Math.max(low_solution[i], high_solution[i]);
				if(i+1 > n) {
					break;
				}
				low_solution[i + 1] = Math.max(low_solution[i]
						, high_solution[i] + b[i] - 1);
				high_solution[i + 1] = Math.max(low_solution[i] + b[i + 1] - 1
						, high_solution[i] + Math.abs(b[i+1] - b[i]));
			}
			
//			System.out.println(Arrays.toString(low_solution));
//			System.out.println(Arrays.toString(high_solution));
//			System.out.println(Arrays.toString(solution));
			
			System.out.println(solution[n]);
		}
//		System.out.println();
	}
}
