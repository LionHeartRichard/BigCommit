package com.tasks.isaev.recursive.classicbacktracking;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class NQueens {

	public int totalNQueens(int n) {
		int[][] board = new int[n][n];
		return totalNQueensUtil(board, 0);
	}

	private int totalNQueensUtil(int[][] board, int column) {

		if (column >= board.length)
			return 1;

		int ans = 0;

		for (int row = 0; row < board[0].length; ++row) {
			if (checkPosition(board, row, column)) {
				board[row][column] = 1;
				ans += totalNQueensUtil(board, column + 1);
				board[row][column] = 0;
			}
		}

		return ans;
	}

	private boolean checkPosition(int[][] board, int row, int column) {

		for (int i = 0; i < column; ++i) {
			if (board[row][i] == 1) {
				return false;
			}
		}

		for (int i = row, j = column; i >= 0 && j >= 0; --i, --j) {
			if (board[i][j] == 1) {
				return false;
			}
		}

		for (int i = row, j = column; j >= 0 && i < board.length; ++i, --j) {
			if (board[i][j] == 1) {
				return false;
			}
		}

		return true;
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
