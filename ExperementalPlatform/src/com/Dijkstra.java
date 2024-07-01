package com;

import static org.junit.Assert.assertArrayEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class Dijkstra {
	public static int[] dijkstra(int[][] graph, int source) {
		int n = graph.length;
		int[] distances = new int[n];

		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[source] = 0;

		PriorityQueue<Integer> pQue = new PriorityQueue<Integer>();
		pQue.offer(source);

		while (!pQue.isEmpty()) {
			int node = pQue.poll();

			for (int i = 0; i < n; ++i) {
				if (graph[node][i] > 0) {
					int newDistance = distances[node] + graph[node][i];
					if (newDistance < distances[i]) {
						distances[i] = newDistance;
						pQue.offer(i);
					}
				}
			}

		}

		return distances;
	}

	@Test
	public void test1() {

		int[][] graph = {{0, 3, 0, 0, 0, 0, 0, 7, 0},
				{3, 0, 7, 0, 0, 0, 0, 10, 4}, {0, 7, 0, 6, 0, 2, 0, 0, 1},
				{0, 0, 6, 0, 8, 13, 0, 0, 3}, {0, 0, 0, 8, 0, 9, 0, 0, 0},
				{0, 0, 2, 13, 9, 0, 4, 0, 5}, {0, 0, 0, 0, 0, 4, 0, 2, 5},
				{7, 10, 0, 0, 0, 0, 2, 0, 6}, {0, 4, 1, 3, 0, 5, 5, 6, 0},};

		int[] actual = dijkstra(graph, 0);
		int[] expected = {0, 3, 8, 10, 18, 10, 9, 7, 7};
		assertArrayEquals(expected, actual);
	}
}
