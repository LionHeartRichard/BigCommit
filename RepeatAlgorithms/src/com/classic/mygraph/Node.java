package com.classic.mygraph;

import java.util.Comparator;
import java.util.Objects;

class Node<T> implements Comparator<Node<T>> {

	public Node() {
	}

	public Node(T node, int price) {
		this.node = node;
		this.price = price;
	}

	private T node;
	private int price;

	@Override
	public int compare(Node<T> n1, Node<T> n2) {
		if (n1.price < n2.price)
			return -1;
		if (n1.price > n2.price)
			return 1;
		return 0;
	}

	public T getNode() {
		return node;
	}

	public void setNode(T node) {
		this.node = node;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
