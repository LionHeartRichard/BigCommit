package com.tasks.isaev.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

import java.util.*;

/*
Вам дана m x n матрица board, содержащая символы 'X' и 'O', захватите области , которые окружены :

Соединение : Ячейка соединяется с соседними ячейками по горизонтали или вертикали.
Регион : Чтобы сформировать регион, соедините все 'O' ячейки.
Окружить : регион окружен 'X'ячейками, если вы можете соединить регион с 'X'ячейками и ни одна из ячеек региона не находится на краю board.
Окруженная область захватывается путем замены всех 'O's на 'X's во входной матрице board.

*/

public class SurroundedRegions {

	private int rows = 0;
	private int columns = 0;

	public char[][] solve(char[][] board) {
		rows = board.length;
		columns = board[0].length;

		if (rows == 1 && columns == 1)
			return board;

		for (int i = 1; i < rows - 1; ++i) {
			check(board, i);
		}
		return board;
	}

	private void check(char[][] board, int i) {
		for (int j = 1; j < columns - 1; ++j) {
			board[i][j] = 'X';
		}

	}

	@Test
	public void test1() {
		char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'},
				{'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};

		char[][] actual = solve(board);
		char[][] expected = {{'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'},
				{'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X'}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test2() {
		char[][] board = {{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};

		char[][] actual = solve(board);
		char[][] expected = {{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};
		assertArrayEquals(expected, actual);
	}
}
