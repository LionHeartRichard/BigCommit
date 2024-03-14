package com.isaev.graphfunction;

import java.util.*;

public class OperationsOverWeightedGraph {

	public static <T> List<T> BFS(T sourceVertex, Map<T, List<Node<T>>> adjacent) {
		if (adjacent.containsKey(sourceVertex)) {
			List<T> result = new ArrayList<T>();
			LinkedList<T> linkList = new LinkedList<T>();
			result.add(sourceVertex);
			linkList.add(sourceVertex);
			Iterator<Node<T>> item = adjacent.get(sourceVertex).iterator();
			while (item.hasNext()) {
				T vertex = item.next().getNode();
				if (!result.contains(vertex)) {
					result.add(vertex);
					linkList.add(vertex);
				}
			}
			while (linkList.size() > 0) {
				T vertex = linkList.pop();
				item = adjacent.get(vertex).iterator();
				while (item.hasNext()) {
					T subVertex = item.next().getNode();
					if (!result.contains(subVertex)) {
						result.add(subVertex);
						linkList.add(subVertex);
					}
				}
			}
			return result;
		}
		return null;
	}

	public static <T> List<T> DFS(T sourceVertex, Map<T, List<Node<T>>> adjacent) {
		if (adjacent.containsKey(sourceVertex)) {
			List<T> listDFS = new ArrayList<T>();
			listDFS.add(sourceVertex);
			Iterator<Node<T>> item = adjacent.get(sourceVertex).iterator();
			while (item.hasNext()) {
				T vertex = item.next().getNode();
				if (!listDFS.contains(vertex)) {
					listDFS.add(vertex);
					traversalDFS(vertex, listDFS, adjacent);
				}
			}
			return listDFS;
		}
		return null;
	}

	private static <T> void traversalDFS(T vertex, List<T> listDFS, Map<T, List<Node<T>>> adjacent) {
		Iterator<Node<T>> item = adjacent.get(vertex).iterator();
		while (item.hasNext()) {
			T subVertex = item.next().getNode();
			if (!listDFS.contains(subVertex)) {
				listDFS.add(vertex);
				traversalDFS(subVertex, listDFS, adjacent);
			}
		}
	}
}
