package com.classic.mygraph;

import java.util.*;

public class GraphWeight<T> {

	public GraphWeight() {
	}

	public GraphWeight(int numberVertices) {
		this.numberVertices = numberVertices;
		this.pQue = new PriorityQueue<Node<T>>(numberVertices, new Node<T>());
	}

	public GraphWeight(Map<T, LinkedList<Node<T>>> adjacent) {
		this.map = adjacent;
		this.numberVertices = adjacent.size();
		this.pQue = new PriorityQueue<Node<T>>(this.numberVertices,
				new Node<T>());
	}

	private Map<T, LinkedList<Node<T>>> map = new HashMap<T, LinkedList<Node<T>>>();
	private LinkedList<T> cache = new LinkedList<T>();

	private int numberVertices = 0;
	private PriorityQueue<Node<T>> pQue;
	private Set<T> keyVertices = new HashSet<T>();
	private Map<T, Integer> distances = new HashMap<T, Integer>();

	private void addVertex(T vertex) {
		map.put(vertex, new LinkedList<Node<T>>());
	}

	public void addEdge(T vertex, T subVertex, int price) {
		if (!map.containsKey(vertex))
			addVertex(vertex);
		if (!map.containsKey(subVertex))
			addVertex(subVertex);
		map.get(vertex).add(new Node<T>(subVertex, price));
		map.get(subVertex).add(new Node<T>(vertex, price));
	}

	public LinkedList<T> DFS(T source) {
		if (map.containsKey(source)) {
			cache.clear();
			cache.add(source);
			for (Node<T> vertex : map.get(source)) {
				if (!cache.contains(vertex.getNode())) {
					cache.add(vertex.getNode());
					traversalDFS(vertex.getNode());
				}
			}
			return cache;
		}
		return null;
	}

	private void traversalDFS(T vertex) {
		Iterator<Node<T>> node = map.get(vertex).iterator();
		while (node.hasNext()) {
			Node<T> current = node.next();
			if (!cache.contains(current.getNode())) {
				cache.add(current.getNode());
				traversalDFS(current.getNode());
			}
		}
	}

	public LinkedList<T> BFS(T source) {
		if (map.containsKey(source)) {

			LinkedList<T> result = new LinkedList<T>();
			result.add(source);
			LinkedList<T> carry = new LinkedList<T>();

			map.get(source).forEach(node -> carry.add(node.getNode()));
			result.addAll(carry);

			while (!carry.isEmpty()) {
				T currentV = carry.pop();
				Iterator<Node<T>> item = map.get(currentV).iterator();
				while (item.hasNext()) {
					T subV = item.next().getNode();
					if (!result.contains(subV)) {
						result.add(subV);
						carry.add(subV);
					}
				}
			}
			return result;
		}
		return null;
	}

	public Map<T, Integer> dijkstra(T source) {
		if (map.containsKey(source)) {
			distances.clear();

			DFS(source);
			cache.forEach(v -> distances.put(v, Integer.MAX_VALUE));
			distances.put(source, 0);
			pQue.add(new Node<T>(source, 0));

			while (keyVertices.size() != numberVertices) {
				if (pQue.isEmpty())
					break;
				T vertex = pQue.remove().getNode();
				if (keyVertices.contains(vertex))
					continue;
				keyVertices.add(vertex);

				searchNeighbours(vertex);
			}

			return distances;
		}
		return null;
	}

	private void searchNeighbours(T current) {
		int interval = distances.get(current);
		Iterator<Node<T>> node = map.get(current).iterator();
		while (node.hasNext()) {
			Node<T> n = node.next();
			if (!keyVertices.contains(n.getNode())) {
				int newInterval = interval + n.getPrice();
				if (newInterval < distances.get(n.getNode()))
					distances.put(n.getNode(), newInterval);
				pQue.add(new Node<T>(n.getNode(), distances.get(n.getNode())));
			}
		}
	}
}
