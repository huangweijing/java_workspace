package com.weijinglab.dnapairing;

import java.util.HashMap;
import java.util.Map;

public class DNAPairing {
	
	public static void main(String args[]) {
		for(int i=0; i<20; i++) {
			String dnaStr = makeRandomDNAStr();
			System.out.println("DNA:" + dnaStr);
			System.out.println("DNA pair:" + pairElement(dnaStr));
			System.out.println("----------------------");
		}
	}
	
	/**
	 * make the pair to the dna string.
	 * @param element
	 * @return
	 */
	public static String pairElement(String element) {
		String returnStr = "";
		Map<String, String> dnaDic = new HashMap<String, String>();
		dnaDic.put("G", "C");
		dnaDic.put("C", "G");
		dnaDic.put("A", "T");
		dnaDic.put("T", "A");
		for(int i=0; i<element.length(); i++) {
			String eleChar = new Character(element.charAt(i)).toString();
			if(i != 0) {
				returnStr = returnStr + ", ";
			}
			returnStr = returnStr + eleChar + dnaDic.get(eleChar);
		}
		return returnStr;
	}
	
	/**
	 * make a random dna element string.
	 * @return
	 */
	public static String makeRandomDNAStr() {
		int len = (int)(Math.random() * 20 + 1);
		String returnStr = "";
		for(int i=0; i<len; i++) {
			 returnStr = returnStr + "CGTA".charAt((int)(Math.random() * 4));
		}
		return returnStr;
	}
}
