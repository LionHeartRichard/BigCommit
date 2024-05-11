package com.bfs;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class LadderLengthDijkstraRecycled {

	// DFS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// What's happening?!!!

	private Map<String, Set<Node>> map = new HashMap<String, Set<Node>>();
	private LinkedList<String> cache = new LinkedList<String>();
	private String endVertex;
	private boolean checkEnd = false;

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
		private LadderLengthDijkstraRecycled getEnclosingInstance() {
			return LadderLengthDijkstraRecycled.this;
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

	public void DFS(String source) {
		cache.add(source);
		for (Node n : map.get(source)) {
			if (checkEnd)
				return;
			if (!cache.contains(n.node)) {
				cache.add(n.node);
				if (endVertex.endsWith(n.node)) {
					checkEnd = true;
					return;
				}
				traversalDFS(n.node);
			}
		}
	}

	private void traversalDFS(String vertex) {
		Iterator<Node> n = map.get(vertex).iterator();
		while (n.hasNext()) {
			if (checkEnd)
				return;
			Node current = n.next();
			if (!cache.contains(current.node)) {
				cache.add(current.node);
				if (endVertex.endsWith(current.node)) {
					checkEnd = true;
					return;
				}
				traversalDFS(current.node);
			}
		}
	}

	public int ladderLength(String begin, String end, List<String> words) {

		Set<String> vertices = new HashSet<String>(words);
		if (!vertices.contains(end))
			return 0;

		if (vertices.size() == 2 && !isValid(begin, end))
			return 0;

		if (isValid(begin, end))
			return 2;

		vertices.add(begin);
		mapping(begin, vertices);

		if (map.isEmpty() || !map.containsKey(end))
			return 0;

		checkEnd = false;
		endVertex = end;
		int res = 0;
		DFS(begin);
		if (checkEnd)
			res = cache.size();
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

	@Test
	public void test5() {
		String beginWord = "hot", endWord = "dog";
		List<String> wordList = Arrays.asList("hot", "dog");
		int actual = ladderLength(beginWord, endWord, wordList);
		assertEquals(0, actual);
	}
}
