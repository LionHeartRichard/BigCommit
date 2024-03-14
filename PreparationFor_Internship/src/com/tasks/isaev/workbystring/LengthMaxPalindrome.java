package com.tasks.isaev.workbystring;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class LengthMaxPalindrome {

	private int maxLen = 0;
	private int begin = 0;

	public String longestPalindrome(String s) {
		char[] input = s.toCharArray();
		if (s.length() < 2) {
			return s;
		}

		for (int i = 0; i < input.length; ++i) {
			expandPalindrome(input, i, i);
			expandPalindrome(input, i, i + 1);
		}
		return s.substring(begin, begin + maxLen);
	}

	public void expandPalindrome(char[] array, int rightIdx, int leftIdx) {
		while (rightIdx >= 0 && leftIdx < array.length
				&& array[rightIdx] == array[leftIdx]) {
			--rightIdx;
			++leftIdx;
		}
		if (maxLen < leftIdx - rightIdx - 1) {
			maxLen = leftIdx - rightIdx - 1;
			begin = rightIdx + 1;
		}
	}

	@Test
	public void positiveTest1() {
		String s = "babad";
		String actual = longestPalindrome(s);
		assertEquals(true, "bab".equals(actual));
	}

	@Test
	public void positiveTest2() {
		String s = "cbbd";
		String actual = longestPalindrome(s);
		assertEquals(true, "bb".equals(actual));
	}

	@Test
	public void positiveTest3() {
		String s = "fgaaaaaaaio";
		String actual = longestPalindrome(s);
		assertEquals(true, "aaaaaaa".equals(actual));
	}

	@Test
	public void positiveTest4() {
		String s = "0921kkttuuiiiiiiiiuuttsdfg";
		String actual = longestPalindrome(s);
		assertEquals(true, "ttuuiiiiiiiiuutt".equals(actual));
	}
}
