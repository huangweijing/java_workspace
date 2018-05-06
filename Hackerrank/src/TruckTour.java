import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class TruckTour {

	static class PetrolPump {
		public int petrol;
		public int distance;
		public PetrolPump(int petrol, int distance) {
			this.petrol = petrol;
			this.distance = distance;
		}
	}
	
    private static Scanner scanner = new Scanner(System.in);
    
	public static void main(String[] args) {
		Queue<PetrolPump> pumpQueue = new LinkedList<>();
		
		int n = scanner.nextInt();
		for(int i=0; i<n; i++) {
			int petrol = scanner.nextInt();
			int distance = scanner.nextInt();
			pumpQueue.add(new PetrolPump(petrol, distance));
		}
		
		int startStation = 0;
		while(startStation < n) {
			long capacity = 0;
			boolean isOkay = true;
			for(PetrolPump pump : pumpQueue) {
				capacity = capacity + pump.petrol;
				if(capacity < pump.distance) {
					isOkay = false;
					break;
				}
				capacity = capacity - pump.distance;
			}
			pumpQueue.add(pumpQueue.poll());
			if(isOkay)
				break;
			startStation++;
		}
		System.out.println(startStation);

	}

}
