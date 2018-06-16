import java.math.BigInteger;
import java.util.Scanner;


public class ACMICPCTeam {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String line = scanner.nextLine();
		String[] lineArr = line.split(" ");
		int n = Integer.valueOf(lineArr[0]);
		int m = Integer.valueOf(lineArr[1]);
		BigInteger numArr[] = new BigInteger[n];
		for(int i=0; i<n; i++) {
			line = scanner.nextLine();
			BigInteger num = new BigInteger(line, 2);
			numArr[i] = num;
		}
		int maxSol = 0;
		int wayCnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				BigInteger result = numArr[i].or(numArr[j]);
				if(result.bitCount() > maxSol)
					maxSol = result.bitCount();
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				BigInteger result = numArr[i].or(numArr[j]);
				if(result.bitCount() == maxSol) {
					wayCnt++;
				}
			}
		}
		System.out.println(maxSol);
		System.out.println(wayCnt);
	}

}
