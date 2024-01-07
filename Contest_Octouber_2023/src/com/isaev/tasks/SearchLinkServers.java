package com.isaev.tasks;

import java.util.*;
import java.io.*;

public class SearchLinkServers {

	static class Graph<T> {
		int numberVertex;

		Set<T> result = new HashSet<T>();
		Set<T> set = new HashSet<T>();
		Deque<T> dQue = new ArrayDeque<T>();;

		Map<T, List<T>> mapVertex = new HashMap<T, List<T>>();

		public Graph(int numberVertex, Map<T, List<T>> adjacent) {
			this.numberVertex = numberVertex;
			this.mapVertex = adjacent;
		}

		public void DFS(T source) {

			set.clear();
			result.clear();
			dQue.clear();

			dQue.add(source);
			while (set.size() != numberVertex) {
				if (dQue.isEmpty()) {
					return;
				}
				T currentVertex = dQue.pop();

				if (set.contains(currentVertex)) {
					continue;
				}
				set.add(currentVertex);

				enterNeighBour(currentVertex);
			}
		}

		private void enterNeighBour(T currentVertex) {

			List<T> list = mapVertex.get(currentVertex);
			for (T subVertex : list) {
				if (!set.contains(subVertex)) {
					result.add(subVertex);
					dQue.add(subVertex);
				}
			}
		}

	}

	public static void main(String[] args) {

		try (BufferedReader reader = new BufferedReader(
				new FileReader("/home/kein/eclipse-workspace/Contest_Octouber_2023/resources/input.txt"))) {

			String s = reader.readLine();
			int n = Integer.parseInt(s);

			Map<Integer, List<Integer>> adjacent = new HashMap<Integer, List<Integer>>();
			for (int i = 0; i < n; ++i) {
				s = reader.readLine();
				String[] arr = s.split(" ");
				int server1 = Integer.parseInt(arr[0]);
				int server2 = Integer.parseInt(arr[1]);

				adjacent.putIfAbsent(server1, new ArrayList<Integer>());
				adjacent.putIfAbsent(server2, new ArrayList<Integer>());

				adjacent.get(server1).add(server2);
				adjacent.get(server2).add(server1);
			}

			Graph<Integer> graph = new Graph<Integer>(n, adjacent);
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

				graph.DFS(source);

				for (Integer i : list) {
					if (graph.result.contains(i)) {
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
