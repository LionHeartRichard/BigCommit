package io.mysuper.functionstil;

import java.util.*;

public class WeightedGraph<T> {
	private int numberVertex;
	private Map<T, Integer> mapDistance = new HashMap<T, Integer>();
	private Set<T> set = new HashSet<T>();
	private PriorityQueue<Node<T>> pQue;
	private Map<T, List<Node<T>>> mapNode = new HashMap<T, List<Node<T>>>();
	private List<T> listDFS = new ArrayList<T>();

	public WeightedGraph(int numberVertex) {
		this.numberVertex = numberVertex;
		this.pQue = new PriorityQueue<Node<T>>(numberVertex, new Node<T>());
	}

	public void funClearStructuresDataAll() {
		mapDistance.clear();
		set.clear();
		pQue.clear();
		mapNode.clear();
	}

	public Map<T, Integer> dijkstra(Map<T, List<Node<T>>> adjacent, T sourceVertex) {
		funClearStructuresDataAll();
		mapNode = adjacent;
		Iterator<T> item = DFS(sourceVertex).iterator();
		while (item.hasNext()) {
			mapDistance.put(item.next(), Integer.MAX_VALUE);
		}
		mapDistance.put(sourceVertex, 0);

		pQue.add(new Node<T>(sourceVertex, 0));
		while (set.size() != numberVertex) {
			if (pQue.isEmpty()) {
				return mapDistance;
			}
			T currentVertex = pQue.remove().getNode();
			if (set.contains(currentVertex)) {
				continue;
			}
			set.add(currentVertex);

			searchNeighbours(currentVertex);
		}
		return mapDistance;
	}

	private void searchNeighbours(T currentVertex) {
		int currentDistance = mapDistance.get(currentVertex);
		Iterator<Node<T>> item = mapNode.get(currentVertex).iterator();
		while (item.hasNext()) {
			Node<T> subVertex = item.next();
			if (!set.contains(subVertex.getNode())) {
				int newDistance = subVertex.getPrice() + currentDistance;
				if (newDistance < mapDistance.get(subVertex.getNode())) {
					mapDistance.put(subVertex.getNode(), newDistance);
				}
				pQue.add(new Node<T>(subVertex.getNode(), mapDistance.get(subVertex.getNode())));
			}
		}
	}

	private void addVertex(T vertex) {
		mapNode.put(vertex, new ArrayList<Node<T>>());
	}

	public void addEdge(T vertex1, T vertex2, int price) {
		if (!mapNode.containsKey(vertex1)) {
			addVertex(vertex1);
		}
		if (!mapNode.containsKey(vertex2)) {
			addVertex(vertex2);
		}
		mapNode.get(vertex1).add(new Node<T>(vertex2, price));
		mapNode.get(vertex2).add(new Node<T>(vertex1, price));
	}

	public List<T> BFS(T vertex) {
		if (mapNode.containsKey(vertex)) {
			List<T> result = new ArrayList<T>();
			LinkedList<T> nodes = new LinkedList<T>();
			result.add(vertex);
			nodes.add(vertex);
			Iterator<Node<T>> item = mapNode.get(vertex).iterator();
			while (item.hasNext()) {
				Node<T> currentVertex = item.next();
				if (!result.contains(currentVertex.getNode())) {
					result.add(currentVertex.getNode());
					nodes.add(currentVertex.getNode());
				}
			}
			while (nodes.size() != 0) {
				T current = nodes.pop();
				Iterator<Node<T>> i = mapNode.get(current).iterator();
				while (i.hasNext()) {
					T subVertex = i.next().getNode();
					if (!result.contains(subVertex)) {
						result.add(subVertex);
						nodes.add(subVertex);
					}
				}
			}
			return result;
		} else {
			return null;
		}
	}

	public List<T> DFS(T vertex) {
		if (mapNode.containsKey(vertex)) {
			listDFS.clear();
			listDFS.add(vertex);
			Iterator<Node<T>> item = mapNode.get(vertex).iterator();
			while (item.hasNext()) {
				Node<T> currentVertex = item.next();
				if (!listDFS.contains(currentVertex.getNode())) {
					listDFS.add(currentVertex.getNode());
					traversalDFS(currentVertex.getNode());
				}
			}
			return listDFS;
		} else {
			return null;
		}
	}

	private void traversalDFS(T node) {
		Iterator<Node<T>> item = mapNode.get(node).iterator();
		while (item.hasNext()) {
			T current = item.next().getNode();
			if (!listDFS.contains(current)) {
				listDFS.add(current);
				traversalDFS(current);
			}
		}
	}

	public Map<T, List<Node<T>>> getWeighGraph() {
		return mapNode;
	}

	public void funClearStructuresData() {
		mapDistance.clear();
		set.clear();
		pQue.clear();
	}

	public Map<T, Integer> dijkstra(T sourceVertex) {
		funClearStructuresData();
		Iterator<T> item = BFS(sourceVertex).iterator();
		while (item.hasNext()) {
			mapDistance.put(item.next(), Integer.MAX_VALUE);
		}
		mapDistance.put(sourceVertex, 0);

		pQue.add(new Node<T>(sourceVertex, 0));
		while (set.size() != numberVertex) {
			if (pQue.isEmpty()) {
				return mapDistance;
			}
			T currentVertex = pQue.remove().getNode();
			if (set.contains(currentVertex)) {
				continue;
			}
			set.add(currentVertex);

			searchNeighbours(currentVertex);
		}
		return mapDistance;
	}

}
