package com.tasks.isaev.recursive;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

//nums = 9, 3, 7
//target = 13
//+-
//9-3+7=13

//nums = 5, 6
//target = 11
//5+6=11

public class SumOrSubstractionSolution {

	private String carry = "";

	public String solve(int[] nums, int target) {
		backtracking("" + nums[0], nums[0], 1, nums, target);
		return carry;
	}

	private void backtracking(String solution, int sum, int idx, int[] nums,
			int target) {
		if (idx >= nums.length) {
			if (sum == target) {
				solution += "=" + sum;
				carry = solution;
			}
			return;
		}

		backtracking(solution + "+" + nums[idx], sum + nums[idx], idx + 1, nums,
				target);
		backtracking(solution + "-" + nums[idx], sum - nums[idx], idx + 1, nums,
				target);

	}

	@Test
	public void tes1() {
		int[] nums = {9, 3, 7};
		int target = 13;

		assertEquals("9-3+7=13", solve(nums, target));
	}

	@Test
	public void tes2() {
		int[] nums = {5, 6, 9};
		int target = 20;

		assertEquals("5+6+9=20", solve(nums, target));
	}

	@Test
	public void tes3() {
		int[] nums = {5, 10, 2, 4, 5, 6, 4, 8, 2, 4, 2, 5, 6, 4};
		int target = 67;

		assertEquals("5+10+2+4+5+6+4+8+2+4+2+5+6+4=67", solve(nums, target));
	}

	@Test
	public void tes4() {
		int[] nums = {5, 10, 2, 4, 5, 6, 4, 8, 2, 4, 2, 5, 6, 4};
		int target = -57;

		assertEquals("5-10-2-4-5-6-4-8-2-4-2-5-6-4=-57", solve(nums, target));
	}
}
