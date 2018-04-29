package com.weijinglab.selectiongame.model;

public class Area {
	public Point start;
	public Point end;
	public Area(int startRow, int startCol, int endRow, int endCol) {
		start = new Point(startRow, startCol);
		end = new Point(endRow, endCol);
	}
	public Area(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	public int getStartRow() {
		return start.row;
	}
	public int getStartCol() {
		return start.col;
	}
	public int getEndRow() {
		return end.row;
	}
	public int getEndCol() {
		return end.col;
	}
}
