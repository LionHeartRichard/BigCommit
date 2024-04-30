package com.classic.sorted;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class InsertionSorted {

	public static void insertionSorted(int[] arr) {
		for (int i = 1; i < arr.length; ++i) {
			int carry = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > carry) {
				arr[j + 1] = arr[j--];
			}
			arr[++j] = carry;
		}
	}

	@Test
	public void test1() {
		int[] actual = {5, 1, 6, 2, 3, 4};
		int[] expected = {1, 2, 3, 4, 5, 6};
		insertionSorted(actual);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test2() {
		int[] actual = {-58, 12, 6, -25, 63, 4, 19, 0, 8, -3, 894, 0, -234, 0,
				457, 457, 34987, -7};
		int[] expected = Arrays.copyOf(actual, actual.length);
		Arrays.sort(expected);
		insertionSorted(actual);
		assertArrayEquals(expected, actual);
	}

}
