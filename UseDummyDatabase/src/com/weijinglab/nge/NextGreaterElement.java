package com.weijinglab.nge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class NextGreaterElement {

    public static void main(String args[])
    {
    	int t=100;
    	while(--t > 0)
	    	for(int i=100; i<=102400000; i=i*2) {
	    		measure(i);	
	    	}
    	
//        measure(10000);
//        measure(20000);
//        measure(40000);
//        measure(80000);
//        measure(160000);
//        measure(320000);
    }
    
    public static void measure(int n) {
//    	System.out.println(String.format("%s : %s", "input size", n));
    	Random rand = new Random(System.nanoTime());
    	List<Integer> randList = new ArrayList<Integer>();
    	for(int i=0; i<n; i++) {
    		randList.add(rand.nextInt(100000000));
    	}
    	Integer[] arr = randList.toArray(new Integer[0]);
    	long start = System.currentTimeMillis();
        printNGE1(arr, arr.length);
    	long end =  System.currentTimeMillis();
    	long time1 = end - start;
//    	System.out.println(String.format("%s : %s", "method1", (end - start)));
    	
    	start = System.currentTimeMillis();
        printNGE2(arr, arr.length);
        end =  System.currentTimeMillis();
        long time2 = end - start;
//        System.out.println(String.format("%s : %s", "method2", (end - start)));
        System.out.println(String.format("%s\t%s\t%s", n, time1, time2));
    }
	
    static void printNGE1(Integer arr[], int n)
    {
        int next, i, j;
        for (i=0; i<n; i++)
        {
            next = -1;
            for (j = i+1; j<n; j++)
            {
                if (arr[i] < arr[j])
                {
                    next = arr[j];
                    break;
                }
            }
//            System.out.println(arr[i]+" -- "+next);
        }
    }
    
    /* prints element and NGE pair for 
    all elements of arr[] of size n */
 static void printNGE2(Integer arr[], int n) 
 {
     int i = 0;
     Stack<Integer> s = new Stack<>();
//     s.top = -1;
     int element, next;

     /* push the first element to stack */
     s.push(arr[0]);

     // iterate for rest of the elements
     for (i = 1; i < n; i++) 
     {
         next = arr[i];

         if (s.isEmpty() == false) 
         {
              
             // if stack is not empty, then 
             // pop an element from stack
             element = s.pop();

             /* If the popped element is smaller than 
                next, then a) print the pair b) keep 
                popping while elements are smaller and 
                stack is not empty */
             while (element < next) 
             {
//                 System.out.println(element + " --> " + next);
                 if (s.isEmpty() == true)
                     break;
                 element = s.pop();
             }

             /* If element is greater than next, then 
                push the element back */
             if (element > next)
                 s.push(element);
         }

         /* push next to stack so that we can find next
            greater for it */
         s.push(next);
     }

     /* After iterating over the loop, the remaining 
        elements in stack do not have the next greater 
        element, so print -1 for them */
     while (s.isEmpty() == false) 
     {
         element = s.pop();
         next = -1;
//         System.out.println(element + " -- " + next);
     }
 }
}
