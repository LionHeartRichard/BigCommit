package com.tasks.isaev.sudoku;

/*
 * Учитывая m x nдвумерную двоичную сетку grid, которая представляет собой карту 
 * '1's (суша) и '0's (вода), верните количество островов. Остров окружен водой и 
 * образован путем соединения соседних земель по горизонтали или вертикали. 
 * Вы можете предположить, что все четыре края сетки окружены водой.
 */

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskIslandsTopSolution {

	int rows;
	int columns;
	int countIslands = 0;

	public int numIslands(char[][] grid) {
		rows = grid.length;
		columns = grid[0].length;
		for (int i = 0; i < rows; ++i) {
			check(grid, i);
		}
		return countIslands;
	}

	public void check(char[][] grid, int row) {
		char[] column = grid[row];
		for (int j = 0; j < columns; ++j) {
			if (column[j] == '1') {
				backtracking(grid, row, j);
				++countIslands;
			}
		}
	}

	public void backtracking(char[][] grid, int row, int col) {
		if (grid[row][col] == '*') {
			return;
		}
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
