package com.tasks.isaev.sudoku;

import static org.junit.Assert.assertArrayEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class SpiralMatrixOrder {

	private int[][] directionsArray = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	// !!!Direction!!!: 0=right 1=down 2=left 3=up
	private int indexDirections = 0;
	private List<Integer> result = new ArrayList<Integer>();

	public void walk(int i, int j, int r, int c, int[][] matrix,
			boolean[][] visits) {
		if (result.size() == r * c) {
			return;
		}

		int currentDirection = indexDirections;
		if (i >= r || i < 0 || j >= c || j < 0) {
			indexDirections = (indexDirections + 1) % 4;
			return;
		}

		if (visits[i][j]) {
			indexDirections = (indexDirections + 1) % 4;
			return;
		} else {
			visits[i][j] = true;
			result.add(matrix[i][j]);
		}

		walk(i + directionsArray[indexDirections][0],
				j + directionsArray[indexDirections][1], r, c, matrix, visits);

		if (currentDirection != indexDirections) {
			walk(i + directionsArray[indexDirections][0],
					j + directionsArray[indexDirections][1], r, c, matrix,
					visits);
		}
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		int r = matrix.length, c = matrix[0].length;
		boolean[][] unique = new boolean[r][c];
		walk(0, 0, r, c, matrix, unique);
		return result;
	}

	@Test
	public void positiveTest1() {
		int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[] expecteds = {1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7};
		int[] actual = new int[expecteds.length];
		List<Integer> list = spiralOrder(matrix);
		int i = 0;
		for (int value : list) {
			actual[i++] = value;
		}
		assertArrayEquals(expecteds, actual);
	}

}
