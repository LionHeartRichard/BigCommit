package io.mysuper.functionstil;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		int numberVertex = 9;

		Map<Integer, List<Node<Integer>>> adjacent = new HashMap<Integer, List<Node<Integer>>>();

		for (int i = 0; i < 9; ++i) {
			adjacent.put(i, new ArrayList<Node<Integer>>());
		}
		adjacent.get(0).add(new Node<Integer>(1, 3));
		adjacent.get(0).add(new Node<Integer>(7, 7));
		adjacent.get(1).add(new Node<Integer>(0, 3));
		adjacent.get(1).add(new Node<Integer>(2, 7));
		adjacent.get(1).add(new Node<Integer>(7, 10));
		adjacent.get(1).add(new Node<Integer>(8, 4));
		adjacent.get(2).add(new Node<Integer>(1, 7));
		adjacent.get(2).add(new Node<Integer>(3, 6));
		adjacent.get(2).add(new Node<Integer>(5, 2));
		adjacent.get(2).add(new Node<Integer>(8, 1));
		adjacent.get(3).add(new Node<Integer>(2, 6));
		adjacent.get(3).add(new Node<Integer>(4, 8));
		adjacent.get(3).add(new Node<Integer>(5, 13));
		adjacent.get(3).add(new Node<Integer>(8, 3));
		adjacent.get(4).add(new Node<Integer>(3, 8));
		adjacent.get(4).add(new Node<Integer>(5, 9));
		adjacent.get(5).add(new Node<Integer>(2, 2));
		adjacent.get(5).add(new Node<Integer>(3, 13));
		adjacent.get(5).add(new Node<Integer>(4, 9));
		adjacent.get(5).add(new Node<Integer>(6, 4));
		adjacent.get(5).add(new Node<Integer>(8, 5));
		adjacent.get(6).add(new Node<Integer>(5, 4));
		adjacent.get(6).add(new Node<Integer>(7, 2));
		adjacent.get(6).add(new Node<Integer>(8, 5));
		adjacent.get(7).add(new Node<Integer>(0, 7));
		adjacent.get(7).add(new Node<Integer>(1, 10));
		adjacent.get(7).add(new Node<Integer>(6, 2));
		adjacent.get(7).add(new Node<Integer>(8, 6));
		adjacent.get(8).add(new Node<Integer>(1, 4));
		adjacent.get(8).add(new Node<Integer>(2, 1));
		adjacent.get(8).add(new Node<Integer>(3, 3));
		adjacent.get(8).add(new Node<Integer>(5, 5));
		adjacent.get(8).add(new Node<Integer>(6, 5));
		adjacent.get(8).add(new Node<Integer>(7, 6));

		WeightedGraph<Integer> graph = new WeightedGraph<Integer>(numberVertex);

		Map<Integer, Integer> mapDistance = graph.dijkstra(adjacent, 0);
		mapDistance.forEach((k, v) -> System.out.println("0 to " + k + " = " + v));

		System.out.println("------------------Graph 2-----------------------");

		WeightedGraph<String> graphStr = new WeightedGraph<String>(20);
		graphStr.addEdge("A", "B", 4);
		graphStr.addEdge("A", "C", 5);
		graphStr.addEdge("B", "D", 9);
		graphStr.addEdge("C", "E", 3);
		graphStr.addEdge("B", "E", 7);
		graphStr.addEdge("D", "F", 2);
		graphStr.addEdge("D", "E", 13);
		graphStr.addEdge("E", "F", 6);
//--------------------------------------------------------------------
		graphStr.addEdge("A0", "B1", 8);
		graphStr.addEdge("A0", "C1", 4);
		graphStr.addEdge("A0", "A1", 3);
		graphStr.addEdge("A1", "F2", 4);
		graphStr.addEdge("A1", "D3", 80);

		graphStr.addEdge("B1", "A2", 17);
		graphStr.addEdge("B1", "B2", 4);
		graphStr.addEdge("B1", "C2", 10);
		graphStr.addEdge("C2", "A3", 4);
		graphStr.addEdge("C2", "B3", 16);

		graphStr.addEdge("D1", "A0", 5);
		graphStr.addEdge("D1", "D2", 4);
		graphStr.addEdge("D1", "F2", 13);
		graphStr.addEdge("D2", "C3", 20);
		graphStr.addEdge("D2", "D3", 10);

		Map<String, Integer> mapDistanceStr = graphStr.dijkstra("A");
		mapDistanceStr.forEach((k, v) -> System.out.println("A to: " + k + " " + v));

		System.out.println("------------------BFS-----------------------");
		List<String> listBFS = graphStr.BFS("A0");
		listBFS.forEach(i -> System.out.print(i + " "));
		System.out.println();
		System.out.println("------------------DFS-----------------------");
		List<String> listDFS = graphStr.DFS("A0");
		listDFS.forEach(i -> System.out.print(i + " "));

		System.out.println();
		System.out.println("------------------Graph 3-----------------------");
		mapDistanceStr.clear();
		mapDistanceStr = graphStr.dijkstra("A0");
		mapDistanceStr.forEach((k, v) -> System.out.println("A0 to " + k + " " + v));
	}

}
