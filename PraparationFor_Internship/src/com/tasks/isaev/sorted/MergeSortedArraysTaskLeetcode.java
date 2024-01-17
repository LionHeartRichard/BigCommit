package com.tasks.isaev.sorted;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/*
 You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, 
 and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. 
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that 
should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 */

public class MergeSortedArraysTaskLeetcode {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = 0, j = 0, k = 0;
		int[] mas = Arrays.copyOf(nums1, m + n);
		while (i < m && j < n) {
			if (mas[i] <= nums2[j]) {
				nums1[k++] = mas[i++];
			} else {
				nums1[k++] = nums2[j++];
			}
		}
		while (i < m) {
			nums1[k++] = mas[i++];
		}
		while (j < n) {
			nums1[k++] = nums2[j++];
		}
	}

	@Test
	public void positiveTest() {
		int[] nums1 = new int[] { 1, 2, 3, 0, 0, 0 };
		int m = 3;
		int[] nums2 = new int[] { 2, 5, 6 };
		int n = 3;
		int[] expected = new int[] { 1, 2, 2, 3, 5, 6 };
		merge(nums1, m, nums2, n);
		assertArrayEquals(expected, nums1);
	}
}
