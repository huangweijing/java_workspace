import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class QHEAP1 {

	public static Scanner scanner = new Scanner(System.in);
	
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	int cmdCnt = scanner.nextInt();
    	for(int i=0; i<cmdCnt; i++) {
    		int cmd = scanner.nextInt();
    		switch(cmd) {
    		case 1:
    			pq.add(scanner.nextInt());
    			break;
    		case 2:
    			pq.remove(scanner.nextInt());
    			break;
    		case 3:
    			System.out.println(pq.peek());
    			break;
    		}
    	}
    }
}