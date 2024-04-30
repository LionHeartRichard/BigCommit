package com.hard;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.*;

public class BasicCalculator {

	private int num = 0;

	public int calculate(String s) {
		return backtracking(s, 0)[0];
	}

	private int[] backtracking(String s, int begin) {
		int i = begin;
		char sign = '+';

		Deque<Integer> nums = new ArrayDeque<Integer>();

		for (i = begin; i < s.length(); ++i) {
			char token = s.charAt(i);
			if (token == '(') {
				int[] temp = backtracking(s, i + 1);
				num = temp[0];
				i = temp[1];
			}
			if (Character.isDigit(token)) {
				num = num * 10 + (token - '0');
			}
			if ((!Character.isDigit(token) && token != ' ')
					|| i == s.length() - 1) {
				switch (sign) {
					case '+' :
						nums.offerLast(num);
						break;
					case '-' :
						nums.offerLast(-num);
						break;
					case '/' :
						nums.offerLast(nums.pollLast() / num);
						break;
					case '*' :
						nums.offerLast(nums.pollLast() * num);
						break;
				}
				sign = token;
				num = 0;
			}
			if (token == ')') {
				break;
			}
		}

		int result = 0;
		while (!nums.isEmpty()) {
			result += nums.pollLast();
		}
		return new int[]{result, i};
	}

	@Test
	public void test1() {
		String s = "1 + 1";
		int expected = 2;
		int actual = calculate(s);
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		String s = "2-1 + 2";
		int expected = 3;
		int actual = calculate(s);
		assertEquals(expected, actual);
	}

	@Test
	public void test3() {
		String s = "(1+(4+5+2)-3)+(6+8)";
		int expected = 23;
		int actual = calculate(s);
		assertEquals(expected, actual);
	}

	@Test
	public void test4() {
		String s = "-(2 + 3)";
		int expected = -5;
		int actual = calculate(s);
		assertEquals(expected, actual);
	}

	@Test
	public void test5() {
		String s = "16 /(14 -(2+   3) * 2)";
		int expected = 4;
		int actual = calculate(s);
		assertEquals(expected, actual);
	}

	@Test
	public void test6() {
		String s = " 14-3*( 2+1)-  10/5 ";
		int expected = 3;
		int actual = calculate(s);
		assertEquals(expected, actual);
	}

	@Test
	public void test7() {
		String s = " 14     -4*(2+   4)+20/5   ";
		int expected = -6;
		int actual = calculate(s);
		assertEquals(expected, actual);
	}
}
