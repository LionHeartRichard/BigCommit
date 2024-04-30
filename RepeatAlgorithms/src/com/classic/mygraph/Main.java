package com.classic.mygraph;

import java.util.*;

public class Main {

	public static void main(String[] args) {
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
		System.out.println(listDFS.size());
		listDFS.forEach(v -> System.out.print(v + " "));

		System.out.println("");
		System.out.println("---------");

		LinkedList<String> listBFS = adjacent.BFS("A");
		System.out.println(listBFS.size());
		listBFS.forEach(v -> System.out.print(v + " "));
		System.out.println();
		System.out.println("---------");
		System.out.println("---------");

		GraphWeight<String> graphWeight = new GraphWeight<String>(9);
		graphWeight.addEdge("0", "1", 3);
		graphWeight.addEdge("0", "7", 7);

		graphWeight.addEdge("1", "2", 7);
		graphWeight.addEdge("1", "7", 10);
		graphWeight.addEdge("1", "8", 4);

		graphWeight.addEdge("2", "3", 6);
		graphWeight.addEdge("2", "5", 2);
		graphWeight.addEdge("2", "8", 1);

		graphWeight.addEdge("3", "4", 8);
		graphWeight.addEdge("3", "5", 13);
		graphWeight.addEdge("3", "8", 3);

		graphWeight.addEdge("4", "5", 9);

		graphWeight.addEdge("5", "6", 4);
		graphWeight.addEdge("5", "8", 5);

		graphWeight.addEdge("6", "7", 2);
		graphWeight.addEdge("6", "8", 5);

		LinkedList<String> weightDFS = graphWeight.DFS("0");
		System.out.println(weightDFS.size());
		weightDFS.forEach(v -> System.out.print(v + " "));
		System.out.println("");
		System.out.println("---------");
		LinkedList<String> weightBFS = graphWeight.BFS("0");
		System.out.println(weightBFS.size());
		weightBFS.forEach(v -> System.out.print(v + " "));

		System.out.println("");
		System.out.println("----Dijkstra-----");
		String source = "0";
		Map<String, Integer> distance = graphWeight.dijkstra(source);
		distance.forEach((k, v) -> System.out
				.println("distance " + source + " to " + k + " = " + v));
	}
}
