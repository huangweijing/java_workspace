package homework;
import java.util.LinkedList;
import java.util.List;

public class Calender {
	public static void main(String[] args) {
		CreateCalender createCalender = new CreateCalender(2017,7);//SUNDAY==7;Monday==1;
		createCalender.printCalender();
	}
}
class CreateCalender{
	static int year;
	static int january1st;
	static List<Integer> daysOfMonth = new LinkedList<Integer>();
	static Integer[] firstDays = new Integer[12];
	static String[] twelveMonths = new String[] {"January","February","March","April","May","June",
			"July","August","September","October","November","December"};
	static String sevenDays= "SUN\tMON\tTUE\tWED\tTHU\tFRI\tSAT";
	CreateCalender(int year,int january1st){
		this.year = year;
		this.january1st = january1st;//Monday==1;
	}

	private  int daysOfFeb() {
		if(((year%100!=0)&&(year%4==0))||((year%100==0)&&(year%400==0))){
			return 29;
		}else {
			return 28;
		}
	}

	private List<Integer> getDaysOfMonth(){
		Integer[] days = new Integer[] {31,31,30,31,30,31,31,30,31,30,31};
		for(Integer day : days) {
			daysOfMonth.add(day);
		}
		daysOfMonth.add(1,daysOfFeb());
		//System.out.println("getDaysOfMonth" +daysOfMonth);
		return daysOfMonth;
	}

	private Integer[] getFirstDays(){
		getDaysOfMonth();
		firstDays[0]=january1st%7;
		int daysSum=january1st;//Monday==1;
		for(int i=0; i<daysOfMonth.size()-1;i++) {
			daysSum=daysSum+daysOfMonth.get(i);
			firstDays[i+1]=daysSum%7;
		}
		//System.out.println("getFirstDays"+ Arrays.toString(firstDays));
		return firstDays;
	}
	public void printCalender() {
		getFirstDays();
		System.out.println("\t\tThe Calender of year " + year);
		for(int i=0; i<twelveMonths.length; i++) {
			System.out.println(twelveMonths[i]);
			System.out.println(sevenDays);
			for(int n=0; n<firstDays[i];n++) {
				System.out.print("\t");
			}
			for(int m=1; m<=daysOfMonth.get(i);m++) {
				if((m+firstDays[i])%7==0) {
					System.out.print(m+"\n");
				}else if(m==daysOfMonth.get(i)){
					System.out.println(m);	
				}else {
					System.out.print(m+"\t");
				}
			}
		}


	}
}