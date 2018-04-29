import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MyTest {
	
	static String testcaseName = "testcase.txt";

	public static void main(String[] args) throws FileNotFoundException {

		long before = System.currentTimeMillis();
		runS4();
		long after = System.currentTimeMillis();
		System.out.println((double)(after - before) / 1000);
		
		before = System.currentTimeMillis();
		runS8();
		after = System.currentTimeMillis();
		System.out.println((double)(after - before) / 1000);
	}
	
	public static void runS4() throws FileNotFoundException {
		Solution4.scanner = new Scanner(new File(testcaseName));
		Solution4.main(null);;
	}
	
	public static void runS8() throws FileNotFoundException {
		Solution8.scanner = new Scanner(new File(testcaseName));
		Solution8.main(null);
	}

}
