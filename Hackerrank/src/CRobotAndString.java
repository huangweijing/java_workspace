import java.util.Scanner;
import java.util.Stack;


public class CRobotAndString {

    public static Scanner scanner = new Scanner(System.in);
    
	public static void main(String[] args) {
		String s = scanner.next();
		int n = scanner.nextInt();
		while(n-- > 0) {
			int l = scanner.nextInt() - 1;
			int r = scanner.nextInt() - 1;
			Stack<Character> stack = new Stack<>();
			for(int i=l; i<=r; i++) {
				pushIntoStack(stack, s.charAt(i));
			}
//			System.out.println(stack);
			if(stack.isEmpty())
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}
	
	public static void pushIntoStack(Stack<Character> stack, char ch) {
		if(!stack.isEmpty() && stack.peek() == ch) {
			char top = stack.pop();
			if(top != 'z')
				pushIntoStack(stack, (char)((int)top+1));
		} else {
			stack.push(ch);
		}
		
	}

}
