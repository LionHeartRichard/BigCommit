package com.tasks.isaev.numberswork;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class RotateArraysBeatsRuntime {
	public static void reverse(int nums[], int begin, int end) {
		while (begin < end) {
			int temp = nums[begin];
			nums[begin++] = nums[end];
			nums[end--] = temp;
		}
	}

	public void rotate(int[] nums, int k) {

		k %= nums.length;

		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}
	
	@Test
	public void positiveTest1() {
		int[] actual = {1, 2, 3, 4, 5, 6, 7};
		int[] expecteds = {5, 6, 7, 1, 2, 3, 4};
		rotate(actual, 3);
		assertArrayEquals(expecteds, actual);
	}
}
