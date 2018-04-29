import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution5 {

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
//    	Map<Integer, Map<Integer, KB>> lineMap = new HashMap<Integer, Map<Integer, KB>>();
    	KB[][] lineMap = new KB[100001][];
    	//Set<Integer> kSet = new HashSet<Integer>();
    	List<Integer> kList = new ArrayList<Integer>();
    	
    	for(int i=0; i<n; i++) {
            String line = scanner.nextLine();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");
            String[] strArr = line.split(" ");
            
    		if(strArr[0].equals("+")){
    			KB plusKb = new KB();
    			plusKb.k = Integer.parseInt(strArr[1]);
    			plusKb.b = Integer.parseInt(strArr[2]) % plusKb.k;
    			if(lineMap[plusKb.k] == null) {
    				lineMap[plusKb.k] = new KB[plusKb.k];
    				kList.add(plusKb.k);
    			}
    			KB kbInMap = lineMap[plusKb.k][plusKb.b];
    			if(kbInMap == null) {
    				plusKb.count = 1;
    				lineMap[plusKb.k][plusKb.b] = plusKb;
    			} else {
    				kbInMap.count++;
    			}
    			
    		} else if(strArr[0].equals("-")) {
    			KB minusKb = new KB();
    			minusKb.k = Integer.parseInt(strArr[1]);
    			minusKb.b = Integer.parseInt(strArr[2]) % minusKb.k;
    			KB[] bMap = lineMap[minusKb.k];
    			if(bMap != null) {
        			KB kbInMap = bMap[minusKb.b];
        			if(kbInMap != null) {
        				kbInMap.count--;
        			}
    			}
    		} else if(strArr[0].equals("?")) {
    			Integer q = Integer.parseInt(strArr[1]);
    			long result = 0;
    			
//    			for(Integer k: kList) {
//    				KB kb = lineMap[k][q % k];
//    				if(kb != null) {
//    					result += kb.count;
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
