package com.tasks.isaev.numberswork;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

public class NumberIsPalindrom {
	public boolean isPalindrom(int num) {

		if (num < 0)
			return false;

		if (num == 0)
			return true;

		Deque<Integer> que = new ArrayDeque<Integer>();
		while (num != 0) {
			que.add(num % 10);
			num /= 10;
		}
		if (que.size() == 1) {
			return true;
		}

		while (que.size() > 1) {
			if (que.pollFirst() != que.pollLast()) {
				return false;
			}
		}

		return true;
	}

	public boolean isValidPalindrome(int number) {
		if (number < 0)
			return false;

		int copy = number;
		int reverse = 0, tail = 0;
		while (copy > 0) {
			tail = copy % 10;
			reverse = reverse * 10 + tail;
			copy /= 10;
		}
		return number == reverse;
	}

	@Test
	public void test1() {
		int num = 121;
		assertEquals(true, isPalindrom(num));
		assertEquals(true, isValidPalindrome(num));
	}
}
