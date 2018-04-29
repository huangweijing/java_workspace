package homework;

import com.weijinglab.dummydb.DummyDatabase;

class StudentDataa {

	String[] studentList = new String[] {
			"weijing", "akira"
	};

	String[] courseList = new String[] {
			"java", "c++", "c#", "html", "css", "xml",
			"network", "computer concept", "microcip", "database", "javascript",
			"operating system", "compling concept", "data structure", "maths", "advanced english"
	};

	public int scoreCalculating(String studentName, String courseName) {
		int studentScore = DummyDatabase.queryCourseScore(studentName,courseName);
//		System.out.println(studentName + "'s " + courseName + " score is " + studentScore);
		return studentScore;
	}
}
class AkirasScore extends Thread{
	StudentDataa studentDataa;
	private Integer score;
	static int n=-1;
	
	AkirasScore(StudentDataa studentDataa){
		this.studentDataa = studentDataa;
	}
		
	public void run() {
		synchronized(""){
			n++;
		}
		score = studentDataa.scoreCalculating("akira",studentDataa.courseList[n]);
	}
	
	public Integer getScore() {
		return score;
	}
}

class WeijingsScore extends Thread{
	StudentDataa studentDataa;
	private Integer score;
	static int n=-1;
	
	WeijingsScore(StudentDataa studentDataa){
		this.studentDataa = studentDataa;
	}
		
	public void run() {
		synchronized(""){
			n++;
		}
		score = studentDataa.scoreCalculating("weijing",studentDataa.courseList[n]);
	}
	
	public Integer getScore() {
		return score;
	}
}
