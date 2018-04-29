package homework;

public class TrappingRainWater {

	public static void main(String[] args) {
				
		Integer[] intArray1 = new Integer[]{0,1,0,2,1,0,1,3,2,1,2,1};
		Integer[] intArray2 = new Integer[]{2,1,5,4,5,6,3,4};
		Integer[] intArray3 = new Integer[]{0,8,0,1,4};
		Integer[] intArray4 = new Integer[]{4,-2,8,0,1};
		Integer[] intArray5 = new Integer[]{1,0,7,0,1};
		Integer[] intArray6 = new Integer[]{0,1,3,2,1};
		Integer[] intArray7 = new Integer[]{0,1,-1,-2,1};
		Integer[] intArray8 = new Integer[]{0,1,-1,-2,1};
		System.out.println(solution(intArray1));//6
		System.out.println(solution(intArray2));//1
		System.out.println(solution(intArray3));//7
		System.out.println(solution(intArray4));//7
		System.out.println(solution(intArray5));//2
		System.out.println(solution(intArray6));//0
		System.out.println(solution(intArray7));//5
		System.out.println(solution(intArray8));//5
	}
	
	public static Integer solution(Integer[] intArray) {
		Integer result = 0; 
		Integer hightsOfBlocks = 0; //���֥��å��θߤ����ĤޤꡢintArray[i]�΂���
		//���¤�intArray��index���ʾ���Ƥ�����Τ����뤿�ᡢintArray��ͬ���L����Integer��Array��
		Integer index = 0;
		Integer[] indexArray = new Integer[intArray.length];
		for(int i=0; i<intArray.length; i++) {
			indexArray[i] = i;
		}
		//���Ϥ�intArray��index���ʾ���Ƥ�����Τ����뤿�ᡢintArray��ͬ���L����Integer��Array��
		Integer indexOfMax1 =indexOfMax(intArray);//indexOfMax�᥽�`�ɤ�ʹ�äơ�intArray���Ф�max��index����`�󤹤룻
		if(indexOfMax1!=0) { //intArray[0]��max���ɤ������жϤ��룻max����ʤ����ϡ�max�����Ƥ���ޤǡ��Ҥ��Է֤��ͤ����Ϥϡ����ä��Ҥ�Ҋ�Ƥ�����
			Integer leftBlock = intArray[0];
			for(int i=1; i<intArray.length; i++) {
				if(intArray[i] > leftBlock) {
					result = result + (i-index-1)*leftBlock-hightsOfBlocks;//�Ҥ��Է֤��ߤ����ϡ��Ĥޤꡢˮ���A�����Ȥ������ȡ��ʤΤǤɤ�����A�ޤ뤫�������
					index = i;
					hightsOfBlocks = 0;
					if(indexOfMax1==i) {
						break;//������max�����Ƥ������ᡢ���줫����Ҥ�ȫ������max���ͤ����ᡢ�I���������룻�ʤΤǡ�if�Ĥ���ơ��Τ�while��`�פ���룻
					}else {
						leftBlock = intArray[i];
					}
				}else {
					hightsOfBlocks = hightsOfBlocks+intArray[i];
				}
			}
		}
		//��������ϡ�max���҂ȤˤĤ��ƄI�����룻
		while(index<intArray.length-1) {	
			//�ޤ��Ϥ��β��֣��¤���Array�����äơ�current��max���҂Ȥ�ȫ����������룻Ŀ�Ĥϡ������Ф���һ���ߤ��֥��å����Τ�max����̽�����ȣ�
			Integer[] subArray = new Integer[(intArray.length-1) - index];
			for(int i=index+1; i<intArray.length; i++) {
				subArray[i-(index+1)] = intArray[i];
			}
			//���Ϥǡ��¤���Array�����äơ�current��max���҂Ȥ�ȫ���������줿��
			Integer indexOfMax2 = indexOfMax(subArray);//�����Ф���һ���ߤ��֥��å����Τ�max����̽��������
			if(indexOfMax2==0) {//max��index�������Ȥ����Τϡ�ǰ��max�δΤˤ����Τ�max�Ǥ��뤳�ȣ����Έ��ϡ����ζ��ĤΥ֥��å����g��Ҋ����ޤ�ʤ���
				index=index+1;
			}else {//ǰ��max�ȴΤ�max���g�ˡ�ˮ����ޤäƤ���Τǡ������������룻
				for(int i=0; i<indexOfMax2; i++) {
					hightsOfBlocks = hightsOfBlocks+subArray[i];
				}
				result = result +indexOfMax2*subArray[indexOfMax2]-hightsOfBlocks;
				hightsOfBlocks=0;
				index=index+indexOfMax2+1;
			}
		}
		return result;
	}

	public static Integer indexOfMax(Integer[] intArray) {
		Integer max = intArray[0];
		Integer indexOfMax = 0;
		for(int i=1; i<intArray.length; i++) {
			if(intArray[i]>max) {
				max  =  intArray[i];
				indexOfMax = i;
			}
		}
		return indexOfMax;

	}

}