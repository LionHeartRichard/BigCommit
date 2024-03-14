package com.tasks.isaev.workmap;

/*
 * Учитывая две строки ransomNoteи magazine, верните trueif, который ransomNote 
 * может быть создан с использованием букв from magazineи false иначе.
 * Каждую букву magazine можно использовать только один раз ransomNote.
 */

public class Magazine {
	public boolean canConstruct(String ransomNote, String magazine) {
		int[] countLetters = new int[26];
		for (char ch : magazine.toCharArray()) {
			++countLetters[ch - 'a'];
		}

		for (char ch : ransomNote.toCharArray()) {
			if (countLetters[ch - 'a'] == 0) {
				return false;
			}
			--countLetters[ch - 'a'];
		}
		return true;
	}
}
