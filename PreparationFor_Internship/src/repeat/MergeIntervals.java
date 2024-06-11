package repeat;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

import java.util.*;

public class MergeIntervals {

	public int[][] merge(int[][] arr) {
		int n = arr.length;
		int[] forward = new int[n];
		int[] backward = new int[n];
		for (int i = 0; i < n; ++i) {
			forward[i] = arr[i][0];
			backward[i] = arr[i][1];
		}

		Arrays.sort(forward);
		Arrays.sort(backward);
		for (int i = 0; i < n; ++i) {
			arr[i][0] = forward[i];
			arr[i][1] = backward[i];
		}

		int j = 0;
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 1; i < n; ++i) {
			if (arr[j][1] >= arr[i][0]) {
				arr[j][1] = arr[i][1];
				set.add(i);
			} else {
				j = i;
			}
		}

		j = 0;
		int[][] result = new int[n - set.size()][2];
		for (int i = 0; i < n; ++i) {
			if (!set.contains(i)) {
				result[j][0] = arr[i][0];
				result[j++][1] = arr[i][1];
			}
		}

		return result;
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

	@Test
	public void test10() {

		int[][] inttervals = {{30, 56}, {2, 3}, {2, 19}, {3, 3}, {1, 10},
				{5, 7}, {2, 2}, {4, 6}};
		int[][] expexted = {{1, 19}, {30, 56}};
		int[][] actual = merge(inttervals);
		assertArrayEquals(expexted, actual);
	}
}
