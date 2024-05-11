package com.bfs;

import static org.junit.Assert.assertEquals;

/* 
 * A transformation sequence from word beginWord to word endWord using a dictionary 
 * wordList is a 
 * sequence of words beginWord -> s1 -> s2 -> ... -> sk.
 */

import java.util.*;

import org.junit.jupiter.api.Test;

public class LadderLength {

	private Set<String> cache;

	public int ladderLength(String begin, String end, List<String> wordList) {
		Set<String> primary = new HashSet<String>(wordList);
		if (!primary.contains(end)) {
			return 0;
		}

		cache = new HashSet<String>();

		primary.remove(begin);
		primary.remove(end);

		BFS(begin, primary, end);

		if (cache.size() != 0)
			return cache.size();
		return 0;
	}

	private void BFS(String current, Set<String> primary, String source) {

		if (cache.contains(source))
			return;

		if (cache.contains(current))
			return;
		cache.add(current);

		if (isValid(source, current)) {
			cache.add(source);
			return;
		}

		current = getValidStr(current, primary);
		if (current != null) {
			primary.remove(current);
			BFS(current, primary, source);
		}
	}

	private String getValidStr(String actual, Set<String> primary) {
		Set<String> temp = new HashSet<String>();
		temp.addAll(primary);
		for (String expected : primary) {
			if (isValid(expected, actual)) {// проверка дополнительная на более
											// короткий путь до последнего звена
				return expected;
			}
		}
		return null;
	}

	private boolean isValid(String reference, String current) {
		int count = 0;
		for (int i = 0; i < reference.length(); ++i) {
			if (reference.charAt(i) != current.charAt(i))
				++count;
			if (count > 1)
				return false;
		}
		return true;
	}

	@Test
	public void test1() {

		String beginWord = "hit", endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log",
				"cog");

		// "hit" -> "hot" -> "dot" -> "dog" -> cog"

		int actual = ladderLength(beginWord, endWord, wordList);
		assertEquals(5, actual);

	}

	@Test
	public void test2() {

		String beginWord = "hit", endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot",
				"log");
		int actual = ladderLength(beginWord, endWord, wordList);
		assertEquals(0, actual);
	}

	@Test
	public void test3() {
		String beginWord = "a", endWord = "c";
		List<String> wordList = Arrays.asList("a", "i", "y", "b", "c");
		int actual = ladderLength(beginWord, endWord, wordList);
		assertEquals(2, actual);
	}

	@Test
	public void test4() {
		String beginWord = "hot", endWord = "dog";
		List<String> wordList = Arrays.asList("hot", "dog", "cog", "pot",
				"dot");
		int actual = ladderLength(beginWord, endWord, wordList);
		assertEquals(3, actual);
	}
}
