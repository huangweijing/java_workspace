package com.weijinglab.bracketscheck;

import java.util.Stack;

public class BracketsCheck {

	public static void main(String[] args) {

	}
	

	public static void checkBrackets(String someStr) {
		Stack<Character> chechStack = new Stack<Character>();
		for(Integer i=0; i<someStr.length(); i++) {
			switch(someStr.charAt(i)){
			case '}':
			case ']':
			case ')':
				break;
			case '{':
			case '[':
			case '(':
				chechStack.push(someStr.charAt(i));
				break;
			}
			
		}

	}

}
