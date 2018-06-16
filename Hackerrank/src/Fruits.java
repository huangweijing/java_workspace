
public class Fruits {

	public static void main(String[] args) {
//		System.out.println(getAnswer(13, 13, 13));
		long MAX = 10000;
		first : for(long i=1; i<MAX; i++) {
			for(long j=i+1; j<MAX; j++) {
				for(long k=j+1; k<MAX; k++) {
					if(solve(i, j, k)) {
						System.out.println(i + "," + j + "," + k);
						System.out.println(getAnswer(i, j, k));
						break first;
					}
				}	
			}	
		}
	}
	
//	public static boolean solve(double a, double b, double c) {
//		return Math.abs(c / (a + b) + b / (a + c) + a / (b+c) - 4) < 0.000000001d;
//	}
	

	public static double getAnswer(double a, double b, double c) {
		return c / (a + b) + b / (a + c) + a / (b+c);
	}
	

	public static boolean solve(long a, long b, long c) {
		long ab = a + b;
		long bc = c + b;
		long ac = c + a;
		long result = a*ac*ab + b*bc*ab + c*ac*bc;
		return (result & 4) == 0 && (result >> 4) == ab * bc * ac;
	}

}
