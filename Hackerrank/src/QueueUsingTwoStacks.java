
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueUsingTwoStacks {

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		int n = scanner.nextInt();
		for(int i=0; i<n; i++) {
			int command = scanner.nextInt();
			switch(command) {
			case 1:
				int x = scanner.nextInt();
				queue.add(x);
				break;
			case 2:
				queue.poll();
				break;
			case 3:
				System.out.println(queue.peek());
				break;
			}
		}
	}
}
