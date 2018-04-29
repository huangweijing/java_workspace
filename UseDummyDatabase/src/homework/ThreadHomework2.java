package homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.weijinglab.usedb.DummyDBInvocation;


public class ThreadHomework2{
	public static void main(String[] args) throws InterruptedException {
		System.out.println("The mainThread has just started.");
		List<AkirasScore> akiraThreadList = new ArrayList<AkirasScore>();
		List<WeijingsScore> weijingThreadList = new ArrayList<WeijingsScore>();
		long startTime = System.currentTimeMillis();
		
		StudentDataa studentData = new StudentDataa();
		for(int i=0; i<16; i++) {
			AkirasScore myThread1 = new AkirasScore(studentData);
			myThread1.start();
			akiraThreadList.add(myThread1);
		}
		for(int i=0; i<16; i++) {
			WeijingsScore myThread1 = new WeijingsScore(studentData);
			myThread1.start();
			weijingThreadList.add(myThread1);
		}

		for(Thread thread : akiraThreadList) {
			thread.join();
		}
		for(Thread thread : weijingThreadList) {
			thread.join();
		}
		

		HashMap<String, Integer> scoreTable = new HashMap<String, Integer>();
		for(AkirasScore akiraScore : akiraThreadList) {
			Integer scoreSum = scoreTable.get("akira");
			if(scoreSum == null) {
				scoreSum = 0;
			}
			scoreSum = scoreSum + akiraScore.getScore();
			scoreTable.put("akira", scoreSum);
		}
		for(WeijingsScore weijingScore : weijingThreadList) {
			Integer scoreSum = scoreTable.get("weijing");
			if(scoreSum == null) {
				scoreSum = 0;
			}
			scoreSum = scoreSum + weijingScore.getScore();
			scoreTable.put("weijing", scoreSum);
		}
		
		for(String studentName : studentData.studentList) {
			System.out.println(String.format("Name: %1$s, Avg: %2$s", 
					studentName, scoreTable.get(studentName) / studentData.courseList.length ));
		}
		
		long timeUsed = System.currentTimeMillis()-startTime;
		System.out.println("The mainThread has just finished\nIt took " + timeUsed + " to finish running.");
	}
}

