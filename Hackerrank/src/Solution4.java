import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution4 {

	private static class KB {
		public Integer k;
		public Integer b;
		public int count;
		public String toString() {
			return k.toString() + "-" + b.toString();
		}
	}
	
    /*
     * Complete the dynamicLineIntersection function below.
     */
    static void dynamicLineIntersection(int n) {
        /*
         * Write your code here.
         */
    	Map<Integer, Map<Integer, KB>> lineMap = new HashMap<Integer, Map<Integer, KB>>();
    	
    	for(int i=0; i<n; i++) {
            String line = scanner.nextLine();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");
            String[] strArr = new String[3];
            strArr[0] = line.substring(0, 1);
            int secSpace = line.indexOf(" ", 2);
            if(secSpace > 0) 
            	strArr[1] = line.substring(2, secSpace);
            else
            	strArr[1] = line.substring(2);
            if(line.length() > secSpace + 1) {
            	strArr[2] = line.substring(secSpace + 1);
            }
            
    		if(strArr[0].equals("+")){
    			KB plusKb = new KB();
    			plusKb.k = Integer.parseInt(strArr[1]);
    			plusKb.b = Integer.parseInt(strArr[2]) % plusKb.k;
    			Map<Integer, KB> bMap = lineMap.get(plusKb.k);
    			if(bMap == null) {
    				bMap = new HashMap<Integer, KB>();
    				lineMap.put(plusKb.k, bMap);
    			}
    			KB kbInMap = bMap.get(plusKb.b);
    			if(kbInMap == null) {
    				plusKb.count = 1;
    				bMap.put(plusKb.b, plusKb);
    			} else {
    				kbInMap.count++;
    			}
    			
    		} else if(strArr[0].equals("-")) {
    			KB minusKb = new KB();
    			minusKb.k = Integer.parseInt(strArr[1]);
    			minusKb.b = Integer.parseInt(strArr[2]) % minusKb.k;
    			Map<Integer, KB> bMap = lineMap.get(minusKb.k);
    			if(bMap != null) {
        			KB kbInMap = bMap.get(minusKb.b);
        			if(kbInMap != null) {
        				kbInMap.count--;
        			}
    			}
    		} else if(strArr[0].equals("?")) {
    			Integer q = Integer.parseInt(strArr[1]);
    			long result = 0;
    			for(Integer k : lineMap.keySet()) {
    				KB kb = lineMap.get(k).get(q % k);
    				if(kb != null) {
    					result += kb.count;
    				}
    			}
    			System.out.println(result);
    		}
    	}

    }

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        dynamicLineIntersection(n);

        scanner.close();
    }
}
