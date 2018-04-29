import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class ArrayManipulation {

    private static class Range {
        public int start;
        public int end;
        public long value;
        public Range next;
        public Range(int start, int end, long value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
        public String toString() {
        	return String.format("[%s,%s]=%s", start, end, value);
        }
    }
    /*
     * Complete the arrayManipulation function below.
     */
    static long arrayManipulation(int n, int[][] queries) {
        /*
         * Write your code here.
         */
        Range head = new Range(1, n, 0);
        Range current = head;
        
        for(int[] query : queries) {
            int a = query[0];
            int b = query[1];
            int k = query[2];
            
            
            Range newRange = new Range(a, b, k);
            current = head;
            
            while(current != null) {
            	
            	if(current.start > newRange.end
            			|| current.end < newRange.start) {
            	}
            	else if(current.start == newRange.start
            			&& current.end <= newRange.end) {
            		current.value += newRange.value;
            		newRange.start = current.end + 1;
            	}
            	else {
            		int old_current_end = current.end;
            		long old_current_value = current.value;
            		
            		int biggerStart = Math.max(current.start, newRange.start);
            		int smallerStart = Math.min(current.start, newRange.start);
            		int biggerEnd = Math.max(current.end, newRange.end);
            		int smallerEnd = Math.min(current.end, newRange.end);
            		Range middle = null, right = null;
            		if(biggerStart == smallerStart) {
            			current.end = smallerEnd;
            			current.value += newRange.value;
            			middle = current;
            		} else {
            			middle = new Range(biggerStart, smallerEnd, 
                				current.value + newRange.value);
                		middle.next = current.next;
                		current.end = biggerStart - 1;
                		current.next = middle;	
            		}
            		
            		if(newRange.end < old_current_end) {
            			 right = new Range(smallerEnd + 1, old_current_end, old_current_value);
            			 right.next = middle.next;
            			 middle.next = right;
            			 
            			 break;
            		} else {
                		newRange.start = middle.end + 1;
            		}
            	}
            	
            	current = current.next;
            	
            }
//    		Range myCurrent = head;
//    		while(myCurrent != null) {
//        		System.out.print(myCurrent);
//    			myCurrent = myCurrent.next;
//    		}
//    		System.out.println();

            
        }
        
        long max = 0;
        current = head;
        while(current != null) {
        	max = max > current.value ? max : current.value;
        	current = current.next;
        };

        return max;
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0].trim());

        int m = Integer.parseInt(nm[1].trim());

        int[][] queries = new int[m][3];

        System.out.println("loading data...");
        
        for (int queriesRowItr = 0; queriesRowItr < m; queriesRowItr++) {
//            String[] queriesRowItems = scanner.nextLine().split(" ");

//        	System.out.println(queriesRowItr);
            for (int queriesColumnItr = 0; queriesColumnItr < 3; queriesColumnItr++) {
                int queriesItem =  scanner.nextInt(); //Integer.parseInt(queriesRowItems[queriesColumnItr].trim());
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }
        System.out.println("loading completed...");

        long result = arrayManipulation(n, queries);
//        System.out.println(result);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}