import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class MaximizingTheProfit {

    /*
     * Complete the maximumProfit function below.
     */
    static long maximumProfit(int[] p) {
        /*
         * Write your code here.
         */ 
    	PriorityQueue<Long> leftMin = new PriorityQueue<Long>();
    	PriorityQueue<Long> rightMin = new PriorityQueue<Long>();
    	PriorityQueue<Long> leftMax = new PriorityQueue<Long>(
    			300000, new Comparator<Long>() {

					@Override
					public int compare(Long o1, Long o2) {
						if(o2 > o1)
							return 1;
						else if(o2 == o1)
							return 0;
						else
							return -1;
					}
		});
    	PriorityQueue<Long> rightMax = new PriorityQueue<Long>(
    			300000, new Comparator<Long>() {

					@Override
					public int compare(Long o1, Long o2) {
						if(o2 > o1)
							return 1;
						else if(o2 == o1)
							return 0;
						else
							return -1;
					}
		});
    	for(int i=1; i<p.length; i++) {
    		rightMax.add((long)p[i]);
    		rightMin.add((long)p[i]);
    	}
    	
    	long total_max = Long.MIN_VALUE;
    	for(int i=1; i<p.length - 1; i++) {
    		
    		leftMin.add((long)p[i - 1]);
    		leftMax.add((long)p[i - 1]);
    		rightMax.remove((long)p[i]);
    		rightMin.remove((long)p[i]);

    		long max_num_left = Long.MIN_VALUE;
        	long min_num_left = Long.MAX_VALUE;
        	long max_num_right = Long.MIN_VALUE;
        	long min_num_right = Long.MAX_VALUE;
        	
        	if(!leftMin.isEmpty())
        		min_num_left = leftMin.peek();
        	if(!rightMin.isEmpty())
        		min_num_right = rightMin.peek();
        	if(!rightMax.isEmpty())
        		max_num_right = rightMax.peek();
        	if(!leftMax.isEmpty())
        		max_num_left = leftMax.peek();
        	
//        	for(int j=0; j<i; j++) {
//        		int num = p[j];
//        		if(num >= p[i])
//        			continue;
//        		if(min_num_left > num)
//        			min_num_left = num;
//        		if(max_num_left < num)
//        			max_num_left = num;
//        	}
//        	for(int j=i+1; j<p.length; j++) {
//        		int num = p[j];
//        		if(num <= p[i])
//        			continue;
//        		if(min_num_right > num)
//        			min_num_right = num;
//        		if(max_num_right < num)
//        			max_num_right = num;
//        	}
        	
        	
        	long n1 = min_num_left * min_num_right * p[i];
        	long n2 = min_num_left * max_num_right * p[i];
        	long n3 = max_num_left * min_num_right * p[i];
        	long n4 = max_num_left * max_num_right * p[i];

        	if(min_num_left == Long.MAX_VALUE) {
        		n1 = Long.MIN_VALUE;
        		n2 = Long.MIN_VALUE;
        	}
        	if(min_num_right == Long.MAX_VALUE) {
        		n1 = Long.MIN_VALUE;
        		n3 = Long.MIN_VALUE;
        	}
        	if(max_num_left == Long.MIN_VALUE) {
        		n3 = Long.MIN_VALUE;
        		n4 = Long.MIN_VALUE;
        	}
        	if(max_num_right == Long.MIN_VALUE) {
        		n2 = Long.MIN_VALUE;
        		n4 = Long.MIN_VALUE;
        	}
        	
        	long biggerOne = Math.max(
        			Math.max(n1, n2),
        			Math.max(n3, n4));
//        	System.out.println("n1:" + n1);
//        	System.out.println("n2:" + n2);
//        	System.out.println("n3:" + n3);
//        	System.out.println("n4:" + n4);
        	if(biggerOne > total_max)
        		total_max = biggerOne;
    	}
    	
    	if(total_max == Long.MIN_VALUE)
    		total_max = -1;
    	
    	return total_max;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[] p = new int[n];

        String[] pItems = scanner.nextLine().split(" ");

        for (int pItr = 0; pItr < n; pItr++) {
            int pItem = Integer.parseInt(pItems[pItr].trim());
            p[pItr] = pItem;
        }

        long result = maximumProfit(p);
        System.out.println(result);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
