import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class GameofTwoStacks {

    /*
     * Complete the twoStacks function below.
     * 思考方案
     * 1.初始状态，先将A的先头元素尽可能取多来消化x，若A取完了建立B
     * 2.随后将A的先头放回去一个，从B里取出能够填补空白的元素的数量
     * 3.反复进行2直到A消化完毕为止。从所有2中获取该数量的最大值
     */
    static int twoStacks(int x, int[] a, int[] b) {
        /*
         * Write your code here.
         */
    	Stack<Integer> stackTmpA = new Stack<>();
    	Stack<Integer> stackTmpB = new Stack<>();
    	Stack<Integer> stackB = new Stack<>();
    	
    	int idxB = 0;
    	int idxA = 0;
    	int count = 0;
    	int sum = 0;
    	for(idxA = 0; idxA < a.length && sum <= x; idxA++) {
    		stackTmpA.add(a[idxA]);
    		sum = sum + a[idxA];
    	}
    	if(sum > x) {
    		idxA--;
    		sum -= stackTmpA.pop();
    	}
    	
//    	System.out.println(stackTmpA);
    	
    	for(idxB = 0; idxB < b.length && sum <= x; idxB++) {
    		stackTmpB.add(b[idxB]);
    		sum = sum + b[idxB];
    	}
    	if(sum > x) {
    		idxB--;
    		sum -= stackTmpB.pop();
    	}
    	for(int i=b.length - 1; i >= idxB; i--) {
    		stackB.add(b[i]);
    	}
    	
    	
    	count = stackTmpB.size() + stackTmpA.size();
    	int maxCount = count;
    	int space = x - sum;
    	while(!stackTmpA.isEmpty()) {
    		int num = stackTmpA.pop();
    		sum = sum - num;
    		space = x - sum;
    		count--;
    		while(!stackB.isEmpty() && stackB.peek() <= space) {
    			space = space - stackB.peek();
    			sum = sum + stackB.peek();
    			stackB.pop();
    			count++;
    		}
			if(count > maxCount) {
				maxCount = count;
			}
    	}
    	
		while(!stackB.isEmpty() && stackB.peek() <= space) {
			space = space - stackB.peek();
			sum = sum + stackB.peek();
			stackB.pop();
			count++;
		}
		if(count > maxCount) {
			maxCount = count;
		}

    	return maxCount;
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
