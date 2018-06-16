import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class CyclicalQueries {
	
	static class StackTree {
		public List<Stack<Long>> stackList;
		//weight of each stack
		public List<Long>stackWeight;
		//the maximum weight of this tree
		public Long maxWeight = 0l;
		//index having the maximum weight
		public int maxWeightIdx = -1;
		
		public StackTree() {
			stackList = new ArrayList<Stack<Long>>();
			stackWeight = new ArrayList<Long>();
		}
		public void addNewStack(Long weight) {
			Stack<Long> newStack = new Stack<Long>();
			newStack.push(weight);
			stackList.add(newStack);
			stackWeight.add(weight);
			if(weight > maxWeight) {
				maxWeight = weight;
				maxWeightIdx = stackList.size() - 1;
			}
		}
		
		public void addToLongest(Long weight) {
			if(maxWeightIdx == -1) {
				addNewStack(weight);
				return;
			}
			stackList.get(maxWeightIdx).add(weight);
			stackWeight.set(maxWeightIdx, stackWeight.get(maxWeightIdx) + weight);
			maxWeight += weight;
		}
		
		public Long removeLongest() {
			Stack<Long> stack = stackList.get(maxWeightIdx);
			Long weight = stack.pop();
			stackWeight.set(maxWeightIdx, stackWeight.get(maxWeightIdx) - weight);
			maxWeight = 0l;
			maxWeightIdx = -1;
			for(int i=0; i<stackWeight.size(); i++) {
				if(maxWeight < stackWeight.get(i)) {
					maxWeight = stackWeight.get(i);
					maxWeightIdx = i;
				}
			}
			return weight;
		}
	}
	
	public static int getFarthestIdx(StackTree[] trees,long total, long[] weightFromFirst, int from) {
		int idx = 1;
		long maxLength = 0;
		for(int i=1; i<weightFromFirst.length; i++) {
			long distance = calcDistance(from , i, total, weightFromFirst) + trees[i].maxWeight;
			if(distance > maxLength) {
				maxLength = distance;
				idx = i;
			}
		}
		return idx;
	}
	
	public static long calcDistance(int from , int to, long total, long[] weightFromFirst) {
		if(from <= to) {
			return weightFromFirst[to] - weightFromFirst[from]; 
		} else {
			return total - (weightFromFirst[from] - weightFromFirst[to]);
		}
	}

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		
		StringBuffer resultBuffer = new StringBuffer();
		
		int n = scanner.nextInt();
		long[] weightFromFirst = new long[n+1];
		long totalDistance = 0l;
		StackTree[] trees = new StackTree[n+1];
		for(int i=1; i<=n; i++) {
			long w = scanner.nextLong();
			trees[i] = new StackTree();
			if(i != n)
				weightFromFirst[i + 1] = weightFromFirst[i] + w;
			else
				totalDistance = weightFromFirst[i] + w;
		}
		
		int m = scanner.nextInt();
		for(int i=0; i<m; i++) {
			int query = scanner.nextInt();
			int x = scanner.nextInt();
			long w, result; int idx;
//			System.out.println("farthest:" + getFarthestIdx(trees, totalDistance ,weightFromFirst, x));
			
			switch(query) {
			case 1:
				idx = getFarthestIdx(trees, totalDistance, weightFromFirst, x);
				w = scanner.nextLong();
				trees[idx].addToLongest(w);
				break;
			case 2:
				w = scanner.nextLong();
				trees[x].addNewStack(w);
				break;
			case 3:
				idx = getFarthestIdx(trees, totalDistance, weightFromFirst, x);
				trees[idx].removeLongest();
				break;
			case 4:
				idx = getFarthestIdx(trees, totalDistance, weightFromFirst, x);
				result = trees[idx].maxWeight + calcDistance(x, idx, totalDistance, weightFromFirst);
				resultBuffer.append(result);
				resultBuffer.append("\n");
				break;
			}
		}
		
		System.out.println(resultBuffer);

	}

}
