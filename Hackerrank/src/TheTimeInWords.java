import java.util.Scanner;


public class TheTimeInWords {
	public static Scanner scanner = new Scanner(System.in);
	static String[] numArr = new String[] {
			"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
			, "eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen", "eighteen",
			"nineteen", "twenty"
	};
	public static void main(String[] args) {
		int hour = scanner.nextInt();
		int minute = scanner.nextInt();
		if(minute == 0) {
			System.out.println(numArr[hour] + " o' clock");
		} else if(minute == 15) {
			System.out.println("quarter past " + numArr[hour]);
		} else if(minute == 30) {
			System.out.println("half past " + numArr[hour]);
		} else if(minute == 45) {
			System.out.println("quarter to " + numArr[hour+1]);
		} else if(minute == 1) {
			System.out.println(numArr[1] + " minute past " + numArr[hour]);
		} else if(minute == 59) {
			System.out.println(numArr[1] + " minute to " + numArr[hour+1]);
		} else if(minute < 30) {
			System.out.println(n(minute) + " minutes past " + numArr[hour]);
		} else if(minute > 30) {
			System.out.println(n(60 - minute) + " minutes to " + numArr[hour+1]);
		}
	}
	
	static String n(int num) {
		if(num > 20)
			return numArr[20] + " " + numArr[num - 20];
		else
			return numArr[num];
	}

}
