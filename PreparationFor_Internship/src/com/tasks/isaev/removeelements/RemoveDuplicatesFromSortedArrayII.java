package com.tasks.isaev.removeelements;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/*
Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place 
such that each unique element appears at most twice. 

The relative order of the elements should be kept the same.
Since it is impossible to change the length of the array in some languages, 
you must instead have the result be placed in the first part of the array nums. 
More formally, if there are k elements after removing the duplicates, 
then the first k elements of nums should hold the final result. 
It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. 
You must do this by modifying the input array in-place with O(1) extra memory.
 */

public class RemoveDuplicatesFromSortedArrayII {
	public int removeDuplicates(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int j = 2;
		for (int i = 2; i < nums.length; ++i) {
			if (nums[i] != nums[j - 2]) {
				nums[j++] = nums[i];
			}
		}
		return j;
	}

	@Test
	public void positiveTest() {
		int[] nums = new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4 };
		int[] expected = new int[] { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4 };
		int k = removeDuplicates(nums);
		int[] actual = Arrays.copyOf(nums, k);

		assertArrayEquals(expected, actual);

	}
}
