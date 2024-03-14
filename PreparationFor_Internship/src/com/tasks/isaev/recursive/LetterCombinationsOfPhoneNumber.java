package com.tasks.isaev.recursive;
/*
 * Учитывая строку, содержащую цифры, начиная с 2-9включительно, верните все возможные комбинации букв, 
 * которые может представлять число. Верните ответ в любом порядке.

Соответствие цифр буквам (как на кнопках телефона) приведено ниже.
        2 - abc
		3 - def
		4 - ghi
		5 - jkl
		6 - mno
		7 - pqrs
		8 - tuv
		9 - wxyz
Обратите внимание, что 1 не соответствует никаким буквам.
Пример:
  Ввод: цифры = "23"
  Вывод: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class LetterCombinationsOfPhoneNumber {

	public List<String> letterCombinations(String digits) {

		if (digits.isEmpty()) {
			return Collections.emptyList();
		}

		String[] phoneMap = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

		List<String> output = new ArrayList<>();
		backtrack("", digits, phoneMap, output);

		return output;
	}

	private void backtrack(String combination, String nextDigits, String[] phoneMap, List<String> output) {
		if (nextDigits.isEmpty()) {
			output.add(combination);
		} else {
			char pivot = nextDigits.charAt(0);
			int index = pivot - '2';
			String letters = phoneMap[index];
			for (char letter : letters.toCharArray()) {
				backtrack(combination + letter, nextDigits.substring(1), phoneMap, output);
			}
		}
	}

	@Test
	public void positiveTest1() {
		String digits = "23";
		List<String> actual = letterCombinations(digits);
		List<String> expected = new ArrayList<String>();
		String[] arrayExpected = "ad,ae,af,bd,be,bf,cd,ce,cf".split(",");
		for (String s : arrayExpected) {
			expected.add(s);
		}
		assertEquals(expected, actual);
	}

	@Test
	public void positiveTest2() {
		String digits = "89";
		List<String> actual = letterCombinations(digits);
		List<String> expexted = new ArrayList<String>();
		String[] arrayExpected = "tw,tx,ty,tz,uw,ux,uy,uz,vw,vx,vy,vz".split(",");
		for (String s : arrayExpected) {
			expexted.add(s);
		}
		assertEquals(expexted, actual);
	}

	@Test
	public void positiveTest3() {
		String digits = "247";
		List<String> actual = letterCombinations(digits);
		List<String> expexted = new ArrayList<String>();
		String[] arrayExpected = "agp,agq,agr,ags,ahp,ahq,ahr,ahs,aip,aiq,air,ais,bgp,bgq,bgr,bgs,bhp,bhq,bhr,bhs,bip,biq,bir,bis,cgp,cgq,cgr,cgs,chp,chq,chr,chs,cip,ciq,cir,cis"
				.split(",");
		for (String s : arrayExpected) {
			expexted.add(s);
		}
		assertEquals(expexted, actual);
	}

}
