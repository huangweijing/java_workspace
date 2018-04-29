package com.weijinglab.usedb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UseDummyDatabase {
	
	public static void main(String[] args) throws InterruptedException {
		List<DummyDBInvocation> dbInvokeList = new ArrayList<DummyDBInvocation>();
		List<Thread> threadList = new ArrayList<Thread>();
		String[] studentList = new String[] {
				"weijing", "akira"
		};
		
		String[] courseList = new String[] {
				"java", "c++", "c#", "html", "css", "xml",
				"network", "computer concept", "microchip", "database", "javascript",
				"operating system", "compling concept", "data structure", "maths", "advanced english"
		};
		
		for(String studentName : studentList) {
			for(String courseName : courseList) {
				DummyDBInvocation dbInvoke = new DummyDBInvocation(studentName, courseName);
				Thread dbInvokeThread = new Thread(dbInvoke);
				dbInvokeThread.start();
				dbInvokeList.add(dbInvoke);
				threadList.add(dbInvokeThread);
			}
		}
		
		for(Thread thread : threadList) {
			thread.join();
		}
		
		HashMap<String, Integer> scoreTable = new HashMap<String, Integer>();
		for(DummyDBInvocation dbInvoke : dbInvokeList) {
			Integer scoreSum = scoreTable.get(dbInvoke.getStudentName());
			if(scoreSum == null) {
				scoreSum = 0;
			}
			scoreSum = scoreSum + dbInvoke.getCourseScore();
			scoreTable.put(dbInvoke.getStudentName(), scoreSum);
		}
		
		for(String studentName : studentList) {
			System.out.println(String.format("Name: %1$s, Avg: %2$s", 
					studentName, scoreTable.get(studentName) / courseList.length ));
			
		}

	}

}
