import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EvenFibonacci {

    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int t = scanner.nextInt();
		Long[] evenFibList = getFib(); 
		while(t-- > 0) {
			long n = scanner.nextLong();
			long sum = 0l;
			for(Long fib : evenFibList) {
				if(fib > n)
					break;
				sum = sum + fib;
			}
			System.out.println(sum);
		}
	}
	
	static Long[] getFib() {
		List<Long> fibList = new ArrayList<>();
		List<Long> evenFibList = new ArrayList<>();
		long n = 40000000000000000L;
		fibList.add(1L);
		fibList.add(2L);
		evenFibList.add(2L);
		while(fibList.get(fibList.size() - 1) <= n) {
			Long num = fibList.get(fibList.size() - 1) 
					+ fibList.get(fibList.size() - 2);
			fibList.add(num);
			if(0 == (num & 1)) {
				evenFibList.add(num);
			}
		}
		return evenFibList.toArray(new Long[0]);
	}

}
