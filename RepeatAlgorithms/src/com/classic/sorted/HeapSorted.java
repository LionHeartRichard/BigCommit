package com.classic.sorted;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class HeapSorted {

	public void heapSorted(int[] arr) {
		int n = arr.length;
		for (int i = n / 2 - 1; i >= 0; --i) {
			heapify(arr, n, i);
		}

		for (int i = n - 1; i >= 0; --i) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			heapify(arr, i, 0);
		}
	}

	private void heapify(int[] arr, int n, int index) {
		int i = index;
		int left = i * 2 + 1;
		int right = left + 1;

		if (left < n && arr[left] > arr[i])
			i = left;

		if (right < n && arr[right] > arr[i])
			i = right;

		if (i != index) {
			int temp = arr[i];
			arr[i] = arr[index];
			arr[index] = temp;

			heapify(arr, n, i);
		}
	}

	@Test
	public void test1() {
		int[] actual = {9, 13, 89, 0, 45, -8, 4, -9, 10, 45, 68, 55, 3, 33, 92,
				13, 37, -90, 85, 1};
		int[] expected = Arrays.copyOf(actual, actual.length);
		Arrays.sort(expected);
		heapSorted(actual);
		assertArrayEquals(expected, actual);
	}

}
