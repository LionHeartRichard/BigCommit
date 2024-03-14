package com.tasks.isaev.workoverlist;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.junit.jupiter.api.Test;

/*
 * Учитывая массив строк words, сгруппируйте анаграммы вместе. 
 * Вы можете вернуть ответ в любом порядке.
 * Анаграмма — это слово или фраза, образованная перестановкой букв другого слова или фразы, 
 * обычно с использованием всех исходных букв ровно один раз.
 */

public class GroupAnnagramTopRezult {

	private List<List<String>> listAns;

	public List<List<String>> groupAnagrams(String[] words) {
		return new AbstractList<List<String>>() {

			@Override
			public int size() {
				if (listAns == null) {
					init();
				}
				return listAns.size();
			}

			@Override
			public List<String> get(int index) {
				if (listAns == null) {
					init();
				}
				return listAns.get(index);
			}

			public void init() {
				Map<String, List<String>> map = new HashMap<>();
				for (String word : words) {
					char[] temp = word.toCharArray();
					Arrays.sort(temp);
					String keyWordSort = new String(temp);
					map.putIfAbsent(keyWordSort, new ArrayList<String>());
					map.get(keyWordSort).add(word);
				}
				listAns = new ArrayList<>(map.values());
			}
		};
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
