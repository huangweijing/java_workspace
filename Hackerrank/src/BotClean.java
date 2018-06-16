import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BotClean {

    static void next_move(int posr, int posc, String[] board){
    	int nextr = -1;
    	int nextc = -1;
    	int minDistance = board.length + board.length;
    	for(int i=0; i<board.length; i++) {
    		String line = board[i];
    		for(int j=0; j<line.length(); j++) {
    			if(line.charAt(j) == 'd') {
    				int distance = Math.abs(i - posr) + Math.abs(j - posc);
    				if(distance == 0) {
    					System.out.println("CLEAN");
    					return;
    				}
    				if(distance < minDistance) {
    					nextr = i;
        				nextc = j;
        				minDistance = distance;
    				}
    			}
    		}
    	}
    	
    	if(posc < nextc) {
    		System.out.println("RIGHT");
    	} else if(posc > nextc) {
    		System.out.println("LEFT");
    	} else if(posr < nextr) {
    		System.out.println("DOWN");
    	} else if(posr > nextr) {
    		System.out.println("UP");
    	}
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int [] pos = new int[2];
        String board[] = new String[5];
        for(int i=0;i<2;i++) pos[i] = in.nextInt();
        for(int i=0;i<5;i++) board[i] = in.next();
        next_move(pos[0], pos[1], board);
    }
}
