package com.tasks.isaev.recursive.classicbacktracking;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskNumIslands {

	int rows, columns;
	int count = 0;

	public int numIslands(char[][] grid) {
		rows = grid.length;
		columns = grid[0].length;

		for (int i = 0; i < rows; ++i) {
			check(grid, i);
		}

		return count;
	}

	private void check(char[][] grid, int row) {
		for (int j = 0; j < columns; ++j) {
			if (grid[row][j] == '1') {
				backtracking(grid, row, j);
				++count;
			}
		}
	}

	private void backtracking(char[][] grid, int row, int col) {
		if (grid[row][col] == '*')
			return;
		grid[row][col] = '*';

		if (row > 0 && grid[row - 1][col] == '1') {
			backtracking(grid, row - 1, col);
		}

		if (row + 1 < rows && grid[row + 1][col] == '1') {
			backtracking(grid, row + 1, col);
		}

		if (col > 0 && grid[row][col - 1] == '1') {
			backtracking(grid, row, col - 1);
		}

		if (col + 1 < columns && grid[row][col + 1] == '1') {
			backtracking(grid, row, col + 1);
		}
	}

	@Test
	public void test1() {
		char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'},
				{'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
		assertEquals(1, numIslands(grid));
	}

	@Test
	public void test2() {
		char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
				{'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
		assertEquals(3, numIslands(grid));
	}

	@Test
	public void test3() {
		char[][] grid = {{'1', '1', '0', '0', '1', '0', '0', '1'},
				{'1', '0', '0', '0', '1', '1', '0', '1'},
				{'0', '0', '0', '0', '0', '0', '0', '0'},
				{'0', '1', '0', '0', '1', '0', '0', '0'},
				{'0', '1', '0', '1', '1', '1', '0', '0'},
				{'0', '0', '0', '0', '1', '0', '0', '0'},
				{'0', '0', '0', '0', '0', '0', '0', '0'},
				{'0', '0', '0', '0', '1', '0', '0', '0'},
				{'1', '1', '0', '0', '1', '1', '0', '0'},
				{'0', '0', '0', '0', '0', '0', '0', '1'},};

		assertEquals(8, numIslands(grid));
	}
}
