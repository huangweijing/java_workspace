import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution7 {
	
    /*
     * Complete the dynamicLineIntersection function below.
     */
    static void dynamicLineIntersection(int n) {
        /*
         * Write your code here.
         */
    	Map<Integer, Map<Integer, Integer>> lineMap = new TreeMap<Integer, Map<Integer, Integer>>();
    	
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
    			Integer k = Integer.parseInt(strArr[1]);
    			Integer b = Integer.parseInt(strArr[2]) % k;
    			Map<Integer, Integer> bMap = lineMap.get(k);
    			if(bMap == null) {
    				bMap = new TreeMap<Integer, Integer>();
    				lineMap.put(k, bMap);
    			}
    			Integer count = bMap.get(b);
    			if(count == null) {
    				bMap.put(b, 1);
    			} else {
    				bMap.put(b, count + 1);
    			}
    			
    		} else if(strArr[0].equals("-")) {
    			Integer k = Integer.parseInt(strArr[1]);
    			Integer b = Integer.parseInt(strArr[2]) % k;
    			Map<Integer, Integer> bMap = lineMap.get(k);
    			if(bMap != null) {
        			Integer count = bMap.get(b);
        			if(count != null) {
        				bMap.put(b, count - 1);
        			}
    			}
    		} else if(strArr[0].equals("?")) {
    			Integer q = Integer.parseInt(strArr[1]);
    			long result = 0;
    			for(Integer k : lineMap.keySet()) {
    				Integer integer = lineMap.get(k).get(q % k);
    				if(integer != null) {
    					result += integer;
    				}
    			}
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
