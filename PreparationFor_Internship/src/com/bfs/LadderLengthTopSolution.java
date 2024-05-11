package com.bfs;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class LadderLengthTopSolution {

	public int ladderLength(String beginWord, String endWord,
			List<String> wordList) {
		Set<String> primarySource = new HashSet<String>(wordList);
		if (!primarySource.contains(endWord))
			return 0;

		Set<String> forwardSet = new HashSet<String>();
		Set<String> backwardSet = new HashSet<String>();
		forwardSet.add(beginWord);
		backwardSet.add(endWord);
		primarySource.remove(endWord);
		primarySource.remove(beginWord);

		return transform(forwardSet, backwardSet, primarySource);
	}

	public int transform(Set<String> forwardSet, Set<String> backwardSet,
			Set<String> primarySource) {

		Set<String> carry = new HashSet<String>();
		for (String fs : forwardSet) {
			char[] word = fs.toCharArray();
			for (int i = 0; i < word.length; ++i) {
				for (int ch = 97; ch <= 122; ++ch) {// перебираю английский
													// алфовит
					char origin = word[i];
					word[i] = (char) ch;
					String target = String.valueOf(word);
					if (backwardSet.contains(target))
						return 2;
					if (primarySource.contains(target)
							&& !forwardSet.contains(target)) {
						primarySource.remove(target);
						carry.add(target);
					}
					word[i] = origin;
				}
			}
		}
		if (carry.size() == 0)
			return 0;
		forwardSet = carry;

		int result = forwardSet.size() > backwardSet.size()
				? transform(backwardSet, forwardSet, primarySource)
				: transform(forwardSet, backwardSet, primarySource);
		return result == 0 ? 0 : result + 1;
	}

	@Test
	public void test1() {

		String beginWord = "hit", endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log",
				"cog");

		// "hit" -> "hot" -> "dot" -> "dog" -> "cog"

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
