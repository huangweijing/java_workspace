package com.weijinglab.mycollections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {
	

	public static void main(String[] args) {
		List<Integer> myList = new MyLinkedList<>(); 
		myList.add(1);
		myList.add(7);
		myList.add(9);
		System.out.println("the size of myList is " + myList.size());
		System.out.println("print out myList:" + myList);
		
		System.out.println("the element on index 1 is " + myList.get(1));
		
		myList.remove(1);
		System.out.println("the size after index 1 deleted: " + myList.size());
		System.out.println("print out myList:" + myList);
		
		
		myList.clear();
		System.out.println("print out myList after being cleared:" + myList);

	}
	
	
	private Integer size;
	private Node<E> firstNode = null;

	class Node<E> {
		public Node(E data, Node<E>next) {
			this.setData(data);
			this.setNext(next);
		}
		public Node() {
			
		}
		public E getData() {
			return data;
		}
		public void setData(E data) {
			this.data = data;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		private E data;
		private Node<E> next;
	}
	
	public MyLinkedList() {
		this.size = 0;
	}

	@Override
	public boolean add(E e) {
		Node<E> lastNode = firstNode;
		Node<E> newNode = new Node<E>(e, null);
		if(lastNode == null) {
			firstNode = newNode;
		} else {
			while(lastNode.getNext() != null) {
				lastNode = lastNode.getNext();
			}
			lastNode.setNext(newNode);
		}
		this.size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		this.firstNode = null;
		this.size = 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		if(index > this.size - 1) {
			return null;
		}
		Node<E> currentNode = this.firstNode;
		for(int i=1; i<=index; i++) {
			currentNode = currentNode.getNext();
		}
		return currentNode.getData();
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int index) {
		if(index > this.size - 1) {
			return null;
		}
		Node<E> currentNode = this.firstNode;
		Node<E> previousNode = null;
		for(int i=0; i<=index; i++) {
			previousNode = currentNode;
			currentNode = currentNode.getNext();
		}
		previousNode.setNext(currentNode.next);
		this.size--;
		return currentNode.getData();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		String str = "[";
		Node<E> currentNode = firstNode;
		while(currentNode!=null) {
			if(currentNode != firstNode) {
				str += ", " ;
			}
			str +=  currentNode.getData().toString();
			currentNode = currentNode.getNext();
		}
		return str + "]";
	}

}
