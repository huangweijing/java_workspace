package com.weijinglab.pairwise;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Pairwise {

	public static void main(String[] args) {
		
		System.out.println(pairwise(new Integer[]{7, 9, 11, 13, 15, 5}, 20));
		System.out.println(pairwise(new Integer[]{1, 4, 2, 3, 0, 5}, 7));
		System.out.println(pairwise(new Integer[]{1, 1, 1}, 2));
		System.out.println(pairwise(new Integer[]{0, 0, 0, 0, 1, 1}, 1));
		System.out.println(pairwise(new Integer[]{}, 100));

	}
	
	public static Integer pairwise(Integer[] intArr, Integer sum) {
		Integer sumOfIndices = 0;
		List<Integer> usedIndices = new LinkedList<Integer>();
		
		for(int i=0; i<intArr.length; i++) {
			if(usedIndices.contains(i)) {
				continue;
			}
			
			for(int j=i+1; j<intArr.length; j++) {
				if(usedIndices.contains(j)) {
					continue;
				}
				
				if(intArr[i] + intArr[j] == sum) {
					sumOfIndices += i + j;
					usedIndices.add(i);
					usedIndices.add(j);
					break;
				}
			}
		}
		
		return sumOfIndices;
	}
	
	public void print(HashMap<Integer,Integer> randomMap, int randomInt) {
		int key = 0;
		System.out.print("([");
		Iterator it =randomMap.keySet().iterator(); 
		while(it.hasNext()) {
			if(key!=0) {
				System.out.print(",");
			}
			System.out.print(randomMap.get(it.next()));
			key++;
		}
		System.out.print(")]," + randomInt+"\n");
	}


}
