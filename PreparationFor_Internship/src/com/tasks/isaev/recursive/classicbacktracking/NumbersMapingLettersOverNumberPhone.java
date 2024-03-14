package com.tasks.isaev.recursive.classicbacktracking;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class NumbersMapingLettersOverNumberPhone {

	private Map<Character, String[]> map = new HashMap<Character, String[]>();

	public List<String> letterCombinations(String digits) {

		List<String> res = new ArrayList<String>();
		if (digits.length() == 0) {
			return res;
		}

		map.put('2', new String[]{"a", "b", "c"});
		map.put('3', new String[]{"d", "e", "f"});
		map.put('4', new String[]{"g", "h", "i"});
		map.put('5', new String[]{"j", "k", "l"});
		map.put('6', new String[]{"m", "n", "o"});
		map.put('7', new String[]{"p", "q", "r", "s"});
		map.put('8', new String[]{"t", "u", "v"});
		map.put('9', new String[]{"w", "x", "y", "z"});

		char[] phone = digits.toCharArray();
		getCombination("", 0, phone, res);
		return res;
	}
	private void getCombination(String combination, int i, char[] arr,
			List<String> res) {
		if (i == arr.length) {
			res.add(combination);
			return;
		}
		String[] currentLetters = map.get(arr[i]);
		for (String letter : currentLetters) {
			getCombination(combination + letter, i + 1, arr, res);
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
