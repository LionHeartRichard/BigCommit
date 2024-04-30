package com.medium;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Sudoku {

	public boolean isValidSudoku(char[][] board) {

		for (int i = 0; i < 9; ++i) {

			boolean[] rows = new boolean[9];
			boolean[] columns = new boolean[9];
			boolean[] box = new boolean[9];

			for (int j = 0; j < 9; ++j) {
				char token = board[i][j];
				if (token != '.') {
					if (rows[token - '1'])
						return false;
					rows[token - '1'] = true;
				}
				token = board[j][i];
				if (token != '.') {
					if (columns[token - '1'])
						return false;
					columns[token - '1'] = true;
				}

				int m = i / 3 * 3 + j / 3;
				int n = i % 3 * 3 + j % 3;

				token = board[m][n];
				if (token != '.') {
					if (box[token - '1'])
						return false;
					box[token - '1'] = true;
				}
			}
		}
		return true;
	}

	@Test
	public void positiveTest1() {
		char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
				{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
				{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
				{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
				{'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		boolean actual = isValidSudoku(board);
		assertEquals(true, actual);
	}

	@Test
	public void positiveTest2() {
		char[][] board = {{'.', '1', '.', '6', '.', '.', '.', '.', '.',},
				{'.', '.', '.', '.', '.', '3', '2', '.', '.',},
				{'.', '5', '5', '.', '.', '4', '.', '.', '.',},
				{'.', '.', '.', '2', '.', '.', '.', '.', '.',},
				{'.', '.', '.', '.', '8', '.', '1', '.', '.',},
				{'.', '9', '.', '.', '3', '2', '.', '.', '.',},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.',},
				{'.', '.', '6', '.', '.', '.', '.', '.', '.',},
				{'.', '.', '.', '.', '.', '.', '1', '2', '.',}};
		boolean actual = isValidSudoku(board);
		assertEquals(false, actual);
	}

}
