import java.util.Scanner;


public class MaximizingXOR {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int l = scanner.nextInt();
		int r = scanner.nextInt();
		System.out.println(solution2(l, r));
	}
	
	static int solution2(int l, int r) {
		return (Integer.highestOneBit(l ^ r) << 1) - 1;
	}

	static int solution(int l, int r) {
		int max = 0;
		for(int i=l; i<=r; i++) {
			for(int j=i; j<=r; j++) {
				int result = i ^ j;
				if(result > max)
					max = result;
			}
		}
		return max;
	}
}
