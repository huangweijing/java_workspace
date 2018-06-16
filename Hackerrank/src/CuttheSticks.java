import java.util.PriorityQueue;
import java.util.Scanner;


public class CuttheSticks {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		
		int n = scanner.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<n; i++) {
			pq.add(scanner.nextInt());
		}
		while(pq.size() > 0) {
			System.out.println(pq.size());
			int head = pq.poll();
			while(pq.size() > 0 && pq.peek() == head) pq.poll();
		}
	}
}
