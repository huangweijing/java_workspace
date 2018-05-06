import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;


public class MyTest3 {
	
	static String testcaseName = "testcase7.txt";

	public static void main(String[] args) throws IOException {
		
		for(int i = 1000; i< 200000; i = i + 1000) {
			generateTestCases(10000000, i);
			
			long before = System.currentTimeMillis();
			solution2();
			long after = System.currentTimeMillis();
			double time1 = (double)(after - before) / 1000;
//			System.out.println((double)(after - before) / 1000);
			
//			before = System.currentTimeMillis();
//			solution1();
//			after = System.currentTimeMillis();
//			double time2 = (double)(after - before) / 1000;
//			System.out.println((double)(after - before) / 1000);
//			System.out.println(String.format("%s\t%s\t%s", i, time1, time2));
			System.out.println(String.format("%s\t%s", i, time1));
		}

	}

	public static void solution1() throws IOException {
		ArrayManipulation.scanner = new Scanner(new File(testcaseName));
		ArrayManipulation.main(null);
	}
	
	public static void solution2() throws IOException {
		ArrayManipulation2.scanner = new Scanner(new File(testcaseName));
		ArrayManipulation2.main(null);
	}
	
	public static void generateTestCases(int n, int m) throws IOException {
		File file = new File(testcaseName);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter writer = new PrintWriter(bw);
		
		writer.println(n + " " + m);
		Random rand = new Random(System.nanoTime());
		
		for(int i=0; i<m; i++) {
			int a = rand.nextInt(n-100);
			int b = a + rand.nextInt(n-a);
			int k = rand.nextInt(1000000000);
			writer.println(a + " " + b + " " + k);
		}
		writer.close();
	}

}
