import java.util.Scanner;


public class ABC101_1 {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[]a = new int[n];
		int oneIdx = -1;
		for(int i=0; i<n; i++) {
			a[i] = scanner.nextInt();
			if(a[i] == 1)
				oneIdx = i;
		}
		int leftCnt = oneIdx;
		int rightCnt = n - oneIdx - 1;
		
		int leftTime = leftCnt / (k - 1);
		int rightTime = rightCnt / (k - 1);
		
		int totalTime = leftTime + rightTime;
		
		int leftMode = leftCnt % (k - 1);
		int rightMode = rightCnt % (k - 1);
		
		if(leftMode + rightMode > k - 1)
			totalTime += 2;
		else if(leftMode + rightMode > 0)
			totalTime += 1;
		
		System.out.println(totalTime);
		
	}

}
