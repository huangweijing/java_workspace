import java.util.Scanner;

public class NoPrefixSet {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = scanner.nextInt();
		Trie trie = new Trie();
		scanner.nextLine();
		boolean fail = false;
		while(n-- > 0) {
			String s = scanner.nextLine();
			boolean result = trie.add(s);
			if(!result) {
				fail = true;
				System.out.println("BAD SET");
				System.out.println(s);
				break;
			}
		}
		if(!fail)
			System.out.println("GOOD SET");
	}
	
	public static class Trie {
		public TrieNode root = new TrieNode();
			public boolean add(String str) {
				TrieNode current = root;
				for(int i=0; i<str.length(); i++) {
					char charAt = str.charAt(i);
					if(current.children[charAt - 'a'] == null) {
						current.children[charAt - 'a'] = new TrieNode();
					} else if(current.children[charAt - 'a'].dataFlg || i==str.length() - 1){
						//路上碰到已经加入过的string，或者走到底发现一直在走前人走过的路，失败
						return false;
					}
					current = current.children[charAt - 'a'];
					
				}
				current.dataFlg = true;
				return true;
			}
		
		class TrieNode {
			public TrieNode[] children = new TrieNode[10];
			public boolean dataFlg = false;
		}
	}

}
