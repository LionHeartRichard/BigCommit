package com.tasks.isaev.workstack;

import java.util.*;

/*
 * Учитывая строку s, содержащую только символы '(', ')', '{', и '}', 
 * определите, является ли входная строка допустимой.'['']'
 * Входная строка действительна, если:
 * Открытые скобки должны закрываться скобками того же типа.
 * Открытые скобки должны закрываться в правильном порядке.
 * Каждой закрывающей скобке соответствует открытая скобка того же типа.
 */

public class CheckValidString {

	public boolean isValid(String str) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : str.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '[')
				stack.push(']');
			else if (c == '{')
				stack.push('}');
			else if (stack.isEmpty() || c != stack.pop())
				return false;
		}
		return stack.isEmpty();
	}
}
