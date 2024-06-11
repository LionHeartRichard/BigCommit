package com.bfs;

/*
 * Последовательность преобразования слова beginWordв слово endWordс помощью словаря wordList— это последовательность слов такая, что:beginWord -> s1 -> s2 -> ... -> sk
Каждая соседняя пара слов отличается одной буквой.
Каждый for есть в формате . Обратите внимание, что это не обязательно должно быть в формате .si1 <= i <= kwordListbeginWordwordList
sk == endWord
Учитывая два слова, beginWordи endWordи словарь wordList, верните количество слов в кратчайшей последовательности преобразования от beginWord до endWordили, 0если такой последовательности не существует.

Пример 1:

Ввод: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 Вывод: 5
 Объяснение: Одна кратчайшая последовательность преобразований это «hit» -> «hot» -> «dot» -> «dog» -> cog», длина которого составляет 5 слов.
Пример 2:

Ввод: BeginWord = "hit", endWord = "cog", wordList = ["hot", "dot", "dog", "lot", "log"]
 Вывод: 0
 Объяснение: EndWord "cog" отсутствует. wordList, поэтому не существует допустимой последовательности преобразования.
 */

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class LadderLengthRepeat {

	public int ladderLength(String beginWord, String endWord,
			List<String> wordList) {

		Set<String> primarySet = new HashSet<String>(wordList);
		if (!primarySet.contains(endWord))
			return 0;

		Set<String> forwardSet = new HashSet<String>();
		Set<String> backwardSet = new HashSet<String>();

		forwardSet.add(beginWord);
		primarySet.remove(beginWord);

		backwardSet.add(endWord);
		primarySet.remove(endWord);

		return transform(forwardSet, backwardSet, primarySet);
	}

	private int transform(Set<String> forwardSet, Set<String> backwardSet,
			Set<String> primarySet) {

		Set<String> carry = new HashSet<>();
		for (String fs : forwardSet) {

			char[] word = fs.toCharArray();
			for (int i = 0; i < word.length; ++i) {
				for (int letter = 'a'; letter <= 'z'; ++letter) {

					char originLetter = word[i];
					word[i] = (char) letter;

					String target = String.valueOf(word);
					if (backwardSet.contains(target))
						return 2;

					if (primarySet.contains(target)
							&& !forwardSet.contains(target)) {
						
						primarySet.remove(target);
						carry.add(target);
					}

					word[i] = originLetter;
				}
			}
		}

		if (carry.size() == 0)
			return 0;
		forwardSet = carry;

		int result = forwardSet.size() > backwardSet.size()
				? transform(backwardSet, forwardSet, primarySet)
				: transform(forwardSet, backwardSet, primarySet);

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
