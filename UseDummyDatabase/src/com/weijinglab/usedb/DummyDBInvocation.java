package com.weijinglab.usedb;

import com.weijinglab.dummydb.DummyDatabase;

public class DummyDBInvocation implements Runnable {
	
	private String studentName;
	private String courseName;
	private Integer courseScore;

	public DummyDBInvocation(String studentName, String courseName) {
		this.studentName = studentName;
		this.courseName = courseName;
	}
	
	@Override
	public void run() {
		courseScore = DummyDatabase.queryCourseScore(studentName, courseName);
	}

	public String getStudentName() {
		return studentName;
	}

	public String getCourseName() {
		return courseName;
	}

	public Integer getCourseScore() {
		return courseScore;
	}

	public String toString(){
		return String.format("Name: %1$s, Course: %2$s, Score: %3$s"
				, this.studentName, this.courseName, this.courseScore);
	}
	
}
