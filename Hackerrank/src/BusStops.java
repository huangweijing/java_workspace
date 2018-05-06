import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class BusStops {
	
	static class Bus {
		public int speed;
		public int position;
	}
	
	static class Person {
		public int speed;
		public int position;
	}

    // Complete the minimumTimeToEnd function below.
    static void minimumTimeToEnd(long[] x, long w, long v, int q) {
        // Take the descriptions of the people from standard input and print the answers to standard output
    	long finalStop = x[x.length-1];
    	double[] stopTakeTimeToFinal = new double[x.length];
    	double[] stopTimeTable = new double[x.length];
    	for(int i=0; i<x.length; i++) {
    		long stopPos = x[i];
    		stopTakeTimeToFinal[i] = (finalStop - stopPos) / (double)v;
    		stopTimeTable[i] = stopPos / (double)v;
    	}
    	
    	while(q-- > 0) {
    		long p = scanner.nextLong();
    		double t = scanner.nextDouble();
    		long u = scanner.nextLong();
    		
    		if(u >= v) {
    			System.out.println((finalStop - p) / (double)u + t);
    			continue;
    		}
    		
    		int nIdx = getNearestBusStopBef(x, p);
    		double timeToNextStop = (x[nIdx+1] - p) / (double)u;
    		double timeToBefStop = (p - x[nIdx]) / (double)u;
    		
    		double time1 = getTimeTillBusCome(stopTimeTable[nIdx], timeToBefStop + t, w);
    		double time2 = getTimeTillBusCome(stopTimeTable[nIdx + 1], timeToNextStop + t, w);

//    		System.out.println("nIdx" + nIdx);
//    		System.out.println("stopTimeTable[nIdx]:" + stopTimeTable[nIdx]);
//    		System.out.println("stopTimeTable[nIdx + 1]:" + stopTimeTable[nIdx + 1]);
//    		System.out.println("timeToNextStop:" + timeToNextStop);
//    		System.out.println("timeToBefStop:" + timeToBefStop);
//    		System.out.println("time1:" + time1);
//    		System.out.println("time2:" + time2);
    		
    		double totalTimeFromBef = time1 + stopTakeTimeToFinal[nIdx];
    		double totalTimeFromNext = time2 + stopTakeTimeToFinal[nIdx+1];
    		double totalTimeIfWalk = (finalStop - p) / (double)u + t;
    		double minTime = Math.min(Math.min(totalTimeFromBef, totalTimeFromNext), totalTimeIfWalk);
    		System.out.println(minTime);
    	}

    }
    
    static double getTimeTillBusCome(double firstTimeBusCome, double currentTime, double w) {
    	if(currentTime < firstTimeBusCome) {
    		return firstTimeBusCome;
    	} else {
    		double value = w - (currentTime - firstTimeBusCome) % w + currentTime;
    		if(value == w)
    			return currentTime;
    		else
    			return value;
//    		return w - ((currentTime - firstTimeBusCome) % w);
    	}
//    	if(currentTime < firstTimeBusCome) {
//    		return firstTimeBusCome - currentTime;
//    	} else {
//    		return w - ((currentTime - firstTimeBusCome) % w);
//    	}
    }
    
    static int getNearestBusStopBef(long[]x, long p) {
//    	int currentPos = x.length / 2;
//    	while(!(x[currentPos] <= p && x[currentPos+1] >= p)) {
//    		currentPos = currentPos
//    	}
    	int idx = 0;
    	for(int i=0; i < x.length -1 ; i++) {
    		if(p >= x[i] && p <= x[i+1]) {
    			idx = i;
    			break;
    		}
    	}
    	return idx;
    }
    
    static int timeForNextBus(long[] x, long w, long v, int q) {
    	
    	return 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] x = new long[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int xItr = 0; xItr < n; xItr++) {
            long xItem = Long.parseLong(xItems[xItr]);
            x[xItr] = xItem;
        }

        String[] wv = scanner.nextLine().split(" ");

        long w = Long.parseLong(wv[0]);

        long v = Long.parseLong(wv[1]);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        minimumTimeToEnd(x, w, v, q);

        scanner.close();
    }
}
