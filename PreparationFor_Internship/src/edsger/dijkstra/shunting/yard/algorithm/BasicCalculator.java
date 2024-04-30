package edsger.dijkstra.shunting.yard.algorithm;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.junit.jupiter.api.Test;

/*
 * Учитывая строку, s - представляющую допустимое выражение, реализуйте базовый калькулятор 
 * для ее вычисления и возвращайте результат вычисления. 
 * Примечание. Вам не разрешено использовать какие-либо встроенные функции, 
 * которые оценивают строки как математические выражения, например eval().
 * 
 * Пример 1:
 * Ввод: s = «1 + 1»
 * Вывод: 2
 * 
 * Пример 2:
 * Ввод: s = «2-1 + 2»
 * Вывод: 3
 * 
 * Пример 3:
 * Ввод: s = "(1+(4+5+2)-3)+(6+8)"
 * Вывод: 23
 */

public class BasicCalculator {

	public int calculate(String s) {
		return backtracking(s, 0)[0];
	}

	public int[] backtracking(String s, int begin) {
		char sign = '+';
		int num = 0;
		Deque<Integer> deque = new ArrayDeque<>();
		int i = begin;
		for (i = begin; i < s.length(); ++i) {
			char token = s.charAt(i);
			if (token == '(') {
				int[] arr = backtracking(s, i + 1);
				num = arr[0];
				i = arr[1];
			}

			if (Character.isDigit(token)) {
				num = num * 10 + (token - '0');
			}
			if ((!Character.isDigit(token) && token != ' ')
					|| i == s.length() - 1) {

				switch (sign) {
					case '+' :
						deque.offerLast(num);
						break;
					case '-' :
						deque.offerLast(-num);
						break;
					case '*' :
						deque.offerLast(deque.pollLast() * num);
						break;
					case '/' :
						deque.offerLast(deque.pollLast() / num);
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
		while (!deque.isEmpty()) {
			result += deque.pollFirst();
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
}
