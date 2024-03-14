package com.tasks.isaev.numberswork;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

/*
 * Учитывая целочисленный массив nums, поверните массив вправо на k шагов, где k неотрицательно.
 * Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 */

public class RotateArraysBeatsMemory {
	public void rotate(int[] nums, int k) {
		int n = nums.length, begin = 0, end = n - 1;
		k %= n;
		while (begin < end) {
			int temp = nums[begin];
			nums[begin++] = nums[end];
			nums[end--] = temp;
		}
		begin = 0;
		end = k - 1;
		while (begin < end) {
			int temp = nums[begin];
			nums[begin++] = nums[end];
			nums[end--] = temp;
		}
		begin = k;
		end = n - 1;
		while (begin < end) {
			int temp = nums[begin];
			nums[begin++] = nums[end];
			nums[end--] = temp;
		}
	}

	@Test
	public void positiveTest1() {
		int[] actual = {1, 2, 3, 4, 5, 6, 7};
		int[] expecteds = {5, 6, 7, 1, 2, 3, 4};
		rotate(actual, 3);
		assertArrayEquals(expecteds, actual);
	}
}
