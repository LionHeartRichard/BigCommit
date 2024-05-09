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

	private String endWord;
	private Set<String> cache;

	public int ladderLength(String begin, String end, List<String> wordList) {

		Set<String> primarySource = new HashSet<String>(wordList);
		if (!primarySource.contains(end))
			return 0;

		endWord = end;

		cache = new HashSet<String>();
		cache.add(begin);
		primarySource.remove(begin);

		BFS(begin, primarySource);

		if (cache.contains(endWord))
			return cache.size();
		return 0;

	}

	private void BFS(String current, Set<String> primarySource) {
		if (primarySource.size() == 0)
			return;
		if (current.equals(endWord))
			return;
		if (isValid(current)) {
			cache.add(endWord);
			return;
		}

		String reference = getValidWord(current, primarySource);
		if (reference != null) {
			cache.add(reference);
			primarySource.remove(reference);
			BFS(reference, primarySource);
		}
	}

	private boolean isValid(String current) {
		int count = 0;

		for (int i = 0; i < current.length(); ++i) {
			if (endWord.charAt(i) != current.charAt(i))
				++count;
			if (count > 1)
				return false;
		}

		if (count == 1)
			return true;
		return false;
	}

	private String getValidWord(String word, Set<String> primarySource) {
		for (String reference : primarySource) {
			int count = 0;
			for (int i = 0; i < reference.length(); ++i) {
				if (reference.charAt(i) != word.charAt(i))
					++count;
				if (count > 1)
					break;
			}
			if (count == 1)
				return reference;
		}
		return null;
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
		List<String> wordList = Arrays.asList("a", "b", "c");
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
