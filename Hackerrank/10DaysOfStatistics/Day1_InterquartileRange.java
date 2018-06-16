import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Day1_InterquartileRange {
	
    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int[] x = new int[n];
		int[] f = new int[n];
		for(int i=0; i<x.length; i++) {
			x[i] = scanner.nextInt();
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<f.length; i++) {
			f[i] = scanner.nextInt();
			for(int j=0; j<f[i]; j++) {
				list.add(x[i]);
			}
		}
		Collections.sort(list);
		Integer[] arr = list.toArray(new Integer[0]);
		double q1, q3;
//		q2 = getMedian(0, arr.length - 1, arr);
		int[] q2IdxArr = getMedianIdx(0, arr.length - 1);
		if(q2IdxArr.length == 1) {
			 q1 = getMedian(0, q2IdxArr[0] - 1, arr);
			 q3 = getMedian(q2IdxArr[0] + 1, arr.length - 1, arr);
		} else {
			q1 = getMedian(0, q2IdxArr[0], arr);
			q3 = getMedian(q2IdxArr[1], arr.length - 1, arr);
		}
		System.out.println(BigDecimal.valueOf(q3 - q1).setScale(1, BigDecimal.ROUND_HALF_UP));
	}
	
	public static double getMedian(int from, int to, Integer[] arr) {
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
