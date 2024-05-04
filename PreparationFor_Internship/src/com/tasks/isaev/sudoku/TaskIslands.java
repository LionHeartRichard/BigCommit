package com.tasks.isaev.sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * Учитывая m x nдвумерную двоичную сетку grid, которая представляет собой карту 
 * '1's (суша) и '0's (вода), верните количество островов. Остров окружен водой и 
 * образован путем соединения соседних земель по горизонтали или вертикали. 
 * Вы можете предположить, что все четыре края сетки окружены водой.
 */

public class TaskIslands {

	private int m = 0, n = 0;

	public int numIslands(char[][] grid) {

		if (grid == null || grid.length == 0) {
			return 0;
		}

		m = grid.length;
		n = grid[0].length;

		int count = 0;
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j] == '1') {
					++count;
					backtracking(grid, i, j);
				}
			}
		}

		return count;
	}

	private void backtracking(char[][] grid, int i, int j) {

		if (!isValid(i, j, grid)) {
			return;
		}

		grid[i][j] = '0';
		backtracking(grid, i + 1, j);
		backtracking(grid, i - 1, j);
		backtracking(grid, i, j + 1);
		backtracking(grid, i, j - 1);
	}

	private boolean isValid(int i, int j, char[][] grid) {
		return (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == '1');
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
