package com.tasks.isaev.sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * Вам дан m x n целочисленный массив grid. Изначально в верхнем левом углу находится робот 
 * (т. е. grid[0][0]). Робот пытается переместиться в правый нижний угол 
 * (т. е. grid[m - 1][n - 1]). В любой момент времени робот может двигаться только вниз 
 * или вправо. Препятствие и пространство отмечаются как 1 или 0 соответственно в grid. 
 * Путь, по которому движется робот, не может включать квадраты , являющиеся препятствиями.
 * Возвращает количество возможных уникальных путей, по которым робот может добраться до 
 * правого нижнего угла.
 */

public class UniquePathForRobot {

	private int count = 0;

	public int uniquePathsWithObstacles(int[][] grid) {
		backtracking(grid, 0, 0);
		return count;
	}

	private boolean backtracking(int[][] grid, int i, int j) {
		if (i == grid.length && j == grid[0].length) {
			++count;
			return false;
		}
		boolean chekPath = backtracking(grid, i, j);
		if (i < grid.length && chekPath) {
			++i;
		} else {
			--i;
		}
		return backtracking(grid, i, j) || backtracking(grid, i, j);
	}

	@Test
	public void test1() {
		int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
		assertEquals(2, uniquePathsWithObstacles(grid));
	}

}
