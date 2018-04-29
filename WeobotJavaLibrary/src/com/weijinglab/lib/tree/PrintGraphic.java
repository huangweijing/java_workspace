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
	 * ��ӡһ����
	 * ���ĸ��ڵ�һ��������������������
	 * ���������֦һ�����������ڵ�Ķ���
	 * @param treeRoot
	 * @return ��ӡ���
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

		//����ӹ��Υĥ�`�η�
		Integer leftWidth = maxSizeOfLine(leftPrintout);
		//����ӹ��Υĥ�`�ߤ�
		Integer leftHeight = leftPrintout.size();
		//�Ҥ��ӹ��Υĥ�`�η�
		Integer rightWidth = maxSizeOfLine(rightPrintout);
		//����ӹ��Υĥ�`��
		Integer rightHeight = rightPrintout.size();
		if(leftHeight > 0) {
			//һ��Ŀ�����֤�����Τ�������Ϥ�/�����ä��롣
			String leftFirst = leftPrintout.getFirst();
			Integer leftSpaceCount = getLeftSapceCount(leftFirst);
			leftPrintout.addFirst(addSpacesToRight(repeat(" ", leftSpaceCount + leftFirst.trim().length() -1) +  "/" , leftWidth));
		}
		if(rightHeight > 0) {
			//һ��Ŀ�����֤�����Τ�������Ϥ�\�����ä��롣
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
	 * ���ک`������ԑ
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
	 * �����Ф���ȤΥ��ک`����ȡ�ä���
	 * @param str
	 * @return
	 */
	public static Integer getLeftSapceCount(String str){
		Integer count = 0;
		for(; str.charAt(count) == ' '; count++);
		return count;
	}
	
	/**
	 * ���}����
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
	 * �ꥹ�Ȥ��Ф������ФΥ�������ȡ�����
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
