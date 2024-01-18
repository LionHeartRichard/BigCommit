package com.tasks.isaev.removeelements;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class RemoveDuplicatesFromSortedArray {

	public int removeDuplicates(int[] nums) {

		int j = 1, n = nums.length;
		for (int i = 1; i < n; i++) {
			if (nums[i] != nums[i - 1]) {
				nums[j++] = nums[i];
			}
		}

		return j;
	}

	public int removeDuplicatesQuick(int[] nums) {
		int curMax = nums[0];
		int k = 1;
		int i = 1;
		while (i < nums.length) {
			if (nums[i] > curMax) {
				nums[k++] = nums[i];
				curMax = nums[i];
			} else {
				i++;
			}
		}
		return k;
	}

	@Test
	public void positiveTest() {
		int[] nums = { 0, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 6 };
		int[] expected = { 0, 1, 2, 3, 4, 5, 6 };
		int partition = removeDuplicates(nums);
		int[] actual = Arrays.copyOf(nums, partition);
		assertArrayEquals(expected, actual);
	}
}
