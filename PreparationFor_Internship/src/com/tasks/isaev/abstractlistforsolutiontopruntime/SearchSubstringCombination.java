package com.tasks.isaev.abstractlistforsolutiontopruntime;

/*
 * Вам дана строка s и массив строк words. 
 * Все строки words имеют одинаковую длину .
 * Объединенная подстрока — это s подстрока, 
 * содержащая все строки любой перестановки words объединенных.
 * Например, если words = ["ab","cd","ef"], 
 * то "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", и "efcdab"
 * все являются объединенными строками. 
 * "acdbef"не является объединенной подстрокой, поскольку 
 * не является объединением какой-либо перестановки words.
 * Верните начальные индексы всех объединенных подстрок в s. 
 * Вы можете вернуть ответ в любом порядке .
 */

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class SearchSubstringCombination {

	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new AbstractList<Integer>() {
			List<Integer> result = null;
			public Integer get(int index) {
				if (result == null) {
					result = mapping(s, words);
				}
				return result.get(index);
			}
			public int size() {
				if (result == null) {
					result = mapping(s, words);
				}
				return result.size();
			}
		};
		return res;
	}

	private List<Integer> mapping(String s, String[] words) {

		Map<String, List<Integer>> map = new HashMap<>();
		int n = words.length;
		int lengthSubS = words[0].length() * n;
		boolean[] beginCh = new boolean[26];
		boolean[] endCh = new boolean[26];

		for (int i = 0; i < words.length; i++) {
			String w = words[i];
			beginCh[w.charAt(0) - 'a'] = true;
			endCh[w.charAt(w.length() - 1) - 'a'] = true;
			map.putIfAbsent(w, new ArrayList<>());
			map.get(w).add(i);
		}

		List<Integer> currentRes = new ArrayList<>();

		for (int i = 0; i <= s.length() - lengthSubS; i++) {

			if (beginCh[s.charAt(i) - 'a']
					&& endCh[s.charAt(i + lengthSubS - 1) - 'a']) {

				boolean contains = consider(i, i + lengthSubS, s, map, n);

				if (contains) {
					currentRes.add(i);
				}
			}
		}

		return currentRes;
	}

	private boolean consider(int begin, int end, String s,
			Map<String, List<Integer>> words, int size) {

		if (words.size() == 1) {
			String value = concat(words.keySet().iterator().next(), size);
			return value.equals(s.substring(begin, end));
		}

		int len = (end - begin) / size;
		boolean[] visited = new boolean[size];

		for (int i = begin; i < end; i += len) {

			String candidate = s.substring(i, i + len);

			if (words.containsKey(candidate)) {
				int idx = getUnvisited(words.get(candidate), visited);
				if (idx >= 0) {
					visited[idx] = true;
					continue;
				}
			}

			return false;
		}

		return true;
	}

	private String concat(String key, int n) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			builder.append(key);
		}
		return builder.toString();
	}

	private int getUnvisited(List<Integer> indexList, boolean[] visited) {
		for (int i = 0; i < indexList.size(); i++) {
			if (!visited[indexList.get(i)]) {
				return indexList.get(i);
			}
		}
		return -1;
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
