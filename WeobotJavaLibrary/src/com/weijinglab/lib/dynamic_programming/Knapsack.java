package com.weijinglab.lib.dynamic_programming;
import java.util.Arrays;
import java.util.Scanner;

//��˵��һ����ȥɭ�����淢����һ�ѱ�ʯ������������һ����n���� 
//����������װ��ʯ�ľ�ֻ��һ������������������ΪC������ǰ�n����ʯ�ų�һ�Ų����Ϻţ� 0,1,2,��,n-1��
//��i����ʯ��Ӧ������ͼ�ֵ�ֱ�ΪV[i]��W[i] ��
//�źú�����ǿ�ʼ˼���� �����ܹ�Ҳ��ֻ��װ�����ΪC�Ķ���������Ҫװ����Щ��ʯ�������һ�����������أ�
public class Knapsack {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int n = scanner.nextInt();
		int[][] gem = new int[n][2];
		int c = scanner.nextInt();
		//��¼�ڱ��������̶��µ�����
		int[] solutions = new int[c+1];
		//��¼��ÿ�����±�ʯ��ʹ��״̬
		int[][] solutions_sack_state = new int[c+1][n]; 
		solutions[0] = 0;
		for(int i=0; i<n; i++) {
			int w = scanner.nextInt();
			int v = scanner.nextInt();
			gem[i][0] = v;
			gem[i][1] = w;
		}
		for(int i=0; i<=c; i++) {
			for(int j=0; j<n; j++) {
				//���ñ�ʯ�Ѿ�����������У�����
				if(solutions_sack_state[i][j] == 1)
					continue;
				
				int value = gem[j][0];
				int weight = gem[j][1];
				
				if(i+weight > c)
					continue;
				//�����������ڵ�j�鱦ʯ��Ľ����
				if(solutions[i + weight] < solutions[i] + value) {
					solutions[i + weight] = solutions[i] + value;
					//����i�⿽��������i+weight��
					for(int k=0; k<n; k++) {
						solutions_sack_state[i + weight][k] = solutions_sack_state[i][k];
					}
					//��Ǹý��µı�ʯʹ�����
					solutions_sack_state[i + weight][j] = 1;
				}
			}
			
			//�����뱦ʯ����µĽ����
			if(i + 1 <= c && solutions[i + 1] < solutions[i]) {
				solutions[i + 1] = solutions[i];
				for(int k=0; k<n; k++) {
					solutions_sack_state[i + 1][k] = solutions_sack_state[i][k];
				}
			}
		}
		System.out.println(solutions[c]);
		System.out.println(Arrays.toString(solutions));

		for(int i=0; i<=c; i++) {
			System.out.println(i + ":" + Arrays.toString(solutions_sack_state[i]));
		}
	}
}
