package com.tasks.isaev.numberswork;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class BinarySumNubers {
	public String sumBinary(String a, String b) {
		StringBuilder res = new StringBuilder();
		int i = a.length() - 1, j = b.length() - 1;
		int carry = 0;
		while (i >= 0 || j >= 0 || carry == 1) {
			if (i >= 0) {
				carry += a.charAt(i--) - '0';
			}
			if (j >= 0) {
				carry += b.charAt(j--) - '0';
			}
			res.append(carry % 2);
			carry /= 2;
		}
		return res.reverse().toString();
	}

	@Test
	public void positiveTest1() {
		String a = "1010", b = "1011";
		String expected = "10101";
		String actual = sumBinary(a, b);
		assertEquals(expected, actual);
	}

	@Test
	public void positiveTest2() {
		String a = "101", b = "111";
		String expected = "1100";
		String actual = sumBinary(a, b);
		assertEquals(expected, actual);
	}

	@Test
	public void positiveTest3() {
		String a = "1", b = "0";
		String expected = "1";
		String actual = sumBinary(a, b);
		assertEquals(expected, actual);
	}

	@Test
	public void positiveTest4() {
		String a = "1", b = "1";
		String expected = "10";
		String actual = sumBinary(a, b);
		assertEquals(expected, actual);
	}

	@Test
	public void positiveTest5() {
		String a = "10101010", b = "11001100";
		String expected = "101110110";
		String actual = sumBinary(a, b);
		assertEquals(expected, actual);
	}
}
