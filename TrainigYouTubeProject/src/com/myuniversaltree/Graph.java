package com.myuniversaltree;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class Graph<T> {

	private Map<T, List<T>> map = new HashMap<T, List<T>>();

	private List<T> list = new ArrayList<T>();

	public Graph() {
	}

	public Graph(Map<T, List<T>> adjacent) {
		this.map = adjacent;
	}

	private void addVertex(T vertex) {
		map.put(vertex, new ArrayList<T>());
	}

	public void addEdge(T sourceVertex, T subVertex, boolean bidirectional) {
		if (!map.containsKey(sourceVertex)) {
			addVertex(sourceVertex);
		}
		if (!map.containsKey(subVertex)) {
			addVertex(subVertex);
		}

		map.get(sourceVertex).add(subVertex);
		if (bidirectional) {
			map.get(subVertex).add(sourceVertex);
		}
	}

	public List<T> BFS(T vertex) {
		if (map.containsKey(vertex)) {
			LinkedList<T> listVertices = new LinkedList<T>();
			Iterator<T> item = map.get(vertex).iterator();
			while (item.hasNext()) {
				listVertices.add(item.next());
			}
			List<T> result = new ArrayList<T>();
			while (listVertices.size() != 0) {
				T currentVertex = listVertices.pop();
				Iterator<T> i = map.get(currentVertex).iterator();
				while (i.hasNext()) {
					T v = i.next();
					if (!result.contains(v)) {
						result.add(v);
						listVertices.add(v);
					}
				}
			}
			return result;
		}
		return null;
	}

	public List<T> DFS(T vertex) {
		if (map.containsKey(vertex)) {
			list.clear();
			list.add(vertex);
			Iterator<T> item = map.get(vertex).iterator();
			while (item.hasNext()) {
				traversalDFS(item.next());
			}
			return list;
		}
		return null;
	}

	private void traversalDFS(T vertex) {
		list.add(vertex);
		Iterator<T> item = map.get(vertex).iterator();
		while (item.hasNext()) {
			T v = item.next();
			if (!list.contains(v)) {
				traversalDFS(v);
			}
		}

	}
}
