import java.util.Scanner;


public class XorSequence {
	
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		int q = scanner.nextInt();
		while(q-- > 0) {
			long l = scanner.nextLong();
			long r = scanner.nextLong();
			long result = 0;
			
			if(r-l > 16) {
				long bigNearL = l & 7;
				if(bigNearL > 0)
					bigNearL = l - bigNearL + 8;
				else
					bigNearL = l;
				
				long smallNearR = r - (r & 7);
				
				for(long i=l; i<bigNearL; i++) {
					result = result ^ a(i);
				}
				for(long i=smallNearR; i<=r; i++) {
					result = result ^ a(i);
				}
			} else {
				for(long i=l; i<=r; i++) {
					result = result ^ a(i);
				}
			}
			System.out.println(result);			
		}
	} 
	
	static long a(long n) {
		int pattern = (int)(n & 3);
		switch(pattern) {
		case 0:
			return n;
		case 1:
			return 1;
		case 2:
			return 1 ^ n;
		case 3:
			return 0;
		}
		return 0l;
	}
	
	static long solution(int r, int l) {
		long result = 0;
		int i=l;
		
		if( ((r - l) & 1) == 1) {
			for(i=l; i<r; i+=2) {
				result = result ^ (i+1);
			}
		} else {
			for(i=l; i<r; i+=2) {
				result = result ^ (i+1);
			}
			i = r;
			do {
				result  = result ^ i;
			} while(i-- > 0);
		}
		return result;
	}

}
