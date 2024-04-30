package com.medium;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class CombinationPhoneNumber {

	Map<Character, Character[]> map = new HashMap<Character, Character[]>();

	public List<String> letterCombinations(String digits) {

		if (digits.length() == 0)
			return Collections.emptyList();

		List<String> res = new AbstractList<String>() {

			List<String> result = null;

			@Override
			public int size() {
				if (result == null)
					result = mapping(digits);
				return result.size();
			}

			@Override
			public String get(int index) {
				if (result == null)
					result = mapping(digits);
				return result.get(index);
			}
		};
		return res;
	}

	private List<String> mapping(String digits) {
		List<String> carry = new ArrayList<String>();

		char[] phone = digits.toCharArray();

		map.put('2', new Character[]{'a', 'b', 'c'});
		map.put('3', new Character[]{'d', 'e', 'f'});
		map.put('4', new Character[]{'g', 'h', 'i'});
		map.put('5', new Character[]{'j', 'k', 'l'});
		map.put('6', new Character[]{'m', 'n', 'o'});
		map.put('7', new Character[]{'p', 'q', 'r', 's'});
		map.put('8', new Character[]{'t', 'u', 'v'});
		map.put('9', new Character[]{'w', 'x', 'y', 'z'});

		backtracking("", 0, phone, carry);
		return carry;
	}

	private void backtracking(String combination, int i, char[] phone,
			List<String> carry) {
		if (i == phone.length) {
			carry.add(combination);
			return;
		}
		Character[] currentLetters = map.get(phone[i]);
		for (char token : currentLetters) {
			backtracking(combination + token, i + 1, phone, carry);
		}
	}

	@Test
	public void positiveTest1() {
		String digits = "23";
		String[] expectedArr = "ad,ae,af,bd,be,bf,cd,ce,cf".split(",");
		List<String> actual = letterCombinations(digits);
		List<String> expected = new ArrayList<String>();
		Arrays.stream(expectedArr).forEach(v -> expected.add(v));
		assertEquals(expected, actual);
	}

}
