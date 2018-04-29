import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BalancedBrackets {

    static String isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
        	if("})]".indexOf(s.charAt(i)) != -1
        			&& stack.isEmpty()) {
        		return "NO";
        	}
        	
            if(']' == s.charAt(i)){
                char topChar = stack.pop();
                if(topChar != '[')
                    return "NO";
            } else if(')' == s.charAt(i)) {
                char topChar = stack.pop();
                if(topChar != '(')
                    return "NO";
            } else if('}' == s.charAt(i)) {
                char topChar = stack.pop();
                if(topChar != '{')
                    return "NO";
            } else {
                stack.push(s.charAt(i));
            }
        }
        if(!stack.isEmpty())
            return "NO";
        return "YES";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            String s = in.next();
            String result = isBalanced(s);
            System.out.println(result);
        }
        in.close();
    }
}
