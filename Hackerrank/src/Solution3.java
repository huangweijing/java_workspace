import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution3 {

	private static class KB {
		public Double k;
		public Double b;
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
    	Map<Double, Map<Double, KB>> lineMap = new HashMap<Double, Map<Double, KB>>();
    	
    	for(int i=0; i<n; i++) {
            String line = scanner.nextLine();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");
            String[] strArr = line.split(" ");
            
    		if(strArr[0].equals("+")){
    			KB plusKb = new KB();
    			plusKb.k = Double.parseDouble(strArr[1]);
    			plusKb.b = Double.parseDouble(strArr[2]);
    			Map<Double, KB> bMap = lineMap.get(plusKb.k);
    			if(bMap == null) {
    				bMap = new HashMap<Double, KB>();
    				lineMap.put(plusKb.k, bMap);
    			}
    			KB kbInMap = bMap.get(plusKb.k);
    			if(kbInMap == null) {
    				plusKb.count = 1;
    				bMap.put(plusKb.k, plusKb);
    			} else {
    				kbInMap.count++;
    			}
    			
    		} else if(strArr[0].equals("-")) {
    			KB minusKb = new KB();
    			minusKb.k = Double.parseDouble(strArr[1]);
    			minusKb.b = Double.parseDouble(strArr[2]);
    			Map<Double, KB> bMap = lineMap.get(minusKb.k);
    			if(bMap != null) {
        			KB kbInMap = bMap.get(minusKb.k);
        			if(kbInMap != null) {
        				kbInMap.count--;
        			}
    			}
    		} else if(strArr[0].equals("?")) {
    			Double q = Double.parseDouble(strArr[1]);
    			long result = 0;
    			Collection<Map<Double,KB>> values = lineMap.values();
    			for(Map<Double,KB> value : values) {
    				Collection<KB> kbList = value.values();
    				for(KB kb : kbList) {
        				if((q - kb.b) % kb.k == 0) {
        					result = result + kb.count;
        				}
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
