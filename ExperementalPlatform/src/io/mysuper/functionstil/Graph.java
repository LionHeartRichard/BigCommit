package io.mysuper.functionstil;

import java.util.*;

public class Graph<T> {
	private Map<T, Set<T>> map = new HashMap<T, Set<T>>();
	private List<T> list = new ArrayList<T>();

	private void addVertex(T vertex) {
		map.put(vertex, new HashSet<T>());
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
			LinkedList<T> node = new LinkedList<T>();
			List<T> result = new ArrayList<T>();
			Iterator<T> item = map.keySet().iterator();
			while (item.hasNext()) {
				T v = item.next();
				if (!result.contains(v)) {
					node.add(v);
					result.add(v);
				}
			}
			while (node.size() != 0) {
				T v = node.pop();
				Iterator<T> i = map.get(v).iterator();
				while (i.hasNext()) {
					T subVertex = i.next();
					if (!result.contains(subVertex)) {
						result.add(subVertex);
						node.add(subVertex);
					}
				}
			}
			return result;
		} else {
			return null;
		}
	}

	public List<T> DFS(T vertex) {
		if (map.containsKey(vertex)) {
			list.clear();
			list.add(vertex);
			Iterator<T> item = map.get(vertex).iterator();
			while (item.hasNext()) {
				T v = item.next();
				traversalDFS(v);
			}
			return list;
		} else {
			return null;
		}
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

	public Map<T, Set<T>> getMapGraph() {
		return map;
	}

}
