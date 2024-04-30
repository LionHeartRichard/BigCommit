package com.tasks.isaev.workbystring;

public class LengthOfLastWord {
	public int lengthOfLastWord(String s) {
		int count = 0;
		String temp = s.trim();
		for (int i = temp.length() - 1; i >= 0; --i) {
			if (temp.charAt(i) == ' ') {
				return count;
			} else {
				++count;
			}
		}
		return count;
	}
}
