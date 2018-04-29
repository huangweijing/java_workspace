package homework;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;


public class Factoization {

	public static void main(String[] args) {
		Random random = new Random();
		Integer randomInt = 12;//random.nextInt(10000)+2;
		PrimeFactoization.printFactoization(PrimeFactoization.primeFactoization(randomInt),randomInt);
	}
}

class PrimeFactoization{
	//static List<Integer> primeList = new ArrayList<Integer>();
	public static List<Integer> primeFactoization(Integer randomInt) {
		List<Integer> primeList = new ArrayList<Integer>();
		for(Integer i=2; i<=randomInt; i++) {
			Integer remainder = randomInt%i;
			Integer quotient = randomInt/i;
			if(remainder==0) {
				primeList.add(i);
				primeList.addAll(primeFactoization(quotient));
				break;
			}else {
				continue;
			}
		}
		return primeList;
		
	}
	public static void printFactoization(List<Integer> primeList, Integer randomInt) {
		if(primeList==null) {
			System.out.println(randomInt + " is a prime number.");
		}
		System.out.println("The factoization of " + randomInt + " is: ");
		for(Integer n=0; n<primeList.size();n++) {
			if(n!=(primeList.size()-1)){
				System.out.print(primeList.get(n) + "　〜　");
			}else {
				System.out.print(primeList.get(n) + "　=　");
			}
		}
		System.out.print(randomInt);
	}
}