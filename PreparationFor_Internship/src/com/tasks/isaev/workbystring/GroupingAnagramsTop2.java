package com.tasks.isaev.workbystring;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import java.util.*;
import java.util.Map.Entry;

public class GroupingAnagramsTop2 {

	public List<List<String>> groupAnagrams(String[] arrayAnagrams) {

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<List<String>> result = new ArrayList<List<String>>();
		for (String word : arrayAnagrams) {
			char[] tempArr = word.toCharArray();
			Arrays.sort(tempArr);
			String sortedWord = new String(tempArr);
			if (!map.containsKey(sortedWord)) {
				map.put(sortedWord, new ArrayList<String>());
			}
			map.get(sortedWord).add(word);
		}

		for (Map.Entry<String, List<String>> e : map.entrySet()) {
			result.add(e.getValue());
		}

		return result;
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
