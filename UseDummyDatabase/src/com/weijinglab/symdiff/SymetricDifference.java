package com.weijinglab.symdiff;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SymetricDifference {

	public static void main(String[] args) {

		System.out.println(sym(new String[]{
			"123", "5214"
		}));
		
		System.out.println(sym(new String[]{
			"125", "235", "345"
		}));
		
		System.out.println(sym(new String[]{
			"1125", "2235", "3455"
		}));
		
		System.out.println(sym(new String[]{
			"33325", "2175", "3466", "123"
		}));
		
		System.out.println(sym(new String[]{
			"33325", "2157", "3466", "123", "5398", "1"
		}));
		
	}
	
	public static String sym(String[] setStrArr) {
		String resultStr = "";
		Set<Character> resultSet = new HashSet<Character>(); 
		List<Character> sortedResultList = new LinkedList<Character>(); 
		for(String setStr : setStrArr) {
			Set<Character> currentSet = new HashSet<Character>();
			Set<Character> unionSet = new HashSet<Character>();
			Set<Character> intersectionSet = new HashSet<Character>();
			for(int i=0; i<setStr.length(); i++) {
				currentSet.add(new Character(setStr.charAt(i)));
			}
			unionSet.addAll(currentSet);
			intersectionSet.addAll(currentSet);

			unionSet.addAll(resultSet);
			intersectionSet.retainAll(resultSet);
			
			unionSet.removeAll(intersectionSet);
			resultSet = unionSet;
		}
		sortedResultList.addAll(resultSet);
		Collections.sort(sortedResultList);
		for(Character ch : sortedResultList) {
			resultStr += ch;
		}
		return resultStr;
	}

}
