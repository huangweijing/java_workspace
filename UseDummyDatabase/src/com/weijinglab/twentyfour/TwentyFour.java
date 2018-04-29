package com.weijinglab.twentyfour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class TwentyFour {
	
	private static Set<String> resultOutput = new HashSet<String>();

	public static void main(String[] args) {
//		List<Double> listToCalc = Arrays.asList(new Double[]{ 3d, 4d, 5d, 6d });
		List<Double> listToCalc = Arrays.asList(new Double[]{ 3d, 4d, 0.1d, 0.1d, 0.1d });
//		List<Double> listToCalc = Arrays.asList(new Double[]{ 1.5d, 2.5d, 3d, 3d, 4.5d });
		System.out.println("find a way to manipulate numbers so that the end result is 24: " + listToCalc);
		calc24(new Stack<Object>()
				, listToCalc
				, Arrays.asList("+", "-", "*", "/"));
		for(String result : resultOutput) {
			System.out.println(result);
		}
	}
	
	public static void calc24(Stack<Object>calcList, List<Double>numList, List<String>operations){
		
		if(!checkOper(calcList)) {
			return;
		}
		
		if(numList.size()==0 && isExpOKay(calcList)) {
			if(calcReversePolish(calcList) == 24) {
				resultOutput.add(convertToInfixNotation(calcList, operations));
			}
			return;
		}
		
		List<Double> newNumList = new ArrayList<Double>();
		for(Double num : numList) {
			newNumList.clear();
			newNumList.addAll(numList);
			calcList.push(num);
			newNumList.remove(num);
			calc24(calcList, newNumList, operations);
			calcList.pop();
		}
		
		for(String oper : operations) {
			calcList.push(oper);
			calc24(calcList, numList, operations);
			calcList.pop();
		}
		return;
		
	}
	
	/**
	 * �ݩ`����Ӌ��C
	 * @param calcList
	 * @return
	 */
	public static Double calcReversePolish(Stack<Object>calcList) {
		Double num1, num2;
		Stack<Object> calcStack = new Stack<Object>();
		for(Object element:calcList) {
			if("*".equals(element)){
				num1 = (Double)calcStack.pop();
				num2 = (Double)calcStack.pop();
				calcStack.push(num1 * num2);
			}
			else if("/".equals(element)){
				num1 = (Double)calcStack.pop();
				num2 = (Double)calcStack.pop();
				calcStack.push(num1 / num2);
			}
			else if("+".equals(element)){
				num1 = (Double)calcStack.pop();
				num2 = (Double)calcStack.pop();
				calcStack.push(num1 + num2);
			}
			else if("-".equals(element)){
				num1 = (Double)calcStack.pop();
				num2 = (Double)calcStack.pop();
				calcStack.push(num1 - num2);
			}
			else {
				calcStack.push(element);
			}
		}
		
		return (Double)calcStack.peek();
	}

	/**
	 * ��ݩ`���ɤ�������ʽ�ˉ�Q���롣
	 * @param calcList
	 * @param operations
	 * @return
	 */
	public static String convertToInfixNotation(Stack<Object>calcList, List<String>operations) {
		Stack<String>calcStack = new Stack<String>();
		//���ä�Ӌ���a�������å�
		Stack<String>operStack = new Stack<String>();
		for(Object element:calcList) {
			if(operations.contains(element)) {
				Object num1 = calcStack.pop();
				Object num2 = calcStack.pop();
				
				if(operStack.size() >= 2) {
					String oper1 = operStack.pop();
					String oper2 = operStack.pop();
					//���֤Έ��ϡ����ä���Ҫ��ʤ�
					//Ӌ����ţ��΃��ȶȤ���η��Ť򳬤��ʤ���С����ä�����Ҫ
					if(oper1 != "N" && !isPriorityBigger(oper1, element.toString())) {
						num1 = String.format("( %s )", num1);
					}
					//���֤Έ��ϡ����ä���Ҫ��ʤ�
					//Ӌ����ţ��΃��ȶȤ���η��Ť��ͤ����ϡ����ä���Ҫ
					if(oper2 != "N" && isPriorityBigger(element.toString(), oper2)) {
						num2 = String.format("( %s )", num2);
					}
				}
				//һ�rӋ��Y���򥹥��å��ˑ���
				calcStack.push(String.format(" %s %s %s "
						, num1, element.toString(), num2));
				
				operStack.push(element.toString());
			} else {
				calcStack.push(element.toString());
				operStack.push("N");
			}
		}
		return calcStack.peek();
	}
	
	
	/**
	 * Ӌ����Ť�ǰ��ʮ�֤����֤����뤫�ɤ���������å�����
	 * @param calcList
	 * @return
	 */
	public static boolean checkOper(Stack<Object>calcList) {
		Integer operCnt = 0;
		for(int i=0; i<calcList.size(); i++) {
			if(calcList.get(i) instanceof String) {
				operCnt++;
			}
			if(operCnt * 2 > i ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Ӌ����Ť��������֤������һ���त�������å����롣
	 * @param calcList
	 * @return
	 */
	public static boolean isExpOKay(Stack<Object>calcList) {
		Integer numCnt = 0;
		Integer operCnt = 0;
		for(Object o : calcList) {
			if(o instanceof String) {
				operCnt++;
			} else {
				numCnt++;
			}
		}
		return numCnt - operCnt <= 1;
	}

	/**
	 * �����λ�����ֻ�����
	 */
	public static Integer getOperPriority(String oper) {
		if("*/".contains(oper)) {
			return 2;
		} else if("+-".contains(oper)) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * ���Ť΃����λ����^���롣
	 * @param oper1
	 * @param oper2
	 * @return
	 */
	public static boolean isPriorityBigger(String oper1, String oper2) {
		Integer pri1 = getOperPriority(oper1);
		Integer pri2 = getOperPriority(oper2);
		//�����λ�θߤ��ۤ����٤�
		if(pri1 > pri2) {
			return true;
		} else if(pri1 == pri2) {
			//�����λ��һ�¡�һ��Ŀ��Ӌ����Ť�����㡢�p��Έ��ϡ�һ��Ŀ���٤�
			if("/-".contains(oper1)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
