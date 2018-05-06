import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class GameofTwoStacks2 {

    /*
     * Complete the twoStacks function below.
     */
    static int twoStacks(int x, int[] a, int[] b) {
        /*
         * Write your code here.
         */
//    	Stack<Integer> stackA = new Stack<>();
//    	Stack<Integer> stackB = new Stack<>();
//    	for(int i = a.length - 1; i >= 0; i--) {
//    		stackA.push(a[i]);
//    	}
//    	for(int i = b.length - 1; i >= 0; i--) {
//    		stackB.push(b[i]);
//    	}

//    	int aIdx = a.length - 1;
//    	int bIdx = b.length - 1;
    	int aIdx = 0;
    	int bIdx = 0;
    	long currentSum = 0;
    	int count = 0;
    	while(true) {
    		if((a[aIdx] > b[bIdx] || aIdx == a.length) && bIdx < b.length) {
    			currentSum += b[bIdx];
    			bIdx++;
    			count++;
    			if(currentSum > x)
    				return count - 1;
    			continue;
    		}else if((b[bIdx] >= a[aIdx] || bIdx == b.length)&& aIdx < a.length){
    			currentSum += a[aIdx];
    			aIdx++;
    			count++;
    			if(currentSum > x)
    				return count - 1;
    			continue;
    		}
    		return count;
    	}
    }

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

//        scanner = new Scanner(new File("gameof2stacks_input01.txt"));
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
            System.out.println(result);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
