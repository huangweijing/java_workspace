import java.util.Scanner;

public class Solution8 {
	
    /*
     * Complete the dynamicLineIntersection function below.
     */
    static void dynamicLineIntersection(int n) {
        /*
         * Write your code here.
         */
        int[] qDatabase = new int[100001];
        int maximum = 100000;
        StringBuffer result = new StringBuffer();
        
    	for(int i=0; i<n; i++) {
            String oprand = scanner.next();
            
    		if(oprand.equals("+")){
    			int k = scanner.nextInt();
    			int b = scanner.nextInt();
    			for(int q=b; q>=0; q=q-k) {
    				qDatabase[q]++;
    			}
    			for(int q=b+k; q<=maximum; q=q+k) {
    				qDatabase[q]++;
    			}
    			
    		} else if(oprand.equals("-")) {
    			int k = scanner.nextInt();
    			int b = scanner.nextInt();
    			for(int q=b; q>=0; q=q-k) {
    				qDatabase[q]--;
    			}
    			for(int q=b+k; q<=maximum; q=q+k) {
    				qDatabase[q]--;
    			}
    			
    		} else if(oprand.equals("?")) {
    			Integer q = scanner.nextInt();
    			result.append(qDatabase[q]);
    			result.append("\n");
//    			System.out.println(qDatabase[q]);
    		}
    	}
    	System.out.println(result);

    }

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        dynamicLineIntersection(n);

        scanner.close();
    }
}
