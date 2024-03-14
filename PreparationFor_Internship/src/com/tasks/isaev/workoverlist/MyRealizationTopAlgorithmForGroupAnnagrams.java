package com.tasks.isaev.workoverlist;

/*
 * Учитывая массив строк words, сгруппируйте анаграммы вместе. 
 * Вы можете вернуть ответ в любом порядке.
 * Анаграмма — это слово или фраза, образованная перестановкой букв другого слова или фразы, 
 * обычно с использованием всех исходных букв ровно один раз.
 */

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class MyRealizationTopAlgorithmForGroupAnnagrams {

	public List<List<String>> groupAnagrams(String[] words) {
		Map<String, List<String>> mapAns = new HashMap<String, List<String>>();
		for (String word : words) {
			int[] letters = new int[26];
			for (char ch : word.toCharArray()) {
				letters[ch - 'a']++;
			}

			StringBuilder builderHashCode = new StringBuilder();
			for (int i = 0; i < 26; ++i) {
				if (letters[i] != 0) {
					builderHashCode.append('a' + i);
				}
				builderHashCode.append(letters[i]);
			}
			String key = builderHashCode.toString();
			mapAns.computeIfAbsent(key, k -> new ArrayList<String>()).add(word);
		}
		return new ArrayList<>(mapAns.values());
	}

	@Test
	public void positiveTest1() {
		String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
		List<List<String>> actual = groupAnagrams(words);
		List<List<String>> expected = new ArrayList<List<String>>();
		expected.add(Arrays.asList(new String[]{"bat"}));
		expected.add(Arrays.asList(new String[]{"tan", "nat"}));
		expected.add(Arrays.asList(new String[]{"eat", "tea", "ate"}));
		assertEquals(expected, actual);
	}

	@Test
	public void positiveTest2() {
		String[] words = {"ddddddddddg", "dgggggggggg"};
		List<List<String>> actual = groupAnagrams(words);
		List<List<String>> expected = new ArrayList<List<String>>();
		expected.add(Arrays.asList(new String[]{"dgggggggggg"}));
		expected.add(Arrays.asList(new String[]{"ddddddddddg"}));
		assertEquals(expected, actual);
	}

}
