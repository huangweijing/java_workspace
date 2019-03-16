import java.math.BigInteger;
import java.util.Scanner;


public class QueensAttackII {

	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		BigInteger result = new BigInteger("1");
		result.multiply(result);
		
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int qRow = scanner.nextInt();
		int qCol = scanner.nextInt();
		//ֱ��ֱ�¾���������ϰ���
		int minRightCol = n + 1;
		int maxLeftCol = 0;
		int maxUpRow = 0;
		int minDownRow = n + 1;
		//ͨ��row����¼б��б�¾���������ϰ���
		int maxLeftupRow = 0;
		int maxRightupRow = 0;
		int minLeftDownRow = n + 1;
		int minRightDownRow = n + 1;
		
		//������������һ������ͷ����һ���޷�������ȥ qRow+t1=n, qCol+t2=n => t1,t2��С���Ǹ�
		if(qRow <= qCol) {
			minRightDownRow = qRow + (n - qCol) + 1;
		}
		if(qRow > qCol) {
			maxLeftupRow = qRow - qCol;
		}
		if(qRow -1 >= n - qCol) {
			maxRightupRow = qRow - (n - qCol) - 1;
		}
		if(n - qRow >= qCol - 1) {
			minLeftDownRow = qRow + (qCol - 1) + 1; 
		}
		
//		System.out.println(minLeftDownRow);
//		System.out.println(maxRightupRow);
//		System.out.println(minRightDownRow);
//		System.out.println(maxLeftupRow);
		
		while(k-- > 0) {
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			
			if(qRow == row) {
				if(qCol < col) {
					minRightCol = col < minRightCol ? col : minRightCol;
				}
				if(qCol > col) {
					maxLeftCol = col > maxLeftCol ? col : maxLeftCol;
				}
			}
			if(qCol == col) {
				if(qRow < row) {
					minDownRow = row < minDownRow ? row : minDownRow;
				}
				if(qRow > row) {
					maxUpRow = row > maxUpRow ? row : minDownRow;
				}
			}
			//���������б����+,+
			if(qRow - row == qCol - col) {
				//
				if(qRow < row) {
					minRightDownRow = row < minRightDownRow ? row : minRightDownRow;
				}
				if(qRow > row) {
					maxLeftupRow = row > maxLeftupRow ? row : maxLeftupRow;
				}
			}
			//������ڸ�б����+,-
			if(qRow - row == col - qCol) {
				//qRow
				if(qRow < row) {
					minLeftDownRow = row < minLeftDownRow ? row : minLeftDownRow;
				}
				if(qRow > row) {
					maxRightupRow = row > maxRightupRow ? row : maxRightupRow;
				}
				
			}
		}
		int attackRange = 0;
		attackRange += minRightCol - maxLeftCol - 2;
		attackRange += minDownRow - maxUpRow - 2;
		attackRange += minLeftDownRow - maxRightupRow - 2;
		attackRange += minRightDownRow - maxLeftupRow - 2;

//		System.out.println(minRightCol);
//		System.out.println(maxLeftCol);
//		System.out.println(minDownRow);
//		System.out.println(maxUpRow);
//		System.out.println(minLeftDownRow);
//		System.out.println(maxRightupRow);
//		System.out.println(minRightDownRow);
//		System.out.println(maxLeftupRow);
		
		
		
		System.out.println(attackRange);

	}

}
