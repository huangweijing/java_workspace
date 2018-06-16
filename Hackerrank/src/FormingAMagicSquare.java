import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class FormingAMagicSquare {
	public static Scanner scanner = new Scanner(System.in);
	public static int[][] inputarr, solution;
	public static int minimumMove = Integer.MAX_VALUE;
	public static int SQUARE_SIZE = 3;
	public static int SUM_OF_ROW = (1 + SQUARE_SIZE * SQUARE_SIZE) * SQUARE_SIZE / 2;
	public static void main(String[] args) {
		solution = new int[SQUARE_SIZE][SQUARE_SIZE];
		inputarr = new int[SQUARE_SIZE][SQUARE_SIZE];
		for(int i=0; i<SQUARE_SIZE; i++) {
			for(int j=0; j<SQUARE_SIZE; j++)
				inputarr[i][j] = scanner.nextInt();
		}
		int[][] arr = new int[SQUARE_SIZE][SQUARE_SIZE];
		Integer[] numArr = new Integer[SQUARE_SIZE * SQUARE_SIZE];
		initSquare(numArr);
		formMagicSquare(Arrays.asList(numArr), arr, 0);

		System.out.println(minimumMove);

//		for(int i=0; i<arr.length; i++) {
//			System.out.println(Arrays.toString(solution[i]));
//		}
		
	}
	
	public static void initSquare(Integer[] numArr) {
		int value = 1;
		for(int i=0; i<numArr.length; i++) {
			numArr[i] = value++;
		}
	}
	
	public static void formMagicSquare(List<Integer> numList, int[][] arr, int level) {
		if(level == SQUARE_SIZE * SQUARE_SIZE) {
			if(isMagic(arr)) {
				int move = 0;
				for(int i=0; i<SQUARE_SIZE; i++) {
					for(int j=0; j<SQUARE_SIZE; j++) {
						move += Math.abs(arr[i][j] - inputarr[i][j]);
					}
				}
				if(move < minimumMove) {
					minimumMove = move;
					for(int i=0; i<arr.length; i++) {
						System.arraycopy(arr[i], 0, solution[i], 0, arr[i].length);
					}
				}
			}
			return;
		}
		for(int i=0; i<numList.size(); i++) {
			int num = numList.get(i);
			arr[level/SQUARE_SIZE][level%SQUARE_SIZE] = num;
			if(level % SQUARE_SIZE == SQUARE_SIZE - 1 && !isMagicRow(arr, level / SQUARE_SIZE)) {
				continue;
			}
			if(level / SQUARE_SIZE == SQUARE_SIZE - 1 && !isMagicCol(arr, level % SQUARE_SIZE)) {
				continue;
			}
			List<Integer> newList = new ArrayList<>(numList); 
			newList.remove(i);
			formMagicSquare(newList, arr, level+1);
			arr[level / SQUARE_SIZE][level % SQUARE_SIZE] = 0;
		}
	}
	
	public static boolean isMagic(int[][] arr) {
		int pmSum = 0;
		int ppSum = 0;
		int mmSum = 0;
		int mpSum = 0;
		for(int i=0; i<SQUARE_SIZE; i++) {
			int rowSum = 0; 
			int colSum = 0;
			for(int j=0; j<SQUARE_SIZE; j++) {
				rowSum += arr[i][j];
				colSum += arr[j][i];
			}
			if(rowSum != SUM_OF_ROW || colSum != SUM_OF_ROW) 
				return false;
			ppSum += arr[i][i];
			mmSum += arr[SQUARE_SIZE - 1 - i][SQUARE_SIZE - 1 - i];
			mpSum += arr[SQUARE_SIZE - 1 - i][i];
			pmSum += arr[i][SQUARE_SIZE - 1 - i];
		}
		if(ppSum != SUM_OF_ROW || mmSum != SUM_OF_ROW || mpSum != SUM_OF_ROW || pmSum != SUM_OF_ROW)
			return false;
		return true;
	}

	public static boolean isMagicRow(int[][] arr, int rowIdx) {
		int rowSum = 0;
		for(int j=0; j<SQUARE_SIZE; j++) {
			rowSum += arr[rowIdx][j];
		}
		return rowSum == SUM_OF_ROW;
	}
	public static boolean isMagicCol(int[][] arr, int colIdx) {
		int colSum = 0;
		for(int j=0; j<SQUARE_SIZE; j++) {
			colSum += arr[j][colIdx];
		}
		return colSum == SUM_OF_ROW;
	}
}
