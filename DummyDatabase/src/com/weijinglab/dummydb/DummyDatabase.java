package com.weijinglab.dummydb;

public class DummyDatabase {
	
	/**
	 * queryCourseScore
	 * @param studentName 
	 * @param courseName
	 * @return courseScore (the progress will take 2 seconds to complete)
	 */
	public static Integer queryCourseScore(String studentName, String courseName) {
		Integer dummyScore = 0;
		String hashString = studentName + courseName;
		for(Integer i = 0; i < hashString.length(); i++) {
			dummyScore = dummyScore + ((int)hashString.charAt(i));
		}
		dummyScore = dummyScore % 100;
		dummyScore = dummyScore + (dummyScore <= 50? 50 : 0);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return dummyScore;
	}

	public static void main(String[] args) {
		System.out.println(DummyDatabase.queryCourseScore("akira", "java"));

	}

}
