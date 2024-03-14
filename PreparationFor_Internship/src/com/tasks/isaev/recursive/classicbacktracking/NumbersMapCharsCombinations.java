package com.tasks.isaev.recursive.classicbacktracking;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class NumbersMapCharsCombinations {

	private String[] mapping = {"abc", "def", "ghi", "jkl", "mno", "pqrs",
			"tuv", "wxyz"};
	private List<String> result = new ArrayList<String>();

	public List<String> letterCombinations(String digits) {
		if (digits.length() == 0) {
			return Collections.emptyList();
		}
		getCombination("", digits);
		return result;
	}

	private void getCombination(String combination, String nextLetter) {
		if (nextLetter.length() == 0) {
			result.add(combination);
			return;
		}
		String letters = mapping[nextLetter.charAt(0) - '2'];
		for (char letter : letters.toCharArray()) {
			getCombination(combination + letter, nextLetter.substring(1));
		}
	}

	@Test
	public void positiveTest1() {
		String digits = "23";
		String[] expectedArr = "ad,ae,af,bd,be,bf,cd,ce,cf".split(",");
		List<String> actual = letterCombinations(digits);
		List<String> expected = new ArrayList<String>();
		for (String s : expectedArr) {
			expected.add(s);
		}
		assertEquals(expected, actual);
	}
}
