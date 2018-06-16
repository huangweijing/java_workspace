import java.util.Scanner;


public class LargestPrimeFactor {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int t = scanner.nextInt();
		while(t-- > 0) {
			Long num = scanner.nextLong();
//			Euler(num);
		}
	}

    private static boolean[] check = new boolean[100000000]; // 存储标记
    private static int[] primeList = new int[100000]; // 存储素数

    private static void Euler(int num) {
        int count = 0;
        for (int indexI = 2; indexI <= num; indexI++) {
            if (!check[indexI]) {
                primeList[count++] = indexI;
            }
            // 每一个数都去乘以当前素数表里面已有的数，如果 indexI 是合数，且 indexI % primeList[indexJ] == 0，那么它只能乘以第一个素数 2
            // 如：2×2、3×(2、3)、4×(2)、5×(2、3、5)、6×(2)、7×(2、3、5、7)、8×(2)、9×(2、3)、10×(2)
            for (int indexJ = 0; indexJ < count; indexJ++) {
                // 过大的时候跳出
                if (indexI * primeList[indexJ] > num) {
                    break;
                }
                check[indexI * primeList[indexJ]] = true;
                // 如果 indexI 是一个合数，而且 indexI % primeList[indexJ] == 0
                // 保证了每个合数只会被它的最小素因子筛掉
                if (indexI % primeList[indexJ] == 0) {
                    break;
                }
            }
        }
    }

}
