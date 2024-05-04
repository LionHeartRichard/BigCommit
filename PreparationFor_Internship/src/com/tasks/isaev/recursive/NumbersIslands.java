package com.tasks.isaev.recursive;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * Учитывая m x nдвумерную двоичную сетку grid, которая представляет собой карту 
 * '1's (суша) и '0's (вода), верните количество островов. Остров окружен водой и 
 * образован путем соединения соседних земель по горизонтали или вертикали. 
 * Вы можете предположить, что все четыре края сетки окружены водой.
 */

public class NumbersIslands {

	private int rows, columns;
	private int count = 0;

	public int numIslands(char[][] grid) {
		rows = grid.length;
		columns = grid[0].length;

		for (int i = 0; i < rows; ++i) {
			check(grid, i);
		}

		return count;
	}

	private void check(char[][] grid, int i) {
		for (int j = 0; j < columns; ++j) {
			if (grid[i][j] == '1') {
				backtracking(grid, i, j);
				++count;
			}
		}
	}

	private void backtracking(char[][] grid, int i, int j) {
		if (grid[i][j] == '*')
			return;
		grid[i][j] = '*';

		if (i > 0 && grid[i - 1][j] == '1')
			backtracking(grid, i - 1, j);

		if (i + 1 < rows && grid[i + 1][j] == '1')
			backtracking(grid, i + 1, j);

		if (j > 0 && grid[i][j - 1] == '1')
			backtracking(grid, i, j - 1);

		if (j + 1 < columns && grid[i][j + 1] == '1')
			backtracking(grid, i, j + 1);

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
