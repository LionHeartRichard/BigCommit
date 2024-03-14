package com.tasks.isaev.removeelements;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class RemoveDuplicatesFromSortedArray {

	public int removeDuplicates(int[] nums) {

		int j = 1, n = nums.length;
		for (int i = 1; i < n; ++i) {
			if (nums[i] != nums[i - 1]) {
				nums[j++] = nums[i];
			}
		}

		return j;
	}

	@Test
	public void positiveTest1() {
		int[] nums = { 0, 0, 0, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 6 };
		int[] expected = { 0, 1, 2, 3, 4, 5, 6 };
		int partition = removeDuplicates(nums);
		int[] actual = Arrays.copyOf(nums, partition);
		assertArrayEquals(expected, actual);
	}

}
