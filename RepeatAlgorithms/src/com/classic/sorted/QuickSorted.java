package com.classic.sorted;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class QuickSorted {

	public static void quickSorted(int[] arr, int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);
			quickSorted(arr, begin, partitionIndex - 1);
			quickSorted(arr, partitionIndex + 1, end);
		}
	}

	private static int partition(int[] arr, int begin, int end) {

		int pivot = arr[end];
		int i = begin;

		for (int j = begin; j <= end - 1; ++j) {
			if (arr[j] < pivot) {
				int temp = arr[i];
				arr[i++] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i];
		arr[i] = arr[end];
		arr[end] = temp;
		return i;
	}

	@Test
	public void test1() {
		int[] actual = {5, 1, 6, 2, 3, 4};
		int[] expected = {1, 2, 3, 4, 5, 6};
		quickSorted(actual, 0, actual.length - 1);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test2() {
		int[] actual = {-58, 12, 6, -25, 63, 4, 19, 0, 8, -3, 894, 0, -234, 0,
				457, 457, 34987, -7};
		int[] expected = Arrays.copyOf(actual, actual.length);
		Arrays.sort(expected);
		quickSorted(actual, 0, actual.length - 1);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test3() {
		int[] actual = {1, 2, 8, 15};
		int[] expected = {1, 2, 8, 15};
		Arrays.sort(expected);
		quickSorted(actual, 0, actual.length - 1);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test4() {
		int[] actual = {1, 2, 3, 2, 2, 4, 5};
		int[] expected = {1, 2, 3, 2, 2, 4, 5};
		Arrays.sort(expected);
		quickSorted(actual, 0, actual.length - 1);
		assertArrayEquals(expected, actual);
	}
}
