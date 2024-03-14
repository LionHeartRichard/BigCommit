package com.tasks.isaev.sudoku;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.junit.jupiter.api.Test;

public class SudokuWithSet {
	public boolean isValidSudoku(char[][] board) {
		Set<Character> rows = new HashSet<Character>();
		Set<Character> columns = new HashSet<Character>();

		boolean check = true;

		for (int i = 0; i < 9; ++i) {
			rows.clear();
			columns.clear();
			for (int j = 0; j < 9; ++j) {
				char chRow = board[i][j];
				char chColumn = board[j][i];
				if (chRow != '.' && !rows.add(chRow) || chColumn != '.' && !columns.add(chColumn)) {
					return false;
				}
			}
			if (i % 3 == 0) {
				check = checkSmallQuare(board, i);
			}
		}

		return check;

	}

	private boolean checkSmallQuare(char[][] board, int begin) {
		Set<Character> quare = new HashSet<Character>();
		for (int k = 0; k < 9; k += 3) {
			quare.clear();
			System.out.println("----------------------");
			for (int i = begin; i < begin + 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					int idx = j + k;
					//System.out.println("row: " + i + " column: " + idx);
					char ch = board[i][idx];
					System.out.println(ch);
					if (ch != '.' && !quare.add(ch)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Test
	public void positiveTest1() {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
						   { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, 
						   { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
						   { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, 
						   { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
						   { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, 
						   { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
						   { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, 
						   { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		boolean actual = isValidSudoku(board);
		assertEquals(true, actual);
	}
}
