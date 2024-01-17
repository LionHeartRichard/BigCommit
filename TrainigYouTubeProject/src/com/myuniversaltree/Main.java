package com.myuniversaltree;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		Graph<String> graph = new Graph<String>();

		graph.addEdge("Соки", "Манго", true);
		graph.addEdge("Соки", "Виноградный", true);
		graph.addEdge("Соки", "Яблочный", true);

		graph.addEdge("Газировка", "Кола", true);
		graph.addEdge("Газировка", "Минералка", true);
		graph.addEdge("Газировка", "Лимонад", true);

		graph.addEdge("Кола", "Кола-оригинальная", true);
		graph.addEdge("Кола", "Кола-классическая", true);
		graph.addEdge("Кола", "Кола-диетическая", true);

		List<String> adjecent = graph.BFS("Газировка");
		adjecent.forEach(s -> System.out.print(s + ", "));

		adjecent.clear();
		System.out.println();

		adjecent = graph.DFS("Газировка");
		adjecent.forEach(s -> System.out.print(s + ", "));
		System.out.println();

		int numberVertex = 20;
		WeighGraph<String> graphStr = new WeighGraph<String>(numberVertex);
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

		Map<String, Double> mapDistanceStr = graphStr.dijkstra("A");
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
