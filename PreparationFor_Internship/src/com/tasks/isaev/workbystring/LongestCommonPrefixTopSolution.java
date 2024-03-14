package com.tasks.isaev.workbystring;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestCommonPrefixTopSolution {

	public String longestCommonPrefix(String[] source) {
		String prefix = source[0];
		for (int i = 1; i < source.length; i++) {
			while (!source[i].startsWith(prefix)) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) {
					return "";
				}
			}
		}
		return prefix;
	}

	@Test
	public void test1() {
		String[] source = {"flow", "flower", "flight"};
		String actual = longestCommonPrefix(source);
		String expected = "fl";
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		String[] source = {"dog", "racecar", "car"};
		String actual = longestCommonPrefix(source);
		String expected = "";
		assertEquals(expected, actual);
	}

	@Test
	public void test3() {
		String[] source = {""};
		String actual = longestCommonPrefix(source);
		String expected = "";
		assertEquals(expected, actual);
	}
}
