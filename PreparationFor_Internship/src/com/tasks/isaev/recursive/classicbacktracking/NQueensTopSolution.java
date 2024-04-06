package com.tasks.isaev.recursive.classicbacktracking;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class NQueensTopSolution {

	private int ans = 0;

	public int totalNQueens(int n) {
		backtracking(n, 0, new boolean[n], new boolean[2 * n - 1],
				new boolean[2 * n - 1]);
		return ans;
	}

	private void backtracking(int n, int i, boolean[] columns, boolean[] diag1,
			boolean[] diag2) {
		if (i == n) {
			++ans;
			return;
		}

		for (int j = 0; j < columns.length; ++j) {

			if (columns[j] || diag1[i + j] || diag2[j - i + n - 1]) {
				continue;
			}

			columns[j] = diag1[i + j] = diag2[j - i + n - 1] = true;

			backtracking(n, i + 1, columns, diag1, diag2);

			columns[j] = diag1[i + j] = diag2[j - i + n - 1] = false;
		}
	}

	@Test
	public void test1() {
		int actual = totalNQueens(4);
		assertEquals(2, actual);
	}

	@Test
	public void test2() {
		int actual = totalNQueens(1);
		assertEquals(1, actual);
	}

}
