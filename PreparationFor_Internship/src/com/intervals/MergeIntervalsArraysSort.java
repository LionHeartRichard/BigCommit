package com.intervals;

import static org.junit.Assert.assertArrayEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class MergeIntervalsArraysSort {

	public int[][] merge(int[][] arr) {
		int n = arr.length;
		int[] begins = new int[n];
		int[] ends = new int[n];

		for (int i = 0; i < n; ++i) {
			begins[i] = arr[i][0];
			ends[i] = arr[i][1];
		}
		Arrays.sort(begins);
		Arrays.sort(ends);
		for (int i = 0; i < n; ++i) {
			arr[i][0] = begins[i];
			arr[i][1] = ends[i];
		}
		Set<Integer> set = new HashSet<Integer>();
		int j = 0;
		for (int i = 1; i < n; ++i) {
			if (arr[j][1] >= arr[i][0]) {
				arr[j][1] = arr[i][1];
				set.add(i);
			} else {
				j=i;
			}
		}

		int[][] res = new int[n - set.size()][2];
		j = 0;
		for (int i = 0; i < n; ++i) {
			if (!set.contains(i)) {
				res[j][0] = arr[i][0];
				res[j++][1] = arr[i][1];
			}
		}
		return res;

	}

	@Test
	public void test1() {
		int[][] intervals = {{15, 18}, {1, 3}, {8, 10}, {2, 6},};
		int[][] expexted = {{1, 6}, {8, 10}, {15, 18}};
		int[][] actual = merge(intervals);
		assertArrayEquals(expexted, actual);
	}

	@Test
	public void test2() {
		int[][] intervals = {{1, 4}, {4, 5}};
		int[][] expexted = {{1, 5}};
		int[][] actual = merge(intervals);
		assertArrayEquals(expexted, actual);
	}

	@Test
	public void test3() {
		int[][] inttervals = {{1, 4}, {0, 4}};
		int[][] expected = {{0, 4}};
		int[][] actual = merge(inttervals);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test4() {

		int[][] inttervals = {{1, 4}, {0, 0}};
		int[][] expected = {{0, 0}, {1, 4}};
		int[][] actual = merge(inttervals);
		assertArrayEquals(expected, actual);

	}

	@Test
	public void test5() {
		int[][] inttervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
		int[][] expexted = {{1, 6}, {8, 10}, {15, 18}};
		int[][] actual = merge(inttervals);
		assertArrayEquals(expexted, actual);
	}

	@Test
	public void test6() {

		int[][] inttervals = {{1, 4}, {1, 5}};
		int[][] expexted = {{1, 5}};
		int[][] actual = merge(inttervals);
		assertArrayEquals(expexted, actual);
	}

	@Test
	public void test7() {

		int[][] inttervals = {{1, 4}, {0, 2}, {3, 5}};
		int[][] expexted = {{0, 5}};
		int[][] actual = merge(inttervals);
		assertArrayEquals(expexted, actual);
	}

	@Test
	public void test8() {

		int[][] inttervals = {{4, 5}, {1, 4}, {0, 1}};
		int[][] expexted = {{0, 5}};
		int[][] actual = merge(inttervals);
		assertArrayEquals(expexted, actual);
	}

	@Test
	public void test9() {

		int[][] inttervals = {{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2},
				{4, 6}};
		int[][] expexted = {{1, 3}, {4, 7}};
		int[][] actual = merge(inttervals);
		assertArrayEquals(expexted, actual);
	}
}
