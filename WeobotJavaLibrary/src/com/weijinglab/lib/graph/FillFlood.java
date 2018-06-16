package com.weijinglab.lib.graph;
import java.util.Scanner;


public class FillFlood {
    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[][] a = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[i].length; j++) {
				a[i][j] = scanner.nextInt();
			}
		}
		boolean result = dfs(0, 0, a, visited);
		System.out.println(result ? "Yes" : "No");
	}
	
	public static boolean dfs(int r, int c, int[][]maze, boolean[][]visited) {
		if(r < 0 || r >= maze.length || c < 0 || c >= maze[r].length)
			return false;
		if(maze[r][c] == 0)
			return false;
		if(visited[r][c])
			return false;
		if(r == maze.length - 1 && c == maze[maze.length - 1].length - 1)
			return true;
		visited[r][c] = true;
		boolean result = false;
		result = result | dfs(r-1, c, maze, visited);
		result = result | dfs(r+1, c, maze, visited);
		result = result | dfs(r, c-1, maze, visited);
		result = result | dfs(r, c+1, maze, visited);
		return result;
	}

}
//Sample Input
//10 10
//1 0 0 0 0 0 0 0 1 1
//1 1 0 0 0 0 0 0 0 1
//1 1 0 0 0 0 0 0 0 1
//0 1 1 0 0 0 1 1 0 1
//0 1 1 1 1 1 1 1 0 1
//0 0 0 0 0 1 1 0 1 1
//0 0 0 0 0 1 0 1 1 1
//0 0 0 0 0 1 0 1 0 1
//0 0 0 0 0 1 0 1 1 1
//0 0 0 0 0 1 1 0 0 1

//3 3
//1 0 1
//1 0 0
//1 1 1
