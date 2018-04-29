package com.weijinglab.moserseq;

import java.util.ArrayList;
import java.util.List;

public class MoserdeBruijnSequence {

	public static void main(String[] args) {
		System.out.println(getMoserdeBruijnSequence(10000));
	}
	
	public static List<Integer> getMoserdeBruijnSequence(Integer n) {
		List<Integer> resultList = new ArrayList<>();
		if(n == 1) {
			resultList.add(0);
			return resultList;
		}
		Integer x = (int) (Math.log(n - 1) / Math.log(2));
		Integer two_exp_x = (int)Math.pow(2, x);
		Integer four_exp_x = (int)Math.pow(4, x);
		List<Integer> subList = getMoserdeBruijnSequence(two_exp_x);
		resultList.addAll(subList);
		for(Integer i=1; i<=n-two_exp_x; i++) {
			resultList.add(subList.get(i-1) + four_exp_x); 
		}
		return resultList;
	}

}
