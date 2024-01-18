package com.tasks.isaev;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class RotateArrayBeforeK {
	public void rotate(int[] nums, int k) {
		int i = 0, j = nums.length - k, temp = 0;

		if (k == nums.length) {
			return;
		}

		if (k > nums.length) {
			j = nums.length - 1;
			int mid = nums.length / 2;
			while (i < mid && j >= mid) {
				temp = nums[i];
				nums[i++] = nums[j];
				nums[j--] = temp;
			}
			return;
		}

		while (i < k && j < nums.length) {
			temp = nums[i];
			nums[i++] = nums[j];
			nums[j++] = temp;
		}
		if (nums.length == k * 2 || k > nums.length / 2) {
			return;
		}
		i = k;
		j = nums.length - k;

		while (i < nums.length && j < nums.length) {
			temp = nums[i];
			nums[i++] = nums[j];
			nums[j++] = temp;
		}

	}

	@Test
	public void poditiveTest1() {
		int[] expected = { 5, 6, 7, 8, 1, 2, 3, 4 };
		int[] actual = { 1, 2, 3, 4, 5, 6, 7, 8 };
		rotate(actual, 4);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void poditiveTest2() {
		int[] expected = { 5, 6, 7, 1, 2, 3, 4 };
		int[] actual = { 1, 2, 3, 4, 5, 6, 7 };
		rotate(actual, 3);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void poditiveTest3() {
		int[] expected = { 2, 1 };
		int[] actual = { 1, 2 };
		rotate(actual, 1);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void poditiveTest4() {
		int[] expected = { 2, 1 };
		int[] actual = { 1, 2 };
		rotate(actual, 3);
		assertArrayEquals(expected, actual);
	}

}
