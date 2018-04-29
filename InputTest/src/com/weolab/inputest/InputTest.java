package com.weolab.inputest;

import java.util.Scanner;

public class InputTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input some number: ");
		while(scanner.hasNext()) {
			String someStr = scanner.next();
			try {
				Integer someInt = Integer.parseInt(someStr);
				if(someInt == 99) {
					break;
				} else {
					System.out.println("The Integer you input is " + someInt);
				}
			}catch(Exception e) {
				System.out.println("Wrong input! " + someStr + " is not a number!");
			}
			System.out.print("Input some number: ");
		}

	}

}


