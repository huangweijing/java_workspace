package com.weijinglab.lib.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable<E>> {

	public static void main(String[] args) {
		BinarySearchTree<Integer> binaryTree = new BinarySearchTree<Integer>();
		Integer[] intArray = new Integer[]{47,2,40,20,38,30,14,28,10,16,19,44,39,27,7,9,31,12,43,21,5,41,34,49,13,33,3,4,25,22,29,15,32,35,6,24,23,26,1,11,42,36,37,17,18,8,45,48,50,46};
		for(Integer someInt : intArray) {
			binaryTree.insert(someInt);
		}
//		binaryTree.print(-1);
//		System.out.println();
//		binaryTree.print(0);
//		System.out.println();
//		binaryTree.print(1);
//		System.out.println();
		
//		PrintGraphic.printGraphic(binaryTree.root);
//		topView(binaryTree.root);
		printLevelOrderTraversal(binaryTree.root);
		
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
	
	/**
	 * -1: preorder
	 * 0: inorder
	 * 1: postorder
	 */
	public void print(int type) {
		switch(type) {
		case -1:
			this.preOrder(this.root);
			break;
		case 0:
			this.inOrder(this.root);
			break;
		case 1:
			this.postOrder(this.root);
			break;
		}
		
	}
	public void preOrder(TreeNode<E> node) {
		System.out.print(node.data + " ");
		if(node.left != null) {
			preOrder(node.left);
		}
		if(node.right != null) {
			preOrder(node.right);
		}
	}
	
	public void postOrder(TreeNode<E> node) {
		System.out.print(node.data + " ");
		if(node.left != null) {
			postOrder(node.left);
		}
		if(node.right != null) {
			postOrder(node.right);
		}
	}
	

	public void inOrder(TreeNode<E> node) {
		if(node.left != null) {
			inOrder(node.left);
		}
		System.out.print(node.data + " ");
		if(node.right != null) {
			inOrder(node.right);
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
	
	public static void printLevelOrderTraversal(TreeNode<Integer> root) {
		Queue<TreeNode<Integer>> queue = new LinkedList<TreeNode<Integer>>();
		queue.add(root);
		levelOrderTraversal(queue);
	}
	
	public static void levelOrderTraversal(Queue<TreeNode<Integer>> queue) {
		if(queue.size() == 0) {
			return;
		}
		Queue<TreeNode<Integer>> nextLevel = new LinkedList<TreeNode<Integer>>();
		for(TreeNode<Integer> node : queue) {
			if(node.left != null) {
				nextLevel.add(node.left);
			}
			if(node.right != null) {
				nextLevel.add(node.right);
			}
			System.out.print(node.data + " ");
		}
		levelOrderTraversal(nextLevel);
	}

    public static void getVerticalOrder(TreeNode<Integer> root, Integer pos, Map<Integer, List<Integer>> verticalMap) {
        if(root == null) {
            return;
        }
        List<Integer> verticalList = verticalMap.get(pos);
        if(verticalList == null) {
            verticalList = new ArrayList<Integer>();
            verticalMap.put(pos, verticalList);
        }
        verticalList.add(root.data);
        
        getVerticalOrder(root.left, pos - 1, verticalMap);
        getVerticalOrder(root.right, pos + 1, verticalMap);
    }

    public static void topView(TreeNode<Integer> root) {
 	      
        Map<Integer, List<Integer>> verticalMap = new HashMap<Integer, List<Integer>>();
        getVerticalOrder(root, 0, verticalMap); 
        List<Integer> newList = new ArrayList<Integer>(verticalMap.keySet());
        Collections.sort(newList);
        for(Integer i : newList) {
            System.out.print(verticalMap.get(i).get(0) + " ");
        }
     }
}
