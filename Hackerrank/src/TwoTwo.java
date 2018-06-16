import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TwoTwo {

	static class Trie {
		public TrieNode root = new TrieNode();
		public void add(String str) {
			TrieNode current = root;
			for(int i=0; i<str.length(); i++) {
				char charAt = str.charAt(i);
				if(current.children[charAt - '0'] == null) {
					current.children[charAt - '0'] = new TrieNode();
				}
				current = current.children[charAt - '0'];
			}
			current.dataFlg = true;
		}
	}
	
	static class TrieNode {
		public TrieNode[] children = new TrieNode[10];
		public boolean dataFlg = false;
	}

	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Trie dic = new Trie(); 
		List<String> biResult = new ArrayList<>();
		BigInteger bi = BigInteger.valueOf(2L);
		
		for(int i=0; i<=800; i++) {
			BigInteger newInt = bi.pow(i);
			String str = newInt.toString();
			biResult.add(str);
			dic.add(str);
		}
		int t = scanner.nextInt();
		
		while(t-- >0) {
			String input = scanner.next();
			int count = 0;
			for(int i=0; i<input.length(); i++) {
				count += getCount(input, i, dic);
			}
			System.out.println(count);
		}
	}
	
	public static int getCount(String str, int startIndex, Trie dic) {
		int count = 0;
		TrieNode current = dic.root;
		for(int i=startIndex; i<str.length(); i++) {
			char ch = str.charAt(i);
			if(current.children[ch - '0'] == null)
				break;
			else {
				current = current.children[ch - '0'];
				if(current.dataFlg)
					count++;
			}
		}
		return count;
	}
	
	public static int getCount(String str, int startIndex, List<String>dic) {
		int count = 0;
		for(String word:dic){
			if(word.length() + startIndex > str.length())
				return count;
			boolean ok = true;
			for(int i=0; i<word.length(); i++) {
				if(word.charAt(i) != str.charAt(i+startIndex)) {
					ok = false;
					break;
				}
			}
			if(ok == true)
				count++;
		}
		return count;
	}

}
