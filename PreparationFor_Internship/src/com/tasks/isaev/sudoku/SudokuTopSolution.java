package com.tasks.isaev.sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class SudokuTopSolution {
	static boolean isValid(char arr[][], int row, int col, char ch) {
		for (int j = 0; j < 9; j++) {
			if (j != col && arr[row][j] == ch)
				return false;
		}

		for (int i = 0; i < 9; i++) {
			if (i != row && arr[i][col] == ch)
				return false;
		}

		int stR = (row / 3) * 3;
		int stC = (col / 3) * 3;

		for (int i = stR; i < stR + 3; i++) {
			for (int j = stC; j < stC + 3; j++) {
				if (i == row && j == col)
					continue;
				if (arr[i][j] == ch)
					return false;
			}
		}
		return true;
	}

	public boolean isValidSudoku(char[][] arr) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arr[i][j] != '.') {
					boolean ans = isValid(arr, i, j, arr[i][j]);
					if (ans == false)
						return false;
				}
			}
		}
		return true;
	}
	
	@Test
	public void positiveTest1() {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		boolean actual = isValidSudoku(board);
		assertEquals(true, actual);
	}
}
