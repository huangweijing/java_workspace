package com.weijinglab.lib.dynamic_programming;
import java.util.Arrays;
import java.util.Scanner;

//话说有一哥们去森林里玩发现了一堆宝石，他数了数，一共有n个。 
//但他身上能装宝石的就只有一个背包，背包的容量为C。这哥们把n个宝石排成一排并编上号： 0,1,2,…,n-1。
//第i个宝石对应的体积和价值分别为V[i]和W[i] 。
//排好后这哥们开始思考： 背包总共也就只能装下体积为C的东西，那我要装下哪些宝石才能让我获得最大的利益呢？
public class Knapsack {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int n = scanner.nextInt();
		int[][] gem = new int[n][2];
		int c = scanner.nextInt();
		//记录在背包容量固定下的最大解
		int[] solutions = new int[c+1];
		//记录在每个解下宝石的使用状态
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
				//若该宝石已经放入过背包中，跳过
				if(solutions_sack_state[i][j] == 1)
					continue;
				
				int value = gem[j][0];
				int weight = gem[j][1];
				
				if(i+weight > c)
					continue;
				//往背包里放入第第j块宝石后的解更新
				if(solutions[i + weight] < solutions[i] + value) {
					solutions[i + weight] = solutions[i] + value;
					//将第i解拷贝后放入第i+weight处
					for(int k=0; k<n; k++) {
						solutions_sack_state[i + weight][k] = solutions_sack_state[i][k];
					}
					//标记该解下的宝石使用情况
					solutions_sack_state[i + weight][j] = 1;
				}
			}
			
			//不放入宝石情况下的解更新
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
