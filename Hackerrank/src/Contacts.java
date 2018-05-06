import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Contacts {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
    	Map<String, Integer> dictionary = new HashMap<String, Integer>();
    	
//    	System.out.println("sssss".subSequence(0, 5));
    	
        int queriesRows = scanner.nextInt();
        while(queriesRows-- > 0) {
        	String query = scanner.next();
    		if(query.charAt(0) == 'a') {
    			String str = scanner.next();
    			for(int i=0; i<=str.length(); i++) {
					String subStr = (String) str.subSequence(0, i);
					Integer cnt = dictionary.get(subStr);
    				if(cnt == null) {
    					cnt = 0;
    				}
    				dictionary.put(subStr, cnt + 1);
    			}
    			
    		} else if(query.charAt(0) == 'f') {
    			String str = scanner.next();
    			Integer result = dictionary.get(str);
    			if(result == null)
    				result = 0;
    			System.out.println(result);
    		}
        }
    }
}
