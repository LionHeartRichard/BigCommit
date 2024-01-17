package com.tasks.isaev.removeelements;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/*
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. 
 * The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, 
to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. 
The remaining elements of nums are not important as well as the size of nums.
Return k.
 */

public class RemoveElement {
	public int removeElement(int[] nums, int val) {
		int partition = nums.length, i = 0, j = 0, pivot = 0, countRepetition = 0;

		while (i < partition) {
			pivot = i;
			while (i < partition && nums[i] == val) {
				++countRepetition;
				++i;
			}
			j = i;
			i = pivot;
			while ((j < partition) && countRepetition != 0) {
				nums[i++] = nums[j++];
			}
			if (countRepetition != 0) {
				i = pivot;
				partition -= countRepetition;
			}
			countRepetition = 0;
			++i;
		}
		return partition;
	}

	@Test
	public void positiveTest() {
		int[] nums = new int[] { 0, 1, 2, 2, 3, 0, 4, 2 };
		int partition = removeElement(nums, 2);
		int[] actual = Arrays.copyOf(nums, partition);
		int[] expected = new int[] { 0, 1, 3, 0, 4 };
		assertArrayEquals(expected, actual);
	}
}
