import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class MyTest3 {
	
	static String testcaseName = "input13.txt";

	public static void main(String[] args) throws IOException {

		long before = System.currentTimeMillis();
		solution2();
		long after = System.currentTimeMillis();
		System.out.println((double)(after - before) / 1000);
		
		before = System.currentTimeMillis();
		solution1();
		after = System.currentTimeMillis();
		System.out.println((double)(after - before) / 1000);
	}

	public static void solution1() throws IOException {
		ArrayManipulation.scanner = new Scanner(new File(testcaseName));
		ArrayManipulation.main(null);
	}
	
	public static void solution2() throws IOException {
		ArrayManipulation2.scanner = new Scanner(new File(testcaseName));
		ArrayManipulation2.main(null);
	}

}
