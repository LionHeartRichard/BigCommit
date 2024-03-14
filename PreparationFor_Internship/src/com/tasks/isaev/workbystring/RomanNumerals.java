package com.tasks.isaev.workbystring;

import static org.junit.Assert.assertEquals;

/*
 * Римские цифры обычно пишутся от большей к меньшей слева направо. 
 * Однако цифра «четыре» не является цифрой «четыре» IIII. 
 * Вместо этого число четыре записывается как IV. Поскольку единица стоит перед пятеркой, 
 * мы вычитаем ее, получая четыре. Тот же принцип применим и к числу девять, 
 * которое записывается как IX. Есть шесть случаев, когда используется вычитание:
 * I - можно поставить перед V(5) и X(10), чтобы получилось 4 и 9. 
 * X -можно поставить перед L(50) и C(100), чтобы получилось 40 и 90. 
 * C - можно поставить перед D(500) и M(1000), чтобы получить 400 и 900.
 * Дана римская цифра, преобразуйте ее в целое число.
 */

import java.util.*;

import org.junit.jupiter.api.Test;

public class RomanNumerals {

	private Map<Character, Integer> map = new HashMap<>();
	public int romanToInt(String s) {
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		if (s.length() == 1) {
			return map.get(s.charAt(0));
		}

		int num = 0;
		char[] arr = s.toCharArray();
		for (int i = 1; i < s.length(); ++i) {
			int first = map.get(arr[i - 1]);
			int second = map.get(arr[i]);
			if (first >= second) {
				if (num == 0) {
					num = first;
				}
				num += second;
			} else {
				if (num != 0) {
					num -= first;
				}
				num = num + second - first;
			}
		}

		return num;
	}

	@Test
	public void test1() {
		String s = "III";
		int expected = 3;
		int actual = romanToInt(s);
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		String s = "LVIII";
		int expected = 58;
		int actual = romanToInt(s);
		assertEquals(expected, actual);
	}

	@Test
	public void test3() {
		String s = "MCMXCIV";
		int expected = 1994;
		int actual = romanToInt(s);
		assertEquals(expected, actual);
	}
}
