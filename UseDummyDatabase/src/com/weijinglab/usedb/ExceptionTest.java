package com.weijinglab.usedb;

public class ExceptionTest  extends Exception {
	
	private static ExceptionTest myException = new ExceptionTest();

	public static void main(String[] args) throws ExceptionTest {
		throw myException;
	}
	
}
