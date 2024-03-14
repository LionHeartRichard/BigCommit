package com.tasks.isaev.workbystring;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.*;

/*
 * Напишите функцию для поиска самой длинной строки общего префикса 
 * среди массива строк.Если общего префикса нет, верните пустую строку "".
 */

public class LongestCommonPrefix {

	public String longestCommonPrefix(String[] source) {
		int n = source.length;
		if (n == 0) {
			return "";
		}

		int min = findMinString(source, n);
		if (min == 0) {
			return "";
		}

		char checkSymbol = source[0].charAt(0);
		for (String s : source) {
			if (s.charAt(0) != checkSymbol)
				return "";
		}

		StringBuilder res = new StringBuilder();
		res.append(checkSymbol);
		int j = 1;

		while (j < min) {
			for (int i = 1; i < n; ++i) {
				checkSymbol = source[i - 1].charAt(j);
				if (source[i].charAt(j) != checkSymbol) {
					return res.toString();
				}
			}
			res.append(checkSymbol);
			++j;
		}
		return res.toString();
	}

	private int findMinString(String[] source, int n) {
		int min = Integer.MAX_VALUE;
		for (String s : source) {
			if (min > s.length()) {
				min = s.length();
			}
		}
		return min;
	}

	@Test
	public void test1() {
		String[] source = {"flower", "flow", "flight"};
		String actual = longestCommonPrefix(source);
		String expected = "fl";
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		String[] source = {"dog", "racecar", "car"};
		String actual = longestCommonPrefix(source);
		String expected = "";
		assertEquals(expected, actual);
	}

	@Test
	public void test3() {
		String[] source = {""};
		String actual = longestCommonPrefix(source);
		String expected = "";
		assertEquals(expected, actual);
	}
}
