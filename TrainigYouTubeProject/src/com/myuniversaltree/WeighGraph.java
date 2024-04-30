package com.myuniversaltree;

import java.util.*;

public class WeighGraph<T> {

	private int numberVertices;

	private Map<T, Double> mapDistance = new HashMap<T, Double>();
	private Set<T> setCache = new HashSet<T>();
	private PriorityQueue<Node<T>> pQue = new PriorityQueue<Node<T>>();

	private Map<T, List<Node<T>>> mapNode = new HashMap<T, List<Node<T>>>();

	private List<T> listDFS = new ArrayList<T>();

	public WeighGraph(int numberVertices) {
		this.numberVertices = numberVertices;
		this.pQue = new PriorityQueue<Node<T>>(numberVertices, new Node<T>());
	}

	private void addVertex(T sourceVertex) {
		mapNode.put(sourceVertex, new ArrayList<Node<T>>());
	}

	public void addEdge(T sourceVertex, T subVertex, double price) {
		if (!mapNode.containsKey(sourceVertex)) {
			addVertex(sourceVertex);
		}
		if (!mapNode.containsKey(subVertex)) {
			addVertex(subVertex);
		}
		mapNode.get(sourceVertex).add(new Node<T>(subVertex, price));
		mapNode.get(subVertex).add(new Node<T>(sourceVertex, price));
	}

	public List<T> DFS(T vertex) {
		listDFS.clear();
		if (mapNode.containsKey(vertex)) {
			listDFS.add(vertex);
			Iterator<Node<T>> item = mapNode.get(vertex).iterator();
			while (item.hasNext()) {
				Node<T> currentNode = item.next();
				if (!listDFS.contains(currentNode.getNode())) {
					listDFS.add(currentNode.getNode());
					traversalDFS(currentNode.getNode());
				}
			}
		}
		return listDFS;
	}

	private void traversalDFS(T vertex) {
		Iterator<Node<T>> item = mapNode.get(vertex).iterator();
		while (item.hasNext()) {
			T currentVertex = item.next().getNode();
			if (!listDFS.contains(currentVertex)) {
				listDFS.add(currentVertex);
				traversalDFS(currentVertex);
			}
		}
	}

	public List<T> BFS(T vertex) {
		if (mapNode.containsKey(vertex)) {
			LinkedList<T> linkList = new LinkedList<T>();
			List<T> result = new ArrayList<T>();
			result.add(vertex);
			linkList.add(vertex);
			Iterator<Node<T>> item = mapNode.get(vertex).iterator();
			while (item.hasNext()) {
				Node<T> currentNode = item.next();
				T currentVertex = currentNode.getNode();
				if (!result.contains(currentVertex)) {
					result.add(currentVertex);
					linkList.add(currentVertex);
				}
			}
			while (linkList.size() > 0) {
				T currentVertex = linkList.pop();
				item = mapNode.get(currentVertex).iterator();
				while (item.hasNext()) {
					Node<T> currentNode = item.next();
					T current = currentNode.getNode();
					if (!result.contains(current)) {
						result.add(current);
						linkList.add(current);
					}
				}
			}
			return result;
		}
		return null;
	}

	private void clearAllStrictures() {
		mapDistance.clear();
		mapNode.clear();
		setCache.clear();
		listDFS.clear();
	}

	private void searchNeighbours(T currentVertex) {
		Double currentDistance = mapDistance.get(currentVertex);
		Iterator<Node<T>> item = mapNode.get(currentVertex).iterator();
		while (item.hasNext()) {
			Node<T> currentNode = item.next();
			if (!setCache.contains(currentNode.getNode())) {
				Double newDistance = currentDistance + currentNode.getPrice();
				if (newDistance < mapDistance.get(currentNode.getNode())) {
					mapDistance.put(currentNode.getNode(), newDistance);
				}
				pQue.add(new Node<T>(currentNode.getNode(),
						mapDistance.get(currentNode.getNode())));
			}
		}
	}

	public Map<T, Double> dijkstra(Map<T, List<Node<T>>> adjacent,
			T sourceVertex) {
		clearAllStrictures();
		mapNode = adjacent;
		Iterator<T> item = DFS(sourceVertex).iterator();
		while (item.hasNext()) {
			mapDistance.put(item.next(), Double.MAX_VALUE);
		}
		mapDistance.put(sourceVertex, 0.0);
		pQue.add(new Node<T>(sourceVertex, 0.0));
		while (setCache.size() != numberVertices) {
			if (pQue.isEmpty()) {
				break;
			}
			T currentVertex = pQue.remove().getNode();
			if (setCache.contains(currentVertex)) {
				continue;
			}
			setCache.add(currentVertex);

			searchNeighbours(currentVertex);
		}
		return mapDistance;
	}

	private void clearForMethodsForDijkstra() {
		mapDistance.clear();
		setCache.clear();
		listDFS.clear();
	}

	public Map<T, Double> dijkstra(T sourceVertex) {
		clearForMethodsForDijkstra();
		Iterator<T> item = DFS(sourceVertex).iterator();
		while (item.hasNext()) {
			mapDistance.put(item.next(), Double.MAX_VALUE);
		}
		mapDistance.put(sourceVertex, 0.0);
		pQue.add(new Node<T>(sourceVertex, 0.0));

		while (setCache.size() != numberVertices) {
			if (pQue.isEmpty()) {
				break;
			}
			T currentVertex = pQue.remove().getNode();
			if (setCache.contains(currentVertex)) {
				continue;
			}
			setCache.add(currentVertex);

			searchNeighbours(currentVertex);
		}

		return mapDistance;
	}

}
