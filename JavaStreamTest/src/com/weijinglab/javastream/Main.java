package com.weijinglab.javastream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
	
	public static Stream<Integer> getDummyJsonEntity() {
		List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
		return integerList.stream();
	}
	
	public static Integer doSomeEdit(Integer t) {
		System.out.println(String.format("doSomeEdit: %s", t));
		return t * 2;
	} 
	
	public static Stream<Integer> editOriginalStream(Stream<Integer> s) {
		return s.map(new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer t) {
				return doSomeEdit(t);
			}
		});
	}
	
	public static void main(String[] args) {
		System.out.println("main start");
		Stream<Integer> s = getDummyJsonEntity();
		Stream<Integer> s2 = editOriginalStream(s);
		System.out.println("after inject map func!");
		s2.forEach(i -> System.out.println(i));
		System.out.println("main end");
	}

}
