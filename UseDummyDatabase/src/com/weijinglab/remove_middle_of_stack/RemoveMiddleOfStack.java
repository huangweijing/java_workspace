package com.weijinglab.remove_middle_of_stack;

import java.util.Collections;
import java.util.Stack;

public class RemoveMiddleOfStack {

	public static void main(String[] args) {
		
		Stack<Integer> stack = new Stack<Integer>();
		Collections.addAll(stack, new Integer[]{
			1, 2, 3, 4, 5	
		});
		System.out.println(stack);
		removeMiddleOfStack(stack, stack.size());
		System.out.println(stack);
		
		stack.clear();
		Collections.addAll(stack, new Integer[]{
				1, 2, 3, 4, 5, 6	
			});
		System.out.println(stack);
		removeMiddleOfStack(stack, stack.size());
		System.out.println(stack);

	}
	
	public static void removeMiddleOfStack(Stack<Integer> stack, Integer staLen) {
		Integer n = stack.pop();
		if(stack.size() == (staLen - 1)/ 2) {
			return;
		}
		removeMiddleOfStack(stack, staLen);
		stack.push(n);
	}

}
