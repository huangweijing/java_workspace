import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class JesseAndCookies {

    /*
     * Complete the cookies function below.
     */
    static int cookies(int k, int[] A) {
        /*
         * Write your code here.
         */
    	int result = 0;
    	PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    	for(int a : A) {
    		pq.add(a);
    	}
    	
    	while(pq.size() >= 2 && pq.peek() < k) {
    		int leastSweet = pq.poll();
    		int secleastSweet = pq.poll();
    		pq.add(leastSweet + 2 * secleastSweet);
    		result++;
    	}
    	
    	if(pq.peek() < k)
    		return -1;
    	
    	return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0].trim());

        int k = Integer.parseInt(nk[1].trim());

        int[] A = new int[n];

        String[] AItems = scanner.nextLine().split(" ");

        for (int AItr = 0; AItr < n; AItr++) {
            int AItem = Integer.parseInt(AItems[AItr].trim());
            A[AItr] = AItem;
        }

        int result = cookies(k, A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
