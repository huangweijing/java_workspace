package com.weijinglab.checklinkedlist;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 
 * @author HuangWeijing
 * 2018/04/17
 */
public class CheckIfLinkedListIsSorted {

	public static void main(String[] args) {
		
		LinkedList<Integer> linkedList1 = new LinkedList<Integer>();
		Collections.addAll(linkedList1, new Integer[]{
				8, 7, 5, 2, 1
			});
		System.out.println("Is linked list 1 sorted in descending order? " + isSorted(linkedList1));
		
		LinkedList<Integer> linkedList2 = new LinkedList<Integer>();
		Collections.addAll(linkedList2, new Integer[]{
			24, 12, 9, 11, 8, 2	
		});
		System.out.println("Is linked list 2 sorted in descending order? " + isSorted(linkedList2));

	}

	public static boolean isSorted(LinkedList<Integer> list) {
		if(list.size() < 2) {
			return true;
		}
		Integer head = list.remove(0);
		if(head > list.peek() && isSorted(list)) {
			return true;
		} else {
			return false;
		}
	}
}
