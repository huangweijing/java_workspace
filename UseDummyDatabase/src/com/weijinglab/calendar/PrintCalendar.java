package com.weijinglab.calendar;

public class PrintCalendar {

	static class DATE {
		public static final Integer SUN = 0;
		public static final Integer MON = 1;
		public static final Integer TUE = 2;
		public static final Integer WED = 3;
		public static final Integer THU = 4;
		public static final Integer FRI = 5;
		public static final Integer SAT = 6;
	}
	
	static final Integer[] dayCountInMonth = new Integer[]{
		31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
	};
	
	public static void main(String[] args) {
		printYear(2018, DATE.MON);
	}
	
	public static Integer printYear(Integer year, Integer firstDate) {
		System.out.println(String.format("        %sÄê¥«¥ì¥ó¥À©`         ", year));
		for(int month=1; month<=12; month++) {
			firstDate = printMonth(year, month, firstDate);
			System.out.println();
		}
		return firstDate %7;
	}

	public static Integer printMonth(Integer year, Integer month, Integer firstDate) {
		System.out.println(String.format("%sÄê%sÔÂ", year, month));
		Integer totalDays = dayCountInMonth[month - 1];
		if(month == 2 && isLeapYear(year)) {
			totalDays = 29;
		}
		for(Integer d=DATE.SUN; d <= DATE.SAT; d++) {
			System.out.print(getWeekdayName(d) + " ");
		}
		System.out.println();
		for(Integer d=DATE.SUN; d < firstDate; d++) {
			System.out.print("    ");
		}
		Integer i= 1;
		Integer date = firstDate;
		for(; i<=totalDays; i++) {
			System.out.print(i + repeat(" ", 3 - i.toString().length()));
			if(( date + 1 )% 7 ==0) {
				System.out.println();
			}
			date++;
			date=date % 7;
		}
		System.out.println();
		return date % 7;
	}
	
	public static String repeat(String str, Integer count) {
		String result = str;
		for(;count > 0; count--, result+=str);
		return result;
	}
	
	public static boolean isLeapYear(Integer year) {
		return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
	}
	
	public static String getWeekdayName(Integer day) {
		return day > 6 || day < 0 ? "" : new String[] {
				"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"
		} [day];
	}
	
}
