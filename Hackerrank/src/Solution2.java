import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution2 {

	private static class KB {
		public int k;
		public int b;
		public int count;
		public int getKey() {
			return k * 100000 + b;
		}
		public int hashCode() {
			return k * 100000 + b;
		}
	}
	
    /*
     * Complete the dynamicLineIntersection function below.
     */
    static void dynamicLineIntersection(int n) {
        /*
         * Write your code here.
         */
    	Map<Integer, KB> lineMap = new HashMap<Integer, KB>();
    	
    	for(int i=0; i<n; i++) {
            String line = scanner.nextLine();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");
            String[] strArr = line.split(" ");
            
    		if(strArr[0].equals("+")){
    			KB plusKb = new KB();
    			plusKb.k = Integer.parseInt(strArr[1]);
    			plusKb.b = Integer.parseInt(strArr[2]);
    			KB kbInMap = lineMap.get(plusKb.getKey());
    			if(kbInMap == null) {
    				plusKb.count = 1;
    				lineMap.put(plusKb.getKey(), plusKb);
    			} else {
    				kbInMap.count++;
    			}
    			
    		} else if(strArr[0].equals("-")) {
    			KB minusKb = new KB();
    			minusKb.k = Integer.parseInt(strArr[1]);
    			minusKb.b = Integer.parseInt(strArr[2]);
    			KB kbInMap = lineMap.get(minusKb.getKey());
    			if(kbInMap == null) {
    			} else if(kbInMap.count == 1) {
    				lineMap.remove(kbInMap.toString());
    			} else {
    				kbInMap.count--;
    			}
    		} else if(strArr[0].equals("?")) {
    			Integer q = Integer.parseInt(strArr[1]);
    			Integer result = 0;
    			
    			for(KB kb : lineMap.values()) {
    				
//    				if((q - kb.b) % kb.k == 0) {
//    					result = result + kb.count;
//    				}
    			}
//    			for(Integer key : lineMap.keySet()) {
//    				KB kb = lineMap.get(key);
//    				if((q - kb.b) % kb.k == 0) {
//    					result = result + kb.count;
//    				}
//    			}
    			System.out.println(result);
    		}
    	}

    }
	

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        dynamicLineIntersection(n);

        scanner.close();
    }
}
