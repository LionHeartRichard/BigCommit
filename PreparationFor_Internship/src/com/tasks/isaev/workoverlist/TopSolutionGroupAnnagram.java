package com.tasks.isaev.workoverlist;
import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

/*
 * Учитывая массив строк strs, сгруппируйте анаграммы вместе. 
 * Вы можете вернуть ответ в любом порядке.
 * Анаграмма — это слово или фраза, образованная перестановкой букв другого слова или фразы, 
 * обычно с использованием всех исходных букв ровно один раз .
 */

public class TopSolutionGroupAnnagram {

	private List<List<String>> ans;

	public List<List<String>> groupAnagrams(String[] strs) {
		
		return new AbstractList<List<String>>() {

			public List<String> get(int index) {
				if (ans == null)
					init();
				return ans.get(index);
			}

			public int size() {
				if (ans == null)
					init();
				return ans.size();
			}

			public void init() {
				Map<String, List<String>> group = new HashMap<>();
				for (String s : strs) {
					int[] count = new int[26];// 26 букв АНГЛИСКОГО АЛФАВИТА
					StringBuilder sb = new StringBuilder();
					for (char c : s.toCharArray()) {
						count[c - 'a']++;
					}
					for (int i = 0; i < 26; i++) {
						if (count[i] != 0)
							sb.append('a' + i);
						sb.append(count[i]);
					}
					String key = sb.toString();
					group.computeIfAbsent(key, k -> new ArrayList<String>())
							.add(s);
				}
				ans = new ArrayList<>(group.values());
			}
		};
	}

	@Test
	public void positiveTest1() {
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		List<List<String>> actual = groupAnagrams(strs);
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
