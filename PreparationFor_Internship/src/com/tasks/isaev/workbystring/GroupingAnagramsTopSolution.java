package com.tasks.isaev.workbystring;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import java.util.*;

/*
 * Учитывая массив строк arrayStr, сгруппируйте анаграммы вместе. 
 * Вы можете вернуть ответ в любом порядке.
 * Анаграмма — это слово или фраза, образованная перестановкой букв другого слова или фразы, 
 * обычно с использованием всех исходных букв ровно один раз.
Example :
	Input: arrWords = ["eat","tea","tan","ate","nat","bat"]
	Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */

public class GroupingAnagramsTopSolution {

	public List<List<String>> groupAnagrams(String[] arrayStr) {

		List<List<String>> res = new AbstractList<List<String>>() {

			List<List<String>> result = null;

			public List<String> get(int index) {
				if (result == null) init();
				return result.get(index);
			}

			public int size() {
				if (result == null) init();
				return result.size();
			}

			private void init() {
				Map<String, List<String>> anagramMap = new HashMap<>();

				for (String word : arrayStr) {
					char[] arr = new char[26];
					char[] strArr = word.toCharArray();
					for (int i = 0; i < strArr.length; i++) {
						arr[strArr[i] - 'a']++;
					}

					String sortedWord = String.valueOf(arr);
					anagramMap.computeIfAbsent(sortedWord, k -> new ArrayList<>());
					anagramMap.get(sortedWord).add(word);
				}

				result = new ArrayList<>(anagramMap.values().size());
				for (Map.Entry<String, List<String>> anaToList : anagramMap.entrySet()) {
					result.add(anaToList.getValue());
				}
			}
		};
		return res;
	}

	@Test
	public void positiveTest1() {
		String[] arrWords = { "eat", "tea", "tan", "ate", "nat", "bat" };
		List<List<String>> actual = groupAnagrams(arrWords);
		List<List<String>> expecteds = new ArrayList<>();
		for (int i = 0; i < 3; ++i) {
			expecteds.add(new ArrayList<>());
		}

		expecteds.get(0).add("tan");
		expecteds.get(0).add("nat");
		expecteds.get(1).add("eat");
		expecteds.get(1).add("tea");
		expecteds.get(1).add("ate");
		expecteds.get(2).add("bat");

		assertEquals(expecteds, actual);

	}
}
