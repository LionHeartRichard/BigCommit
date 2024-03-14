package com.tasks.isaev.workmap;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class MagazineTopSolution {

	public boolean canConstruct(String ransomNote, String magazine) {
		int[] letters = new int[26];
		for (char ch : ransomNote.toCharArray()) {
			int i = magazine.indexOf(ch, letters[ch % 26]);
			if (i == -1) {
				return false;
			}
			letters[ch % 26] = i + 1;
		}
		return true;
	}

	@Test
	public void test1() {
		String ransomNote = "qwertyuiopasdfghjklzxcvbnm",
				magazine = "qwertyuiopasdfghjklzxcvbnm";
		assertEquals(true, canConstruct(ransomNote, magazine));
	}

	@Test
	public void test2() {
		String ransomNote = "a", magazine = "b";
		assertEquals(false, canConstruct(ransomNote, magazine));
	}

	@Test
	public void test3() {
		String ransomNote = "aa", magazine = "ab";
		assertEquals(false, canConstruct(ransomNote, magazine));
	}

	@Test
	public void test4() {
		String ransomNote = "aa", magazine = "aab";
		assertEquals(true, canConstruct(ransomNote, magazine));
	}
}
