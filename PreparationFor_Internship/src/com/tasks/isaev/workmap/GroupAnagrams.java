package com.tasks.isaev.workmap;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;
/*
 * Учитывая массив строк strs, сгруппируйте анаграммы вместе. 
 * Вы можете вернуть ответ в любом порядке.
 * Анаграмма — это слово или фраза, образованная перестановкой букв другого слова или фразы, 
 * обычно с использованием всех исходных букв ровно один раз .
 */
public class GroupAnagrams {

	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String word : strs) {
			char[] temp = word.toCharArray();
			Arrays.sort(temp);
			String keySortingWord = new String(temp);
			if (!map.containsKey(keySortingWord)) {
				map.put(keySortingWord, new ArrayList<>());
			}
			map.get(keySortingWord).add(word);
		}
		return new ArrayList<>(map.values());
	}

	@Test
	public void positiveTest1() {
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		List<List<String>> actual = groupAnagrams(strs);
		List<List<String>> expected = new ArrayList<List<String>>();
		expected.add(Arrays.asList(new String[]{"eat", "tea", "ate"}));
		expected.add(Arrays.asList(new String[]{"bat"}));
		expected.add(Arrays.asList(new String[]{"tan", "nat"}));
		assertEquals(expected, actual);
	}
}
