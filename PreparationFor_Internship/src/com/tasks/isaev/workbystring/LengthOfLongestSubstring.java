package com.tasks.isaev.workbystring;

/*
 * Учитывая строку s, найдите длину самой длинной подстрокиx без повторения символов.
 */

import java.util.*;

public class LengthOfLongestSubstring {
	public int lengthOfLongestSubstring(String s) {
		int i = 0, j = 0, max = 0, n = s.length();
		Set<Character> set = new HashSet<Character>();
		while (i < n && j < n) {
			if (set.contains(s.charAt(j))) {
				set.remove(s.charAt(i++));
			} else {
				set.add(s.charAt(j++));
				max = Math.max(max, set.size());
			}
		}
		return max;
	}

	public int lengthOfLongestSubstringWithUseCharArray(String s) {
		int i = 0, j = 0, max = 0, n = s.length();
		char[] arr = s.toCharArray();
		Set<Character> set = new HashSet<Character>();
		while (i < n && j < n) {
			if (set.contains(arr[j])) {
				set.remove(arr[i++]);
			} else {
				set.add(arr[j++]);
				max = Math.max(max, set.size());
			}
		}
		return max;
	}
}
