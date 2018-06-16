import java.util.Arrays;
import java.util.Scanner;


public class Day1_Quartiles {

    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int arr[] = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = scanner.nextInt();
		}
		Arrays.sort(arr);
		int q1, q2, q3;
		q2 = (int)getMedian(0, arr.length - 1, arr);
		int[] q2IdxArr = getMedianIdx(0, arr.length - 1);
		if(q2IdxArr.length == 1) {
			 q1 = (int)getMedian(0, q2IdxArr[0] - 1, arr);
			 q3 = (int)getMedian(q2IdxArr[0] + 1, arr.length - 1, arr);
		} else {
			q1 = (int)getMedian(0, q2IdxArr[0], arr);
			q3 = (int)getMedian(q2IdxArr[1], arr.length - 1, arr);
		}
		
		System.out.println(q1);
		System.out.println(q2);
		System.out.println(q3);
		
	}
	
	
	public static double getMedian(int from, int to, int[] arr) {
		if((to - from + 1 & 1 )== 1) {
			return arr[(from + to) >> 1];
		} else {
			return (arr[(from + to) >> 1] + arr[(from + to + 1) >> 1]) >> 1; 
		}
	}
	
	public static int[] getMedianIdx(int from, int to) {
		if((to - from + 1 & 1 )== 1) {
			return new int[]{(from + to) >> 1};
		} else {
			return new int[]{
				(from + to) >> 1
				, (from + to + 1) >> 1
			};
		}
	}

}
