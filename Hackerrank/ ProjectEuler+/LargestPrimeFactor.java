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

    private static boolean[] check = new boolean[100000000]; // �洢���
    private static int[] primeList = new int[100000]; // �洢����

    private static void Euler(int num) {
        int count = 0;
        for (int indexI = 2; indexI <= num; indexI++) {
            if (!check[indexI]) {
                primeList[count++] = indexI;
            }
            // ÿһ������ȥ���Ե�ǰ�������������е�������� indexI �Ǻ������� indexI % primeList[indexJ] == 0����ô��ֻ�ܳ��Ե�һ������ 2
            // �磺2��2��3��(2��3)��4��(2)��5��(2��3��5)��6��(2)��7��(2��3��5��7)��8��(2)��9��(2��3)��10��(2)
            for (int indexJ = 0; indexJ < count; indexJ++) {
                // �����ʱ������
                if (indexI * primeList[indexJ] > num) {
                    break;
                }
                check[indexI * primeList[indexJ]] = true;
                // ��� indexI ��һ������������ indexI % primeList[indexJ] == 0
                // ��֤��ÿ������ֻ�ᱻ������С������ɸ��
                if (indexI % primeList[indexJ] == 0) {
                    break;
                }
            }
        }
    }

}
