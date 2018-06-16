package com.weijinglab.lib.tree;

import java.util.Arrays;

public class SegmentTree {

	public static class Node {
		public int from;
		public int to;
		public int sum;
		public Node(int from, int to) {
			this.from = from;
			this.to = to;
		}
		public String toString() {
			return String.format("[from=%s, to=%s]=%s", from, to, sum);
		}
	}
	
	private Node[] segmentNodeArr;
	private int[] orgArr;
	
	public SegmentTree(int[] arr) {
		orgArr = Arrays.copyOf(arr, arr.length);
		int MaxleftCnt = Integer.highestOneBit(arr.length - 1) << 1;
		segmentNodeArr = new Node[(MaxleftCnt << 1) - 1];
		build(0, 0, orgArr.length);
//		System.out.println(Arrays.toString(segmentNodeArr));
	}
	
	public Node build(int v, int start, int size) {
		Node node = new Node(start, start + size - 1);
		segmentNodeArr[v] = node;
		if(size == 1) {
			node.sum = orgArr[start];
		} else {
			int subSize = (size + 1)/ 2;
			node.sum = build(v * 2 + 1, start, subSize).sum + 
					build(v * 2 + 2, start + subSize, size - subSize).sum;
		}
		return node;
	}
	
	public int getSum(int from, int to) {
		return getSum(0, from, to);
	}
	
	public int getSum(int v, int from, int to) {
		if(from <= segmentNodeArr[v].from && segmentNodeArr[v].to <= to ) {
			return segmentNodeArr[v].sum; 
		} else if(segmentNodeArr[v].to < from || to < segmentNodeArr[v].from) {
			return 0;
		} else {
			return getSum(v * 2 + 1, from, to) + getSum(v * 2 + 2, from, to);
		}
	}
	
	public void update(int v, int index, int value) {
		if(segmentNodeArr[v].from <= index && index <= segmentNodeArr[v].to) {
			segmentNodeArr[v].sum += value - orgArr[index];
			if(segmentNodeArr[v].from  == segmentNodeArr[v].to) {
				orgArr[index] = value;
			} else {
				update(v * 2 + 1, index, value);
				update(v * 2 + 2, index, value);
			}
		} else if(segmentNodeArr[v].to < index || index < segmentNodeArr[v].from) {
			return;
		} else {
			update(v * 2 + 1, index, value);
			update(v * 2 + 2, index, value);
		}
		
	}
	
	
	public static void main(String[] args) {
		int[] arr = {3, 2, 4, 5, 8};
		SegmentTree tree = new SegmentTree(arr);
		System.out.println(tree.getSum(2, 3));
		tree.update(0, 2, 7);
		System.out.println(tree.getSum(0, 4));
	}

}
