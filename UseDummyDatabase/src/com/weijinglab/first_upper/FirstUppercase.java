package com.weijinglab.first_upper;

public class FirstUppercase {

	public static void main(String[] args) {
		System.out.println(firstUpperCase("aabsadsfadfdf"));
	}
	
	public static int firstUpperCase(String str) {
		if(str.length() == 0) {
			return -1;
		}
		
		char ch = str.charAt(0);
		if(ch >= 'A' && ch <= 'Z')
		{
			return 0;
		} else {
			int idx = firstUpperCase(str.substring(1));
			return idx == -1 ? -1 : 1 + idx ;
		}
		
	}

}
