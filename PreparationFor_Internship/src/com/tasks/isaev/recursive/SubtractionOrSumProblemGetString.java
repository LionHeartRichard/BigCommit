package com.tasks.isaev.recursive;

//nums = 9, 3, 7
//target = 13
//+-
//9-3+7=13

//Backtracking

// !!!!
//RETURN  String: "9-3+7=13"

import java.util.*;

public class SubtractionOrSumProblemGetString {

	public static void main(String[] args) {
		int[] nums = { 9, 3, 7 };
		int target = 13;
		List<String> result = operationCombination(nums, target);
		result.forEach(s -> System.out.println(s));
	}

	public static List<String> operationCombination(int[] nums, int target) {
		if (nums.length == 0) {
			return Collections.emptyList();
		}
		List<String> result = new ArrayList<String>();
		backtracking("", 0, nums, result, target, 0);
		return result;
	}

	private static void backtracking(String combination, int nextIdx, int[] nums, List<String> result, int target,
			int sum) {
		if (nextIdx < nums.length) {
			for (int i = 0; i < nums.length; ++i) {
				if (sum == target) {
					result.add(combination);
					return;
				}
				if (combination.length() > 0 && i != nextIdx - 1) {
					int temp = sum + nums[i];
					result.add(combination + "+" + nums[i] + "=" + temp);
					temp = sum - nums[i];
					result.add(combination + "-" + nums[i] + "=" + temp);
					backtracking(combination + "+" + nums[i], nextIdx + 1, nums, result, target, sum += nums[i]);
					backtracking(combination + "-" + nums[i], nextIdx + 1, nums, result, target, sum -= nums[i]);
				}
				if (combination.length() == 0) {
					backtracking(nums[i] + "", nextIdx + 1, nums, result, target, nums[i]);
				}
			}
		}
	}

}
