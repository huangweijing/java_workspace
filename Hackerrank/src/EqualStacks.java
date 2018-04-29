import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class EqualStacks {

    static class StackNode{
        int value;
        int currentSum;
        public StackNode(int v, int s) {
            value = v;
            currentSum = s;
        }
    }
    /*
     * Complete the equalStacks function below.
     */
    static int equalStacks(int[] h1, int[] h2, int[] h3) {
        /*
         * Write your code here.
         */
    	Stack<StackNode>s1 = new Stack<>();
    	Stack<StackNode>s2 = new Stack<>();
    	Stack<StackNode>s3 = new Stack<>();
    	int curSum1=0, curSum2=0, curSum3=0;
        for(int i=h1.length - 1; i>=0; i--) {
        	curSum1 += h1[i];
        	s1.push(new StackNode(h1[i], curSum1));
        }
        for(int i=h2.length - 1; i>=0; i--) {
        	curSum2 += h2[i];
        	s2.push(new StackNode(h2[i], curSum2));
        }
        for(int i=h3.length - 1; i>=0; i--) {
        	curSum3 += h3[i];
        	s3.push(new StackNode(h3[i], curSum3));
        }
        
        while(!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty()) {
        	StackNode p1 = s1.peek();
        	StackNode p2 = s2.peek();
        	StackNode p3 = s3.peek();
        	if(p1.currentSum == p2.currentSum
        			&& p1.currentSum == p3.currentSum) {
        		return p1.currentSum;
        	} else {
        		if(p1.currentSum >= p2.currentSum
        				&& p1.currentSum >= p3.currentSum) {
        			s1.pop();
        		}
        		else if(p2.currentSum >= p1.currentSum
        				&& p2.currentSum >= p3.currentSum) {
        			s2.pop();
        		}
        		else if(p3.currentSum >= p2.currentSum
        				&& p3.currentSum >= p1.currentSum) {
        			s3.pop();
        		}
        	}
        }
        return 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] n1N2N3 = scanner.nextLine().split(" ");

        int n1 = Integer.parseInt(n1N2N3[0].trim());

        int n2 = Integer.parseInt(n1N2N3[1].trim());

        int n3 = Integer.parseInt(n1N2N3[2].trim());

        int[] h1 = new int[n1];

        String[] h1Items = scanner.nextLine().split(" ");

        for (int h1Itr = 0; h1Itr < n1; h1Itr++) {
            int h1Item = Integer.parseInt(h1Items[h1Itr].trim());
            h1[h1Itr] = h1Item;
        }

        int[] h2 = new int[n2];

        String[] h2Items = scanner.nextLine().split(" ");

        for (int h2Itr = 0; h2Itr < n2; h2Itr++) {
            int h2Item = Integer.parseInt(h2Items[h2Itr].trim());
            h2[h2Itr] = h2Item;
        }

        int[] h3 = new int[n3];

        String[] h3Items = scanner.nextLine().split(" ");

        for (int h3Itr = 0; h3Itr < n3; h3Itr++) {
            int h3Item = Integer.parseInt(h3Items[h3Itr].trim());
            h3[h3Itr] = h3Item;
        }

        int result = equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
