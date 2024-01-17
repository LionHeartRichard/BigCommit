package com.myuniversaltree;

import java.util.Comparator;
import java.util.Iterator;

class Node<T> implements Comparator<Node<T>> {

	private T node;
	private double price;

	public Node() {
	}

	public Node(T node, double price) {
		this.node = node;
		this.price = price;
	}

	@Override
	public int compare(Node<T> vertex1, Node<T> vertex2) {
		if (vertex1.price < vertex2.price) {
			return -1;
		}
		if (vertex1.price > vertex2.price) {
			return 1;
		}
		return 0;
	}

	public T getNode() {
		return node;
	}

	public void setNode(T node) {
		this.node = node;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
