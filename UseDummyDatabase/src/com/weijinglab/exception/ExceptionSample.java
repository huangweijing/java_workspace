package com.weijinglab.exception;

public class ExceptionSample {

	public static void main(String[] args) {
		Leader leader = new Leader();
		leader.setMySubordinates(new Employee[] {
				new Employee("Akira"), new Employee("weijing")	
		});
		leader.doTask();
	}
}

class Leader {
	
	private Employee[] mySubordinates = new Employee[]{};
	
	public void doTask() {
		
		for(Employee employee : this.mySubordinates) {
			
			try {
				employee.doTask();
			} catch (PlayingJavaException e) {
				System.out.println("Oh shit, " + employee.getEmployeeName() + "'s spent the whole day on playing java.");
				System.out.println("The leader fired " + employee.getEmployeeName());
				System.out.println("The leader has done the rest of the task instead of " + employee.getEmployeeName() + ".");
			} catch (Exception e) {
				System.out.println("Oh! Something must be wrong! The leader can't handle it!");
				throw e;
			}
			
		}
		System.out.println("The task of the team has been finished!");
		
		
	}

	public Employee[] getMySubordinates() {
		return mySubordinates;
	}

	public void setMySubordinates(Employee[] mySubordinates) {
		this.mySubordinates = mySubordinates;
	} 
}

class Employee {
	
	private String employeeName;
	
	public Employee(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public void doTask() throws PlayingJavaException {
		
		System.out.println(employeeName + " started doing the task.");
		
		for(int i=0; i<10; i++) {
			if(Math.random() > 0.95) {
				throw new PlayingJavaException();
			} else if(Math.random() > 0.93) {
				System.out.println(this.employeeName + ": What a pain...");
				throw new HasAStomachAcheException();
			}
			System.out.println(this.employeeName + "'s doing the job...ZZZ...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println(employeeName + " has done all of the tasks!");
		
	}

	public String getEmployeeName() {
		return employeeName;
	}

}

class PlayingJavaException extends Exception {

	private static final long serialVersionUID = 1L;
	
}

class HasAStomachAcheException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
}