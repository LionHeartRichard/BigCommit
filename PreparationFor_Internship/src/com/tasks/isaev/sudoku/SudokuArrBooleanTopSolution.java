package com.tasks.isaev.sudoku;

import static org.junit.Assert.assertEquals;
import java.util.*;

import org.junit.jupiter.api.Test;

public class SudokuArrBooleanTopSolution {

	public boolean isValidSudoku(char[][] board) {

		for (int i = 0; i < 9; ++i) {

			boolean[] rows = new boolean[9];
			boolean[] columns = new boolean[9];
			boolean[] box = new boolean[9];

			for (int j = 0; j < 9; ++j) {
				char valueRow = board[i][j];
				if (valueRow != '.') {
					if (rows[valueRow - '1']) return false;
					rows[valueRow - '1'] = true;
				}

				char valueColumn = board[j][i];
				if (valueColumn != '.') {
					if (columns[valueColumn - '1']) return false;
					columns[valueColumn - '1'] = true;
				}

				int m = i / 3 * 3 + j / 3;
				int n = i % 3 * 3 + j % 3;
				char valueBox = board[m][n];
				if (valueBox != '.') {
					if (box[valueBox - '1']) return false;
					box[valueBox - '1'] = true;
				}
			}
		}
		return true;
	}

	private char[][] converterStrSudokuInArrChars(String str) {
		char[][] arrSudoku = new char[9][9];
		Set<Character> set = new HashSet<Character>();
		set.add('.');
		for (int i = 1; i < 10; ++i) {
			char ch = (char) (i + '0');
			set.add(ch);
		}

		Queue<Character> que = new ArrayDeque<Character>();
		for (char i : str.toCharArray()) {
			if (set.contains(i)) {
				que.add(i);
			}
		}

		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (!que.isEmpty()) {
					arrSudoku[i][j] = que.remove();
				}
			}
		}

		return arrSudoku;
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

	@Test
	public void positiveTest2() {
		String str = "[[.,1,.,6,.,.,.,.,.],[.,.,.,.,.,3,2,.,.],[.,5,5,.,.,4,.,.,.],[.,.,.,2,.,.,.,.,.],[.,.,.,.,8,.,1,.,.],[.,9,.,.,3,2,.,.,.],[.,.,.,.,.,.,.,.,.],[.,.,6,.,.,.,.,.,.],[.,.,.,.,.,.,1,2,.]]";

		char[][] arr = converterStrSudokuInArrChars(str);
		for (int i = 0; i < 9; ++i) {
			String row = "";
			for (int j = 0; j < 9; ++j) {
				row += arr[i][j] + " ";
			}
			System.out.println(row);
		}
		boolean actual = isValidSudoku(arr);
		assertEquals(false, actual);
	}
}
