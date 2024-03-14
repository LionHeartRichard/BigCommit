package com.tasks.isaev.workbystring;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;
/*
 * Учитывая массив строк strs, сгруппируйте анаграммы вместе. 
 * Вы можете вернуть ответ в любом порядке.
 * Анаграмма — это слово или фраза, образованная перестановкой букв другого слова или фразы, 
 * обычно с использованием всех исходных букв ровно один раз.
Example :
	Input: arrWords = ["eat","tea","tan","ate","nat","bat"]
	Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */

public class GroupingAnagrams {

	public List<List<String>> groupAnagrams(String[] arrWords) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String word : arrWords) {
			char[] tempArr = word.toCharArray();
			Arrays.sort(tempArr);
			String sortedWord = new String(tempArr);
			if (!map.containsKey(sortedWord)) {
				map.put(sortedWord, new ArrayList<String>());
			}
			map.get(sortedWord).add(word);
		}
		return new ArrayList<>(map.values());
	}

	@Test
	public void positiveTest1() {
		String[] arrWords = { "eat", "tea", "tan", "ate", "nat", "bat" };
		List<List<String>> actual = groupAnagrams(arrWords);
		List<List<String>> expecteds = new ArrayList<>();
		for (int i = 0; i < 3; ++i) {
			expecteds.add(new ArrayList<>());
		}
		
		expecteds.get(0).add("eat");
		expecteds.get(0).add("tea");
		expecteds.get(0).add("ate");
		expecteds.get(1).add("bat");
		expecteds.get(2).add("tan");
		expecteds.get(2).add("nat");
		
		assertEquals(expecteds, actual);

	}
}
