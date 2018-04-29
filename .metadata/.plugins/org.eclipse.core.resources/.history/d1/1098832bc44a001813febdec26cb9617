package com.weijinglab.binarytree;


public class BinarySearchTree<E extends Comparable<E>> {

	public static void main(String[] args) {
		BinarySearchTree<Integer> binaryTree = new BinarySearchTree<Integer>();
		Integer[] intArray = new Integer[]{ 4, 2, 1, 8, 0, 41, 8, 12, 11, 31};
		for(Integer someInt : intArray) {
			binaryTree.insert(someInt);
		}
		binaryTree.print();
	}
	
	public TreeNode<E> root = null;
	
	public BinarySearchTree(TreeNode<E> root) {
		this.root = root;
	}
	
	public BinarySearchTree() {
		this.root = null;
	}
	
	public void insert(E data) {
		if(root == null) {
			root = new TreeNode<E>(data);
		} else {
			this.insert(root, data);
		}
	}
	
	public void insert(TreeNode<E> node, E data) {
		if(data.compareTo(node.data) < 0) {
			if(node.left != null) {
				insert(node.left, data);
			} else {
				node.left = new TreeNode<E>(data);
			}
		}else {
			if(node.right != null) {
				insert(node.right, data);
			} else {
				node.right = new TreeNode<E>(data);
			}
		}
	}
	
	public void print() {
		this.print(this.root);
	}
	

	public void print(TreeNode<E> node) {
		if(node.left != null) {
			print(node.left);
		}
		System.out.print(node.data + " ");
		if(node.right != null) {
			print(node.right);
		}
			
	}
	
	static class TreeNode<E> {
		public E data = null;
		public TreeNode<E>left = null;
		public TreeNode<E>right = null;
		public TreeNode(E data) {
			this.data = data;
		}
	}


}
