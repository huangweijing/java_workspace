package com.weijinglab.rainwater;

public class HeightInfo implements Comparable<HeightInfo>{
	public Integer height;
	public Integer index;
	
	@Override
	public int compareTo(HeightInfo o) {
		return height - o.height;
	}
	
	@Override
	public String toString() {
		return "{height=" + height + ", index=" + index + "}";
	}
}