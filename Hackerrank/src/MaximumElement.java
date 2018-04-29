import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;


public class MaximumElement {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(100, new Comparator<Integer>(){

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg1 - arg0;
			}
			
		});
		int n = scanner.nextInt();
		for(int i=0; i<n; i++) {
			int command = scanner.nextInt();
			switch(command) {
			case 1:
				int x = scanner.nextInt();
				stack.push(x);
				priorityQueue.add(x);
				break;
			case 2:
				int delInt = stack.pop();
				priorityQueue.remove(delInt);
				break;
			case 3:
				System.out.println(priorityQueue.element());
				break;
			}
		}

	}

}
