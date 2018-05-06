import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class DownToZeroII {

	private static Map<Integer, Integer> downToZeroResult = new HashMap<Integer, Integer>();
	private static Map<Integer, Double> sqrtN = new HashMap<Integer, Double>();
	/*
	 * Complete the downToZero function below.
	 */
	public static int downToZero(int n) {
		if(n<=3)
			return n;
		if(downToZeroResult.containsKey(n)) {
			System.out.println("hit:" + n);
			return downToZeroResult.get(n);
		}
		List<Integer> dividerList = getBiggerDividerList(n);
		System.out.println(dividerList);
		dividerList.add(n-1);
		int minDownToZeroCount = n;
		for(Integer divider : dividerList) {
			int distance = downToZero(divider);
			if(distance < minDownToZeroCount) {
				minDownToZeroCount = distance;
			}
		}
//		if(n == minDownToZeroCount) {
//			minDownToZeroCount = downToZero(n - 1);
//		}
		
		downToZeroResult.put(n, minDownToZeroCount + 1);
		return minDownToZeroCount + 1;
	}

	public static List<Integer> getBiggerDividerList(int n) {
		Double r = sqrtN.get(n);
		if(r == null) {
			r = Math.sqrt(n);
			sqrtN.put(n, r);
		}
		if((int)r.doubleValue() < r) 
			r = r + 1;
		int rootN = (int)r.doubleValue();
		if(rootN==1)
			rootN = 2;
		List<Integer> resultList = new ArrayList<Integer>();
		for(int i=rootN; i<=n-1; i++) {
			if(n%i==0)
				resultList.add(i);
		}
		return resultList;
	}

	private static final Scanner scanner = new Scanner(System.in);

//	public static void main(String[] args) throws IOException {
//		System.out.println(getMiddleDivider(15));
//	}
	
	public static void main(String[] args) {
		System.out.println(downToZero(30));
//		System.out.println(downToZero(30));
//    	for(int i=0; i<10000; i++) {
//    		System.out.println(downToZero(i));
//    		
//    	}
//    	System.out.println(downToZero((int)(Math.random() * 100000)));
//    		System.out.println(downToZero(i));
    	
	}
	
	
	public static void main2(String[] args) throws IOException {
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(scanner.nextLine().trim());

		for (int qItr = 0; qItr < q; qItr++) {
			int n = Integer.parseInt(scanner.nextLine().trim());

			int result = downToZero(n);
			System.out.println(result);
//			bufferedWriter.write(String.valueOf(result));
//			bufferedWriter.newLine();
		}

//		bufferedWriter.close();
	}
}
