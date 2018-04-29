package com.weijinglab.blockgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static final int BOARD_SIZE = 6;
	public static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
	
	public static void main(String[] args) {

		List<Block> blockList = initGame();
		nextStep(board, blockList, 1);
//		boolean result = putToBoard(board, blockList.get(0), 1);
//		putToBoard(board, blockList.get(7), 2);
//		putToBoard(board, blockList.get(2), 3);
//		putToBoard(board, blockList.get(5), 4);
//		putToBoard(board, blockList.get(3), 5);
//		putToBoard(board, blockList.get(1), 6);
//		putToBoard(board, blockList.get(8), 7);
//		System.out.println(result);
		printBoard(board);
	}
	
	public static void nextStep(int[][] playBoard, List<Block> blockList, int depth) {
//		if(depth == 9) {
//			printBoard(playBoard);
//		}
		for(Block block : blockList) {
			int[][] currentBoard = copyPlayBoard(playBoard);
			boolean putResult = putToBoard(currentBoard, block, depth);
			//printBoard(currentBoard);
			if(putResult) {
				if(judgeSuccess(currentBoard)) {
					printBoard(currentBoard);
					break;
				}
				List<Block> noCurrent = new ArrayList<Block>();
				copyBlockList(blockList, noCurrent);
				noCurrent.remove(block);
				nextStep(currentBoard, noCurrent, depth + 1);
			}
		}
		
	}
	
	public static int[][] copyPlayBoard(int[][] source) {
		int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for(int i=0; i<BOARD_SIZE; i++) {
			for(int j=0; j<BOARD_SIZE; j++) {
				newBoard[i][j] = source[i][j];
			}
		}
		return newBoard;
	}
	
	public static void copyBlockList(List<Block>from, List<Block>to) {
		for(Block block : from) {
			to.add(block);
		}
	}
	
	public static boolean putToBoard(int[][] boardToPut, Block block, int blockName) {

//		boolean putOK = false;
		
		for(int i=0; i<BOARD_SIZE; i++) {
			for(int j=0; j<BOARD_SIZE; j++) {
				if(boardToPut[i][j] == 0) {
					//System.out.println(i + "," + j);
					List<Point> pList = block.pointList;
					for(Point p : pList) {
						if(i + p.y - 1 >= BOARD_SIZE || i + p.y - 1 < 0) {
							return false;
						}
						if(j + p.x -1 >= BOARD_SIZE || j + p.x -1 < 0) {
							return false;
						}
						try {
							if(boardToPut[i + p.y - 1][j + p.x -1] != 0) {
								return false;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						boardToPut[i + p.y - 1][j + p.x -1] = blockName;
					}
					return true;
				}
			}
//			if(putOK) {
//				break;
//			}
		}
		return false;
		
	}
	

	public static boolean judgeSuccess(int[][] boardToJudge) {
		for(int i=0; i<BOARD_SIZE; i++) {
			for(int j=0; j<BOARD_SIZE; j++) {
				if(boardToJudge[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	public static void clearBoard(int[][] boardToClear) {
		for(int i=0; i<BOARD_SIZE; i++) {
			for(int j=0; j<BOARD_SIZE; j++) {
				boardToClear[i][j] = 0;
			}
		}
	}
	
	public static void printBoard(int[][] boardToPrint) {
		for(int i=0; i<BOARD_SIZE; i++) {
			for(int j=0; j<BOARD_SIZE; j++) {
				System.out.print(boardToPrint[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
//	public static List<Block> initGame() {
//		clearBoard(board);
//		Point[] shape1 = new Point[] {
//				new Point(1,1),
//				new Point(2,1),
//				new Point(3,1)
//		};
//		
//		Point[] shape2 = new Point[] {
//				new Point(1,1),
//				new Point(2,1)
//		};
//
//		Point[] shape3 = new Point[] {
//				new Point(1,1),
//				new Point(1,2),
//				new Point(1,3)
//		};
//		
//		Point[] shape4 = new Point[] {
//				new Point(1,1),
//				new Point(1,2),
//				new Point(2,2),
//				new Point(3,2)
//		};
//		
//		Point[] shape5 = new Point[] {
//				new Point(3,1),
//				new Point(1,2),
//				new Point(2,2),
//				new Point(3,2)
//		};
//		Block[] blockArr = new Block[] {
//			new Block(shape1),
//			new Block(shape2),
//			new Block(shape3),
//			new Block(shape4),
//			new Block(shape5)
//		};
//		return Arrays.asList(blockArr);
//	}
	
	public static List<Block> initGame() {
		clearBoard(board);
		
		Point[] shape1 = new Point[] {
				new Point(1,1),
				new Point(2,1)
		};

		Point[] shape2 = new Point[] {
				new Point(1,1),
				new Point(2,1),
				new Point(1,2)
		};

		Point[] shape3 = new Point[] {
				new Point(1,1),
				new Point(2,1),
				new Point(2,2),
		};
		

		Point[] shape4 = new Point[] {
				new Point(1,1),
				new Point(2,1),
				new Point(1,2),
				new Point(2,2),
				new Point(2,3),
				new Point(2,4)
		};

		Point[] shape5 = new Point[] {
				new Point(1,1),
				new Point(1,2),
				new Point(2,2),
		};

		Point[] shape6 = new Point[] {
				new Point(1,1),
				new Point(1,2),
				new Point(2,2),
				new Point(3,2),
				new Point(3,3)
		};


		Point[] shape7 = new Point[] {
				new Point(1,1),
				new Point(1,2),
				new Point(2,2),
				new Point(3,2)
		};

		Point[] shape8 = new Point[] {
				new Point(1,1),
				new Point(2,1),
				new Point(0,2),
				new Point(1,2),
				new Point(2,2),
				new Point(3,2),
				new Point(2,3)
		};
		

		Point[] shape9 = new Point[] {
				new Point(1,1),
				new Point(1,2),
				new Point(0,2)
		};
		
		Block[] blockArr = new Block[] {
				new Block(shape1),
				new Block(shape2),
				new Block(shape3),
				new Block(shape4),
				new Block(shape5),
				new Block(shape6),
				new Block(shape7),
				new Block(shape8),
				new Block(shape9)
		};
		return Arrays.asList(blockArr);
	}

	
//	public static List<Block> initGame() {
//		clearBoard(board);
//		
//		Point[] shape1 = new Point[] {
//				new Point(1,1),
//				new Point(2,1),
//				new Point(3,1),
//				new Point(3,2),
//				new Point(4,2),
//		};
//
//		Point[] shape2 = new Point[] {
//				new Point(1,1),
//				new Point(2,1),
//				new Point(3,1),
//				new Point(3,2),
//				new Point(3,3),
//		};
//
//		Point[] shape3 = new Point[] {
//				new Point(1,1),
//				new Point(1,2),
//				new Point(2,1),
//				new Point(2,2),
//				new Point(2,3),
//				new Point(2,4),
//		};
//		
//
//		Point[] shape4 = new Point[] {
//				new Point(1,1),
//				new Point(1,2),
//				new Point(1,3),
//				new Point(2,3)
//		};
//
//		Point[] shape5 = new Point[] {
//				new Point(1,1),
//				new Point(1,2),
//				new Point(2,1),
//		};
//
//		Point[] shape6 = new Point[] {
//				new Point(1,1),
//				new Point(1,2),
//				new Point(1,3),
//				new Point(2,1),
//				new Point(2,2)
//		};
//
//
//		Point[] shape7 = new Point[] {
//				new Point(1,1),
//				new Point(1,2),
//				new Point(0,2)
//		};
//
//		Point[] shape8 = new Point[] {
//				new Point(1,1),
//				new Point(2,1),
//				new Point(2,2)
//		};
//		
//
//		Point[] shape9 = new Point[] {
//				new Point(1,1),
//				new Point(2,1)
//		};
//		
//		Block[] blockArr = new Block[] {
//				new Block(shape1),
//				new Block(shape2),
//				new Block(shape3),
//				new Block(shape4),
//				new Block(shape5),
//				new Block(shape6),
//				new Block(shape7),
//				new Block(shape8),
//				new Block(shape9)
//		};
//		return Arrays.asList(blockArr);
//	}

}
