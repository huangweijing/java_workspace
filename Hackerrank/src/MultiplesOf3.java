import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MultiplesOf3 {

    public static void main(String[] args) {
    	int max = 1000000001;
    	
//    	Map<Integer, Long> solutionMap = new HashMap<Integer, Long>();
//    	solutionMap.put(0, 0l);
////    	System.out.println(solutionMap.get(0));
//    	for(int i=4; i<=max - 1; i++) {
//    		if((i - 1) % 3 == 0 || (i - 1) % 5 == 0) {
//    			int t = i - 1;
//    			while(!solutionMap.containsKey(t))
//    				t--;
//    			solutionMap.put(i, solutionMap.get(t) + i - 1);
//    		}
//    	}
//    	System.out.println("input");
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            
            int i = n - 1;
            long num3 = i - i % 3;
            long sum3 = (num3 / 3) * ( 3 + num3) / 2;
            
            long num5 = i - i % 5;
            long sum5 = (num5 / 5) * ( 5 + num5) / 2; 
            
            long num15 = i - i % 15;
            long sum15 = (num15 / 15) * ( 15 + num15) / 2;
            
            if(i < 3)
            	System.out.println(0);
            else if(i < 5)
            	System.out.println(sum3);
            else if(i < 15)
            	System.out.println(sum3 + sum5);
            else
            	System.out.println(sum3 + sum5 - sum15);
            
//			int j = n;
//			while(!solutionMap.containsKey(j))j--;
//            System.out.println(solutionMap.get(j));
        }
        in.close();
    }
}
