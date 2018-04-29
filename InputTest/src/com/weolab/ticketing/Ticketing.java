package com.weolab.ticketing;

public class Ticketing implements Runnable{
	static int ticketCount=100;
	//String key = "123";
	//String key = new String("123");
	public void run() {
		while(true) {
			//synchronized("key") {	
				if(ticketCount>0) {
					System.out.println(Thread.currentThread().getName() + " is selling the " + (101-ticketCount) +"th ticket.");
					ticketCount--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}else{
					break;	
				}
			//}

		}
	}
	public static void main(String[] args) {
		Ticketing ticketing1 = new Ticketing();
		Ticketing ticketing2 = new Ticketing();
		Thread myThread1 = new Thread(ticketing1);
		Thread myThread2 = new Thread(ticketing2);
		myThread1.start();
		myThread2.start();
	}
}
