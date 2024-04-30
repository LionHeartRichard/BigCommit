package com.classic.sorted;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TimSorted {

	private static final int RUN = 32;

	public void timSorted(int[] arr, int n) {

		for (int i = 0; i < n; i += RUN) {
			insertionSorted(arr, i, Math.min(i + RUN, n));
		}

		int begin = 0;
		for (int size = RUN; size < n; size *= 2) {
			int mid = begin + RUN;
			int sizeN = Math.min(size + RUN, n);
			if (mid < sizeN) {
				merge(arr, begin, mid, sizeN);
			}
			begin = size;
		}
	}

	private void insertionSorted(int[] arr, int begin, int n) {
		for (int i = 0; i < n; ++i) {
			int carry = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > carry) {
				arr[j + 1] = arr[j--];
			}
			arr[++j] = carry;
		}
	}

	private void merge(int[] arr, int begin, int mid, int endN) {

		int i = 0, j = 0, k = begin;

		int n1 = mid - begin;
		int n2 = endN - mid;

		int[] left = new int[n1];
		int[] right = new int[n2];

		for (i = 0; i < n1; ++i) {
			left[i] = arr[begin + i];
		}
		for (j = 0; j < n2; ++j) {
			right[j] = arr[mid + j];
		}
		i = 0;
		j = 0;

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
		int[] actual = new int[]{12, 2, 4, 5, 23, 44, 55, 23, 11, 0, -2, -4,
				-23, 34, 45, 0, 23, 34, 45, 1, 2, -5, 39, 19, -44, -30, 30, 99,
				100, -100, 22, -22, 44, 99, 9, 22, 40, 29, 47, 88, -88, 23};
		int[] expected = Arrays.copyOf(actual, actual.length);
		Arrays.sort(expected);

		timSorted(actual, actual.length);

		assertArrayEquals(expected, actual);
	}

	@Test
	public void test2() {
		int[] actual = {5, 1, 6, 2, 3, 4};
		int[] expected = {1, 2, 3, 4, 5, 6};
		timSorted(actual, actual.length);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test3() {
		int[] actual = {-58, 12, 6, -25, 63, 4, 19, 0, 8, -3, 894, 0, -234, 0,
				457, 457, 34987, -7, 94, 0, 0, 89, -45, 67, 77, -55, 45, 897,
				-3, 5, -5689, 34, 34, 90, 89, 45, 45, 34, 23, 12, 12, 12, 57,
				90, 456, -786, -9040, 459, 43758, 45, 9, 12, 2, 4, 5, 23, 44,
				55, 23, 11, 0, -2, -4, -23, 34, 45, 0, 23, 34, 45, 1, 2, -5, 39,
				19, -44, -30, 30, 99, 100, -100, 22, -22, 44, 99, 9, 22, 40, 29,
				47, 88, -88, 23, 5, 1, 6, 2, 3, 4, 5, 1, 6, 2, 3, 4, 5, 1, 6, 2,
				3, 4, 5, 1, 6, 2, 3, 4, 5, 1, 6, 2, 3, 4, 5, 1, 6, 2, 3, 4};
		int[] expected = Arrays.copyOf(actual, actual.length);
		Arrays.sort(expected);
		timSorted(actual, actual.length);
		assertArrayEquals(expected, actual);
	}
}
