package com.tasks.isaev.sudoku;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class SudokuWithArrayBoolean {
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			boolean[] rowCheck = new boolean[9];
			boolean[] colCheck = new boolean[9];
			boolean[] boxCheck = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
				} else if (rowCheck[board[i][j] - '1'])
					return false;
				else
					rowCheck[board[i][j] - '1'] = true;
				if (board[j][i] == '.') {
				} else if (colCheck[board[j][i] - '1'])
					return false;
				else
					colCheck[board[j][i] - '1'] = true;
				int m = i / 3 * 3 + j / 3;
				int n = i % 3 * 3 + j % 3;
				if (board[m][n] == '.') {
				} else if (boxCheck[board[m][n] - '1'])
					return false;
				else
					boxCheck[board[m][n] - '1'] = true;
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
