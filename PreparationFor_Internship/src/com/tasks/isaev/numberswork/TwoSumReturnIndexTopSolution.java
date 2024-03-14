package com.tasks.isaev.numberswork;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class TwoSumReturnIndexTopSolution {

	public int[] twoSum(int[] nums, int target) {

		for (int i = 1; i < nums.length; ++i) {
			for (int j = i; j < nums.length; ++j) {
				if (target == nums[j] + nums[j - i]) {
					return new int[]{j - i, j};
				}
			}
		}
		return null;
	}

	@Test
	public void positiveTest1() {
		int[] nums = {2, 1, -3, 0, 1, 2, -5};
		int target = -3;
		int[] actual = twoSum(nums, target);
		int[] expected = {2, 3};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void positiveTest2() {
		int[] nums = {2, 7, 11, 15};
		int target = 9;
		int[] actual = twoSum(nums, target);
		int[] expected = {0, 1};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void positiveTest3() {
		int[] nums = {3, 2, 4};
		int target = 6;
		int[] actual = twoSum(nums, target);
		int[] expected = {1, 2};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void positiveTest4() {
		int[] nums = {3, 3};
		int target = 6;
		int[] actual = twoSum(nums, target);
		int[] expected = {0, 1};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void positiveTest5() {
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, -8};
		int target = 0;
		int[] actual = twoSum(nums, target);
		int[] expected = {7, 8};
		assertArrayEquals(expected, actual);
	}
}
