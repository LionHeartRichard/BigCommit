package com.tasks.isaev.workmap;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class CombinationSubstringInString {

	public List<Integer> findSubstring(String source, String[] words) {
		List<Integer> res = new ArrayList<Integer>();
		int sizeWord = words[0].length();
		int limit = sizeWord * words.length;
		char[] carry = source.toCharArray();
		if (carry.length < limit) {
			return res;
		}
		int count = 1;
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String s : words) {
			if (!map.containsKey(s)) {
				map.put(s, 1);
			} else {
				count = map.get(s);
				map.put(s, count + 1);
			}
		}
		int i = 0;
		while (i + limit <= source.length()) {
			String findSubstring = source.substring(i, i + limit);
			if (!findSubstring.isEmpty()) {
				if (combination(findSubstring, map, 0, sizeWord)) {
					res.add(i);
					// i += sizeWord;
				} // else {
				++i;
				// }
			} else {
				break;
			}
		}
		return res;
	}

	private boolean combination(String findSubstring, Map<String, Integer> map,
			int begin, int sizeWord) {
		boolean flag = false;
		Map<String, Integer> mapWords = new HashMap<String, Integer>(map);
		while (begin < findSubstring.length()) {
			flag = true;
			String temp = findSubstring.substring(begin, begin + sizeWord);

			if (!mapWords.containsKey(temp)) {
				return false;
			}
			int count = mapWords.get(temp);
			if (count <= 0) {
				return false;
			}
			begin += sizeWord;
			mapWords.put(temp, count - 1);
		}
		for (Map.Entry<String, Integer> e : mapWords.entrySet()) {
			if (e.getValue() != 0) {
				return false;
			}
		}
		return flag;
	}

	@Test
	public void test1() {
		String source = "barfoothefoobarman";
		String[] words = {"foo", "bar"};
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(0);
		expected.add(9);
		List<Integer> actual = findSubstring(source, words);
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		String source = "wordgoodgoodgoodbestword";
		String[] words = {"word", "good", "best", "word"};
		List<Integer> actual = findSubstring(source, words);
		List<Integer> expected = new ArrayList<Integer>();
		assertEquals(expected, actual);
	}

	@Test
	public void test3() {
		String source = "barfoofoobarthefoobarman";
		String[] words = {"bar", "foo", "the"};
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(6);
		expected.add(9);
		expected.add(12);
		List<Integer> actual = findSubstring(source, words);
		assertEquals(expected, actual);
	}

	@Test
	public void test4() {
		String source = "wordgoodgoodgoodbestword";
		String[] words = {"word", "good", "best", "good"};
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(8);
		List<Integer> actual = findSubstring(source, words);
		assertEquals(expected, actual);
	}

	@Test
	public void test5() {
		String source = "aaaaaaaaaaaaaa";
		String[] words = {"aa", "aa"};
		List<Integer> expected = new ArrayList<Integer>();
		for (int i = 0; i < 11; ++i) {
			expected.add(i);
		}
		List<Integer> actual = findSubstring(source, words);
		assertEquals(expected, actual);
	}

	@Test
	public void test6() {
		String source = "bcabbcaabbccacacbabccacaababcbb";
		String[] words = {"c", "b", "a", "c", "a", "a", "a", "b", "c"};
		List<Integer> actual = findSubstring(source, words);
		List<Integer> expected = Arrays.asList(6, 16, 17, 18, 19, 20);
		assertEquals(expected, actual);
	}
}
