package com.tasks.isaev.sudoku;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class Sudoku {
	public boolean isValidSudoku(char[][] arr) {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (arr[i][j] != '.') {
					char num = arr[i][j];
					arr[i][j] = '.';
					if (!funCheck(arr, num, i, j)) {
						return false;
					}
					int m = (i / 3 * 3) + j / 3;
					int n = (i % 3 * 3) + j % 3;
					if (arr[m][n] == num) {
						return false;
					}
					arr[i][j] = num;
				}
			}
		}
		return true;
	}

	private boolean funCheck(char[][] arr, char num, int row, int column) {
		for (int i = 0; i < 9; ++i) {
			if (arr[i][column] == num || arr[row][i] == num)
				return false;

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
