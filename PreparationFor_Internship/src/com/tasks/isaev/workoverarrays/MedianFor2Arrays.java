package com.tasks.isaev.workoverarrays;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MedianFor2Arrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length, n2 = nums2.length;
		int i = 0, j = 0, k = 0;
		int m = n1 + n2;
		double[] arr = new double[m];
		while (i < n1 && j < n2) {
			if (nums1[i] <= nums2[j]) {
				arr[k++] = nums1[i++];
			} else {
				arr[k++] = nums2[j++];
			}
		}
		while (i < n1) {
			arr[k++] = nums1[i++];
		}
		while (j < n2) {
			arr[k++] = nums2[j++];
		}
		int idx = m / 2;
		if (m % 2 == 0) {
			double median = (arr[idx - 1] + arr[idx]) / 2.0;
			return median;
		}
		return arr[idx];
	}

	@Test
	public void positiveTest1() {
		int[] nums1 = {1, 3};
		int[] nums2 = {2};
		double actual = findMedianSortedArrays(nums1, nums2);
		assertEquals(true, 2.0 == actual);
	}

	@Test
	public void positiveTest2() {
		int[] nums1 = {1, 2};
		int[] nums2 = {3, 4};
		double actual = findMedianSortedArrays(nums1, nums2);
		assertEquals(true, 2.5 == actual);
	}
}
