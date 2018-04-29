package com.weijinglab.blockgame;

import java.util.ArrayList;
import java.util.List;

public class Block {
	public Block(Point[] pointArr) {
		pointList.clear();
		for(Point p : pointArr) {
			pointList.add(p);
		}
		
	}
	public List<Point> pointList = new ArrayList<Point>();
	
}
