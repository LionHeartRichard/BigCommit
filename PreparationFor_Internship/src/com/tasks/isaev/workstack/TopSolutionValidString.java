package com.tasks.isaev.workstack;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class TopSolutionValidString {
	public boolean isValid(String s) {
		char[] chars = s.toCharArray();
		int n = chars.length;
		char[] stack = new char[n];
		int idx = -1;

		for (int i = 0; i < n; i++) {
			char ch = chars[i];
			if (ch == '(' || ch == '{' || ch == '[') {
				stack[++idx] = ch;
			} else {
				if (idx < 0) {
					return false;
				}
				char popped = stack[idx--];
				if ((popped == '(' && ch != ')') || (popped == '{' && ch != '}')
						|| (popped == '[' && ch != ']')) {
					return false;
				}
			}
		}
		return idx == -1;
	}

	@Test
	public void positiveTest1() {
		assertEquals(true, isValid("()[]{}"));
	}

	@Test
	public void positiveTest2() {
		assertEquals(true, isValid("{{([])}}"));
	}

	@Test
	public void negativeTest3() {
		assertEquals(false, isValid("(]"));
	}

}
