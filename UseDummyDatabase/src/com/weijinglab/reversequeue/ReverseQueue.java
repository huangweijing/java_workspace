package com.weijinglab.reversequeue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ReverseQueue {

	public static void main(String[] args) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		Collections.addAll(q, new Integer[]{
				5, 24, 9, 6, 8, 4, 1, 8, 3, 6
		});
		System.out.println(reverse(q));
	}
	
	public static Queue<Integer> reverse(Queue<Integer> q) {
		if(q.size() == 1)
			return q;
		else {
			Integer first = q.poll();
			Queue<Integer> reversedQ = reverse(q);
			reversedQ.add(first);
			return reversedQ;
		}
	}

}
