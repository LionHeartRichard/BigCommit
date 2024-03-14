package com.tasks.isaev.recursive.classicbacktracking;

import java.util.*;

public class ClassicBacktracking {

	public static void main(String[] args) {
		String strCombination = "1!b";
		List<String> output = new ArrayList<String>();
		output = combination(strCombination);
		output.forEach(s -> System.out.println(s));

		System.out.println("--------------------------------------------");

		output.clear();
		output = combinationFull(strCombination);
		output.forEach(s -> System.out.println("Full: " + s));

		System.out.println("--------------------------------------------");

		output.clear();
		output = combinationNumbersInArray(new int[] { 1, 2, 3 });
		output.forEach(s -> System.out.println(s));
	}

	private static List<String> combinationFull(String strCombination) {
		if (strCombination.isEmpty()) {
			return Collections.emptyList();
		}
		List<String> result = new ArrayList<String>();
		backtrackingFull("", strCombination, strCombination.toCharArray(), result);
		return result;
	}

	private static void backtrackingFull(String combination, String nextLetters, char[] letterArray,
			List<String> result) {
		if (nextLetters.isEmpty()) {
			return;
		}
		for (char letter : letterArray) {
			result.add(combination + letter);
			backtrackingFull(combination + letter, nextLetters.substring(1), letterArray, result);
		}
	}

	public static List<String> combination(String strChars) {
		if (strChars.isEmpty()) {
			Collections.emptyList();
		}
		List<String> result = new ArrayList<String>();
		backtracking("", strChars, strChars.toCharArray(), result);
		return result;
	}

	private static void backtracking(String combination, String nextSubStr, char[] arrLetters, List<String> result) {
		if (nextSubStr.isEmpty()) {
			result.add(combination);
			return;
		}
		for (char letter : arrLetters) {
			backtracking(combination + letter, nextSubStr.substring(1), arrLetters, result);
		}
	}

	public static List<String> combinationNumbersInArray(int[] nums) {
		if (nums.length == 0) {
			return Collections.emptyList();
		}
		List<String> result = new ArrayList<String>();
		backtrackingNumbersInArray("", 0, nums, result);
		return result;
	}

	private static void backtrackingNumbersInArray(String combination, int nextIdx, int[] nums, List<String> result) {
		if (nextIdx < nums.length) {
			for (int item : nums) {
				backtrackingNumbersInArray(combination + item, nextIdx + 1, nums, result);
			}
		} else {
			result.add(combination);
			return;
		}
	}
}
