package com.weijinglab.lib.tree;

public class Trie {
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
	
	class TrieNode {
		public TrieNode[] children = new TrieNode[10];
		public boolean dataFlg = false;
	}
}