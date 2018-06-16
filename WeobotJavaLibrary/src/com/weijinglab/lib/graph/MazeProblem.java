package com.weijinglab.lib.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MazeProblem {

	public static Scanner scanner = new Scanner(System.in);
	
	public static final char ROAD = ' ';
	public static final char WALL = '#';
	public static final char STA = 'S';
	public static final char END = 'E';
	public static final char ANS = 'O';
	
	public static class Position {
		public int row;
		public int col;
		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public String toString() {
			return "[row=" + row + ", col=" + col + "]";
		}
	}
	
	public static void main(String[] args) {
		
		String line = scanner.nextLine();
		String[] splitedLine = line.split(" ");
		Integer n = Integer.valueOf(splitedLine[0]);
		Integer m = Integer.valueOf(splitedLine[1]);
		
		char[][] mazeData = new char[n][m];
		int[][] roadIdxData = new int[n][m];
		
		Integer roadIdx = 0;
		
		Position startPos = new Position(0, 0);
		Position endPos = new Position(n-1, m-1);
		
		List<Position> posList = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			line = scanner.nextLine();
			for(int j=0; j<line.length(); j++) {
				mazeData[i][j] = line.charAt(j);
				if(mazeData[i][j] == STA) {
					startPos = new Position(i, j);
					mazeData[i][j] = ROAD;
				}
				if(mazeData[i][j] == END) {
					endPos = new Position(i, j);
					mazeData[i][j] = ROAD;
				}
				if(mazeData[i][j] == ROAD) {
					roadIdxData[i][j] = roadIdx;
					posList.add(new Position(i, j));
					roadIdx++;
				}
			}
		}
		
//		System.out.println(roadIdxData[startPos.row][startPos.col]);
//		System.out.println(roadIdxData[endPos.row][endPos.col]);
		
		Graph g = new Graph(roadIdx);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(mazeData[i][j] != ROAD) {
					continue;
				}
				if(i + 1 < n && mazeData[i+1][j] == ROAD ) {
					g.addEdge(new Edge(roadIdxData[i][j], roadIdxData[i+1][j], 1));
				}
				if(i - 1 >= 0 && mazeData[i-1][j] == ROAD) {
					g.addEdge(new Edge(roadIdxData[i][j], roadIdxData[i-1][j], 1));
				}
				if(j + 1 < n && mazeData[i][j+1] == ROAD) {
					g.addEdge(new Edge(roadIdxData[i][j], roadIdxData[i][j+1], 1));
				}
				if(j - 1 >= 0 && mazeData[i][j-1] == ROAD) {
					g.addEdge(new Edge(roadIdxData[i][j], roadIdxData[i][j-1], 1));
				}
			}
		}
		
//		g.printEdges();
		
		long start = System.currentTimeMillis();
		List<List<Integer>> pathList = g.getBellmanFordSP_SolutionPath(
				roadIdxData[startPos.row][startPos.col]);
		long end = System.currentTimeMillis();
		System.out.println("time cost:" + (end - start) + "ms");
		List<Integer> path = pathList.get(roadIdxData[endPos.row][endPos.col]);
		for(Integer v : path) {
			mazeData[posList.get(v).row][posList.get(v).col] = ANS;
		}
		

		System.out.println("<Path>");
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(mazeData[i][j]);
			}
			System.out.println();
		}
	}

}
