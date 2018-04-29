import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class GameofTwoStacks {

    /*
     * Complete the twoStacks function below.
     */
    static int twoStacks(int x, int[] a, int[] b) {
        /*
         * Write your code here.
         */
    	Stack<Integer> stackA = new Stack<>();
    	Stack<Integer> stackB = new Stack<>();
    	for(int i = a.length - 1; i >= 0; i--) {
    		stackA.push(a[i]);
    	}
    	for(int i = b.length - 1; i >= 0; i--) {
    		stackB.push(b[i]);
    	}

    	while(!stackA.isEmpty() || !stackB.isEmpty()) {
    		int pA = 0, pB = 0;
    		if(!stackA.isEmpty())
    			pA = stackA.peek();
    		if(!stackB.isEmpty())
    			pB = stackB.peek();
    		if(pA > pB) {
    			x -= stackA.pop();
    		} else {
    			x -= stackB.pop();
    		}
    	}
    	return 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(scanner.nextLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            String[] nmx = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmx[0].trim());

            int m = Integer.parseInt(nmx[1].trim());

            int x = Integer.parseInt(nmx[2].trim());

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");

            for (int aItr = 0; aItr < n; aItr++) {
                int aItem = Integer.parseInt(aItems[aItr].trim());
                a[aItr] = aItem;
            }

            int[] b = new int[m];

            String[] bItems = scanner.nextLine().split(" ");

            for (int bItr = 0; bItr < m; bItr++) {
                int bItem = Integer.parseInt(bItems[bItr].trim());
                b[bItr] = bItem;
            }

            int result = twoStacks(x, a, b);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
