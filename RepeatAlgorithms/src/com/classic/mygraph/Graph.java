package com.classic.mygraph;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class Graph<T> {

	public Graph() {
	}

	public Graph(Map<T, LinkedList<T>> adjacent) {
		map.clear();
		this.map = adjacent;
	}

	private Map<T, LinkedList<T>> map = new HashMap<T, LinkedList<T>>();
	private LinkedList<T> cacheDFS = new LinkedList<T>();

	private void addVertices(T vertices) {
		map.put(vertices, new LinkedList<T>());
	}

	public void addEdge(T source, T subVertices, boolean bidirectional) {
		if (!map.containsKey(source))
			addVertices(source);
		if (!map.containsKey(subVertices))
			addVertices(subVertices);
		map.get(source).add(subVertices);
		if (bidirectional)
			map.get(subVertices).add(source);
	}

	public LinkedList<T> BFS(T source) {
		if (map.containsKey(source)) {
			LinkedList<T> result = new LinkedList<T>();
			result.add(source);
			LinkedList<T> carry = map.get(source);
			result.addAll(carry);
			while (!carry.isEmpty()) {
				T currentV = carry.pop();
				Iterator<T> item = map.get(currentV).iterator();
				while (item.hasNext()) {
					T subV = item.next();
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

	public LinkedList<T> DFS(T source) {
		if (map.containsKey(source)) {
			cacheDFS.clear();
			cacheDFS.add(source);
			for (T vertice : map.get(source)) {
				if (!cacheDFS.contains(vertice)) {
					cacheDFS.add(vertice);
					traversalDFS(vertice);
				}
			}
			return cacheDFS;
		}
		return null;
	}

	private void traversalDFS(T vertex) {
		Iterator<T> item = map.get(vertex).iterator();
		while (item.hasNext()) {
			T current = item.next();
			if (!cacheDFS.contains(current)) {
				cacheDFS.add(current);
				traversalDFS(current);
			}
		}
	}

	@Test
	public void test1DFS() {

		Graph<String> adjacent = new Graph<String>();
		adjacent.addEdge("A", "A-11", false);
		adjacent.addEdge("A", "A-12", false);
		adjacent.addEdge("A", "A-13", false);

		adjacent.addEdge("A-11", "A-21", false);
		adjacent.addEdge("A-12", "A-22", false);
		adjacent.addEdge("A-13", "A-23", false);

		adjacent.addEdge("A-21", "A-31", false);
		adjacent.addEdge("A-22", "A-32", false);
		adjacent.addEdge("A-23", "A-33", false);

		adjacent.addEdge("A-31", "A-41", false);
		adjacent.addEdge("A-32", "A-42", false);
		adjacent.addEdge("A-33", "A-43", false);

		LinkedList<String> listDFS = adjacent.DFS("A");

		LinkedList<String> listBFS = adjacent.BFS("A");

		assertEquals(listDFS.size(), listBFS.size());
	}
}
