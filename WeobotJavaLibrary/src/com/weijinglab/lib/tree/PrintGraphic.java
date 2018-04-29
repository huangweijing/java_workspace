package com.weijinglab.lib.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.weijinglab.lib.tree.BinarySearchTree.TreeNode;

public class PrintGraphic {

	public static void printGraphic(TreeNode<Integer> treeRoot) {
		List<String> printLine = getGraphicLine(treeRoot);
		for(String line : printLine) {
			System.out.println(line);
		}
	}
	/**
	 * 打印一棵树
	 * 树的根节点一定在两颗子树的正中央
	 * 两根叉叉树枝一定在子树根节点的顶上
	 * @param treeRoot
	 * @return 打印结果
	 */
	public static List<String> getGraphicLine(TreeNode<Integer> treeRoot) {
		
		if(treeRoot.left == null && treeRoot.right == null) {
			List<String> oneDataList = new LinkedList<String>();
			oneDataList.add(treeRoot.data.toString());
			return oneDataList;
		}
		
		LinkedList<String> currentPrintout = new LinkedList<String>();
		LinkedList<String> leftPrintout = new LinkedList<String>();
		LinkedList<String> rightPrintout = new LinkedList<String>();
		if(treeRoot.left != null) {
			leftPrintout.addAll(getGraphicLine(treeRoot.left));
		}
		if(treeRoot.right != null) {
			rightPrintout.addAll(getGraphicLine(treeRoot.right));
		}

		//左の子供のツリーの幅
		Integer leftWidth = maxSizeOfLine(leftPrintout);
		//左の子供のツリー高さ
		Integer leftHeight = leftPrintout.size();
		//右の子供のツリーの幅
		Integer rightWidth = maxSizeOfLine(rightPrintout);
		//左の子供のツリー幅
		Integer rightHeight = rightPrintout.size();
		if(leftHeight > 0) {
			//一行目の数字の最後のけ桁の真上に/を配置する。
			String leftFirst = leftPrintout.getFirst();
			Integer leftSpaceCount = getLeftSapceCount(leftFirst);
			leftPrintout.addFirst(addSpacesToRight(repeat(" ", leftSpaceCount + leftFirst.trim().length() -1) +  "/" , leftWidth));
		}
		if(rightHeight > 0) {
			//一行目の数字の最初のけ桁の真上に\を配置する。
			String rightFirst = rightPrintout.getFirst();
			Integer rightSpaceCount = getLeftSapceCount(rightFirst);
			rightPrintout.addFirst(addSpacesToRight(repeat(" ", rightSpaceCount) +  "\\", rightWidth));
		}
		
		Integer rootDataLen = treeRoot.data.toString().length();
		Integer higerHeight = leftHeight > rightHeight ? leftHeight : rightHeight;
		for(Integer i=0; i<=higerHeight; i++){
			String currentLine = "";
			String leftLine = "";
			String rightLine = "";
			if(i < leftPrintout.size()) {
				leftLine = leftPrintout.get(i);
			} else {
				leftLine = repeat(" ", leftWidth);
			}
			if(i < rightPrintout.size()) {
				rightLine = rightPrintout.get(i);
			} else {
				rightLine = repeat(" ", rightWidth);
			}
			currentLine = leftLine + repeat(" ", rootDataLen) + rightLine; 
			currentPrintout.add(currentLine);
		}
		currentPrintout.addFirst(repeat(" ", leftWidth) + treeRoot.data.toString() + repeat(" ", rightWidth));
		
		return currentPrintout;
	}
	

	/**
	 * スペースで右詰
	 * @param str
	 * @param width
	 * @return
	 */
	public static String addSpacesToRight(String str, Integer width) {
		while(str.length() < width) {
			str = str + " ";
		}
		return str;
	}
	
	/**
	 * 文字列の左側のスペースを取得する
	 * @param str
	 * @return
	 */
	public static Integer getLeftSapceCount(String str){
		Integer count = 0;
		for(; str.charAt(count) == ' '; count++);
		return count;
	}
	
	/**
	 * 重複出力
	 * @param str
	 * @param times
	 * @return
	 */
	private static String repeat(String str, Integer times) {
		String result = "";
		for(int i=1; i<=times; i++) {
			result = result + str;
		}
		return result;
	}
	
	/**
	 * リストの中に最大の行のサイズを取り出す
	 * @param lines
	 * @return
	 */
	private static Integer maxSizeOfLine(List<String> lines) {
		Integer maxLength = 0;
		for(String line : lines) {
			if(line.length() > maxLength) {
				maxLength = line.length();
			}
		}
		return maxLength;
	}

}
