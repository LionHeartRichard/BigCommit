package com.isaev.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class MyGraph {

	static class Graph<T> {
		private Map<T, Set<T>> map = new HashMap<T, Set<T>>();

		private Set<T> setTraversal = new HashSet<T>();

		public void addNewVertex(T source) {
			map.put(source, new HashSet<T>());
		}

		public void addNewEdge(T source, T destination, boolean bidirectional) {
			if (!map.containsKey(source)) {
				addNewVertex(source);
			}
			if (!map.containsKey(destination)) {
				addNewVertex(destination);
			}
			map.get(source).add(destination);
			if (bidirectional) {
				map.get(destination).add(source);
			}
		}

		public void checkNeighBour(T vertex) {
			Set<T> set = map.get(vertex);
			for (T v : set) {
				if (!setTraversal.contains(v)) {
					setTraversal.add(v);
					checkNeighBour(v);
				}
			}
		}
	}

	public static void main(String[] args) {

		try (BufferedReader reader = new BufferedReader(
				new FileReader("/home/kein/eclipse-workspace/Contest_Octouber_2023/resources/input.txt"))) {

			String s = reader.readLine();
			int n = Integer.parseInt(s);

			Graph<Integer> graph = new Graph<Integer>();
			for (int i = 0; i < n; ++i) {

				s = reader.readLine();
				String[] mas = s.split(" ");
				int server1 = Integer.parseInt(mas[0]);
				int server2 = Integer.parseInt(mas[1]);

				graph.addNewEdge(server1, server2, true);
			}

			s = reader.readLine();
			n = Integer.parseInt(s);

			Map<Integer, List<Integer>> resultMap = new HashMap<Integer, List<Integer>>();

			for (int j = 0; j < n; j++) {

				resultMap.put(j, new ArrayList<Integer>());
				int source = 0, k = 0;

				s = reader.readLine();
				String[] mas = s.split(" ");

				source = Integer.parseInt(mas[0]);
				k = Integer.parseInt(mas[1]);

				List<Integer> list = new ArrayList<Integer>();
				s = reader.readLine();
				String[] arr = s.split(" ");
				for (int i = 0; i < k; i++) {
					list.add(Integer.parseInt(arr[i]));
				}
				graph.setTraversal.clear();
				graph.checkNeighBour(source);
				for (Integer i : list) {
					if (graph.setTraversal.contains(i)) {
						resultMap.get(j).add(i);
					}
				}
			}

			FileWriter writer = new FileWriter(
					"/home/kein/eclipse-workspace/Contest_Octouber_2023/resources/output.txt");

			for (Integer item : resultMap.keySet()) {
				String str = resultMap.get(item).size() + "";
				for (Integer value : resultMap.get(item)) {
					str += " " + value;
				}
				writer.write(str + "\n");
				writer.flush();
			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}