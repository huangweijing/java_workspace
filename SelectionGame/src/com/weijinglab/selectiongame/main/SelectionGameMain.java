package com.weijinglab.selectiongame.main;

import java.util.ArrayList;
import java.util.List;

import com.weijinglab.selectiongame.model.Action;
import com.weijinglab.selectiongame.model.ActionType;
import com.weijinglab.selectiongame.model.Area;

public class SelectionGameMain {
//	static int winBoard[][] = new int[][] { 
//		{1,1,0,1,0},
//		{1,0,1,0,0},
//		{0,1,1,0,0},
//		{1,0,0,0,1},
//		{0,0,0,1,0}
//};
	static int winBoard[][] = new int[][] {
		{ 1, 1, 1 },
		{ 1, 0, 0 },
		{ 1, 0, 1 },
	};

	public static void main(String[] args) {
		
		//int[][] board = new int(3,2,2);
		
		int gameBoard[][] = new int[][] { 
				{0,0,0},
				{0,0,0},
				{0,0,0},
		};
		
//		int gameBoard[][] = new int[][] { 
//				{0,0,0,0,0},
//				{0,0,0,0,0},
//				{0,0,0,0,0},
//				{0,0,0,0,0},
//				{0,0,0,0,0}
//		};
		
		List<Action> actionList = new ArrayList<Action>();
		actionList.add(new Action(ActionType.PLUS));
		actionList.add(new Action(ActionType.PLUS));
		actionList.add(new Action(ActionType.REVERSE));
		actionList.add(new Action(ActionType.REVERSE));
		
		play(gameBoard, 0, actionList);
//		Area testArea = new Area(0,0,4,4);
//		Action testAction = new Action(ActionType.REVERSE);
//		testAction.area = testArea;
//		testAction.print();
//		doAction(winBoard, testAction);
//		printBoard(winBoard);
//		System.out.println(checkBoardEqual(gameBoard, gameBoard));
	}
	
	public static void play(int[][] board, int step, List<Action> actionList) {
		if(step <= 1) {
			System.out.println("step=" + step);
		}
		if(step >= actionList.size()) {
			for(Action action : actionList) {
				action.print();
			}
			printBoard(board);
			if(checkBoardEqual(board, winBoard)) {
			}
			return;
		}
		for(int rowStart=0; rowStart<board.length; rowStart++) {
			
			for(int colStart=0; colStart<board.length; colStart++) {
				for(int rowEnd=rowStart; rowEnd<board.length; rowEnd++) {
					for(int colEnd=colStart; colEnd<board.length; colEnd++) {
						Area area = new Area(rowStart, colStart, rowStart, rowEnd);
						Action action = actionList.get(step);
						action.area = area;
						int newBoard[][] = copyBoard(board);
						doAction(newBoard, action);
						play(newBoard, step+1, actionList);
					}
				}
			}
		}
	}
	
	public static void doAction(int[][] board, Action action) {
		doAction(board, action.area, action.type);
	}
	
	public static void doAction(int[][] board, Area area, ActionType actionType) {
		
		if(actionType == ActionType.PLUS) {
			add(board, area);
		} else if (actionType == ActionType.MINUS) {
			minus(board, area);
		} else if (actionType == ActionType.REVERSE) {
			reverse(board, area);
		}
	}
	
	public static void add(int[][] board, Area area) {
		for(int row=area.getStartRow(); row<=area.getEndRow(); row++) {
			for(int col=area.getStartCol(); col<=area.getEndCol(); col++) {
				board[row][col] = 1;
			}
		}
	}
	
	public static void minus(int[][] board, Area area) {
		for(int row=area.getStartRow(); row<=area.getEndRow(); row++) {
			for(int col=area.getStartCol(); col<=area.getEndCol(); col++) {
				board[row][col] = 0;
			}
		}
	}

	public static void reverse(int[][] board, Area area) {
		for(int row=area.getStartRow(); row<=area.getEndRow(); row++) {
			for(int col=area.getStartCol(); col<=area.getEndCol(); col++) {
				if(board[row][col] == 0) {
					board[row][col] = 1;
				} else if(board[row][col] == 1) {
					board[row][col] = 0;
				}
			}
		}
	}
	
	public static int[][] copyBoard(int[][] source) {
		int[][] newBoard = new int[source.length][source.length];
		for(int i=0; i<source.length; i++) {
			for(int j=0; j<source.length; j++) {
				newBoard[i][j] = source[i][j];
			}
		}
		return newBoard;
	}

	public static boolean checkBoardEqual(int[][] src, int[][] dest) {
		for(int i=0; i<src.length; i++) {
			for(int j=0; j<src.length; j++) {
				if(dest[i][j] != src[i][j])
					return false;
			}
		}
		return true;
	}
	

	public static void printBoard(int[][] boardToPrint) {
		for(int i=0; i<boardToPrint.length; i++) {
			for(int j=0; j<boardToPrint.length; j++) {
				System.out.print(boardToPrint[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
