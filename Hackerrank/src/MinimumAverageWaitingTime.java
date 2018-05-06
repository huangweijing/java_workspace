import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class MinimumAverageWaitingTime {

	static class Customer {
		public int orderTime;
		public int timeToMakePizza;
		public Customer(int orderTime, int timeToMakePizza) {
			this.orderTime = orderTime;
			this.timeToMakePizza = timeToMakePizza;
		}
		public String toString() {
			return String.format("{ordertime=%s, pizza=%s}"
					, this.orderTime, this.timeToMakePizza);
		}
	}
    /*
     * Complete the minimumAverage function below.
     */
    static long minimumAverage(int[][] customers) {
        /*
         * Write your code here.
         */
    	PriorityQueue<Customer> customerByTime = new PriorityQueue<>(
    			100000, new Comparator<Customer>() {
					@Override
					public int compare(Customer o1, Customer o2) {
						return o1.orderTime - o2.orderTime;
					}
				}); 
    	PriorityQueue<Customer> customerAtResturant = new PriorityQueue<>(
    			100000, new Comparator<Customer>() {
					@Override
					public int compare(Customer o1, Customer o2) {
						return o1.timeToMakePizza - o2.timeToMakePizza;
					}
				}); 
    	for(int[] customer : customers) {
    		customerByTime.add(new Customer(customer[0], customer[1]));
    	}
//    	System.out.println(customerByTime);
    	
    	long waitingTime = 0;
    	long currentTime = 0;
    	if(!customerByTime.isEmpty())
    		currentTime = customerByTime.peek().orderTime;
    	while(!customerByTime.isEmpty() || !customerAtResturant.isEmpty()) {
    		while(!customerByTime.isEmpty() && customerByTime.peek().orderTime <= currentTime) {
    			customerAtResturant.add(customerByTime.poll());
    		}
    		if(customerAtResturant.isEmpty()) {
    			currentTime = customerByTime.peek().orderTime;
    			continue;
    		}
    		Customer fastestCustomer = customerAtResturant.poll();
    		currentTime += fastestCustomer.timeToMakePizza;
    		waitingTime += currentTime - fastestCustomer.orderTime;
    	}
    	
    	return waitingTime / customers.length;
    	
//    	int max = 1000000000;
//    	for(int i=0; i<max; i++) {
//    		
//    	}
    	
    }

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//    	minimumAverage(null);
//    	System.out.println("end");
//    	System.exit(0);
    	
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int[][] customers = new int[n][2];

        for (int customersRowItr = 0; customersRowItr < n; customersRowItr++) {
        	
        	customers[customersRowItr][0] = scanner.nextInt();
        	customers[customersRowItr][1] = scanner.nextInt();
        }

        long result = minimumAverage(customers);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
