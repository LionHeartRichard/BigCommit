package com.bfs;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class LadderLengthDijkstra {

	private int numberVertices = 0;
	private Map<String, Set<Node>> map = new HashMap<String, Set<Node>>();

	private Map<String, Integer> distance = new HashMap<String, Integer>();
	private Set<String> keyVertices = new HashSet<String>();
	private PriorityQueue<Node> pQue;

	private Set<String> cache = new HashSet<String>();

	private class Node implements Comparator<Node> {
		Node() {
		}
		Node(String node, int price) {
			this.node = node;
			this.price = price;
		}
		String node;
		int price;
		@Override
		public int compare(Node n1, Node n2) {
			if (n1.price < n2.price)
				return -1;
			if (n1.price > n2.price)
				return 1;
			return 0;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(node);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return Objects.equals(node, other.node);
		}
		private LadderLengthDijkstra getEnclosingInstance() {
			return LadderLengthDijkstra.this;
		}

	}

	private void DFS(String source) {
		cache.add(source);
		for (Node vertex : map.get(source)) {
			if (!cache.contains(vertex.node)) {
				cache.add(vertex.node);
				traversalDFS(vertex.node);
			}
		}

	}

	private void traversalDFS(String vertex) {
		Iterator<Node> node = map.get(vertex).iterator();
		while (node.hasNext()) {
			Node v = node.next();
			if (!cache.contains(v.node)) {
				cache.add(v.node);
				traversalDFS(v.node);
			}
		}
	}

	private Map<String, Integer> dijkstra(String source) {

		if (map.containsKey(source)) {
			DFS(source);
			cache.forEach(v -> distance.put(v, Integer.MAX_VALUE));

			distance.put(source, 0);
			pQue.add(new Node(source, 0));

			while (keyVertices.size() != numberVertices) {
				if (pQue.isEmpty())
					break;
				String vertex = pQue.remove().node;
				if (keyVertices.contains(vertex))
					continue;
				keyVertices.add(vertex);

				searchNeighbours(vertex);
			}
			return distance;
		}
		return null;
	}

	private void searchNeighbours(String vertex) {
		int interval = distance.get(vertex);
		Iterator<Node> node = map.get(vertex).iterator();
		while (node.hasNext()) {
			Node n = node.next();
			if (!keyVertices.contains(n.node)) {
				int newInterval = interval + n.price;
				if (newInterval < distance.get(n.node)) {
					distance.put(n.node, newInterval);
				}
				pQue.add(new Node(n.node, distance.get(n.node)));
			}
		}
	}

	private void addVertex(String vertex) {
		map.put(vertex, new HashSet<Node>());
	}

	private void addEdge(String vertex, String subVertex, int price) {
		if (!map.containsKey(vertex))
			addVertex(vertex);
		if (!map.containsKey(subVertex))
			addVertex(subVertex);
		map.get(vertex).add(new Node(subVertex, price));
		map.get(subVertex).add(new Node(vertex, price));
	}

	public int ladderLength(String begin, String end, List<String> words) {

		Set<String> vertices = new HashSet<String>(words);
		if (!vertices.contains(end))
			return 0;

		if (vertices.size() == 2 && !isValid(begin, end))
			return 0;

		vertices.add(begin);
		mapping(begin, vertices);

		numberVertices = map.size();
		pQue = new PriorityQueue<Node>(numberVertices, new Node());

		dijkstra(begin);

		if (map.isEmpty())
			return 0;

		int res = 0;
		if (distance.get(end) != null)
			res = distance.get(end) + 1;

		return res;
	}

	private void mapping(String begin, Set<String> vertices) {
		for (String vertex : vertices) {
			for (String sub : vertices) {
				if (!vertex.equals(sub)) {
					if (isValid(vertex, sub))
						addEdge(vertex, sub, 1);
				}
			}
		}
	}

	private boolean isValid(String reference, String current) {
		int count = 0;
		for (int i = 0; i < reference.length(); ++i) {
			if (reference.charAt(i) != current.charAt(i))
				++count;
			if (count > 1)
				return false;
		}
		return true;
	}

	@Test
	public void test1() {

		String beginWord = "hit", endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log",
				"cog");

		// "hit" -> "hot" -> "dot" -> "dog" -> cog"

		int actual = ladderLength(beginWord, endWord, wordList);
		assertEquals(5, actual);

	}

	@Test
	public void test2() {

		String beginWord = "hit", endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot",
				"log");
		int actual = ladderLength(beginWord, endWord, wordList);
		assertEquals(0, actual);
	}

	@Test
	public void test3() {
		String beginWord = "a", endWord = "c";
		List<String> wordList = Arrays.asList("a", "i", "y", "b", "c");
		int actual = ladderLength(beginWord, endWord, wordList);
		assertEquals(2, actual);
	}

	@Test
	public void test4() {
		String beginWord = "hot", endWord = "dog";
		List<String> wordList = Arrays.asList("hot", "dog", "cog", "pot",
				"dot");
		int actual = ladderLength(beginWord, endWord, wordList);
		assertEquals(3, actual);
	}
}
