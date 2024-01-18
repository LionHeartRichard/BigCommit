package com.tasks.isaev.recursive;

//nums = 9, 3, 7
//target = 13
// +-
//  9-3+7=13

// nums = 5, 6
//target = 11
//  5+6=11

// 1. Backtracking
// 2. Backpack DP
// 3. "Backtracking" with set

public class SubtractionOrSumProblem {

	public static void main(String[] args) {
		int[] nums = { 9, 3, 7 };
		int target = 13;

		System.out.println(solve(nums, target));
	}

	private static boolean solve(int[] nums, int target) {
		if (nums.length == 0) {
			return false;
		}
		return calc(nums, target, nums[0], 1);
	}

	private static boolean calc(int[] nums, int target, int sum, int index) {
		// Edge cases
		if (index == nums.length) {
			return target == sum;
		}
		return calc(nums, target, sum + nums[index], index + 1) || calc(nums, target, sum - nums[index], index + 1);
	}
	// O(2^N) = 2^N-1
	// 0(3) {9,3,7}
	// 9 : 1
	// 12, 6 : 2
	// 5, 19, 13, -1 :4
	// ... : 2^(N-1)
	// 1+2+4+..+2*(N-1) = 2^N-1
	// Time complexity - O(2^N)

	// Space complexity - O(N)

	// Unit TESTS
	// 1==1
	// -1+5=4
}
