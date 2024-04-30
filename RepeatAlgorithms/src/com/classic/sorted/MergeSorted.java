package com.classic.sorted;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MergeSorted {

	public static void mergeSort(int[] arr, int n) {
		if (n < 2) {
			return;
		}

		int mid = n / 2;
		int[] left = new int[mid];
		int[] right = new int[n - mid];

		for (int i = 0; i < mid; ++i) {
			left[i] = arr[i];
		}
		for (int i = mid; i < n; ++i) {
			right[i - mid] = arr[i];
		}

		mergeSort(left, mid);
		mergeSort(right, n - mid);

		merge(arr, left, right, left.length, right.length);
	}

	private static void merge(int[] arr, int[] left, int[] right, int n1,
			int n2) {
		int i = 0, j = 0, k = 0;

		while (i < n1 && j < n2) {
			if (left[i] <= right[j]) {
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}
		while (i < n1) {
			arr[k++] = left[i++];
		}
		while (j < n2) {
			arr[k++] = right[j++];
		}
	}

	@Test
	public void test1() {
		int[] actual = {5, 1, 6, 2, 3, 4};
		int[] expected = {1, 2, 3, 4, 5, 6};
		mergeSort(actual, actual.length);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test2() {
		int[] actual = {-58, 12, 6, -25, 63, 4, 19, 0, 8, -3, 894, 0, -234, 0,
				457, 457, 34987, -7};
		int[] expected = Arrays.copyOf(actual, actual.length);
		Arrays.sort(expected);
		mergeSort(actual, actual.length);
		assertArrayEquals(expected, actual);
	}
}
