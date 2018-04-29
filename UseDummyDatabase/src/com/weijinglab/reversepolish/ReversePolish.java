package com.weijinglab.reversepolish;

import java.util.Stack;

public class ReversePolish {

	public static void main(String[] args) {
		
		System.out.println(calcReversePolish(new String[]{
				"10", "2" , "4", "7", "+", "*", "3", "/", "-"
		}));

	}
	
	public static Integer calcReversePolish(String[] reversePolish) {
//		Integer result = 0;
		Integer num1, num2, pushEle;
		Stack<String> calcStack = new Stack<String>();
		for(String element:reversePolish) {
			switch(element){
			case "*":
				num1 = Integer.parseInt(calcStack.pop());
				num2 = Integer.parseInt(calcStack.pop());
				pushEle = num2 * num1;
				calcStack.push(pushEle.toString());
				break;
			case "/":
				num1 = Integer.parseInt(calcStack.pop());
				num2 = Integer.parseInt(calcStack.pop());
				pushEle = num2 / num1;
				calcStack.push(pushEle.toString());
				break;
			case "+":
				num1 = Integer.parseInt(calcStack.pop());
				num2 = Integer.parseInt(calcStack.pop());
				pushEle = num2 + num1;
				calcStack.push(pushEle.toString());
				break;
			case "-":
				num1 = Integer.parseInt(calcStack.pop());
				num2 = Integer.parseInt(calcStack.pop());
				pushEle = num2 - num1;
				calcStack.push(pushEle.toString());
				break;
			default:
				calcStack.push(element);
				break;
			}
			System.out.println(calcStack);
		}
		
		return Integer.parseInt(calcStack.peek());
	}

}
