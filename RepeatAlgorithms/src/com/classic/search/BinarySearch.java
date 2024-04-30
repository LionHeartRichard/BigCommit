package com.classic.search;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BinarySearch {

	public static int binarySearch(int[] sortedArray, int findNumber) {
		int begin = 0, end = sortedArray.length - 1;
		while (begin <= end) {
			int mid = (end - begin) / 2 + begin;
			if (sortedArray[mid] == findNumber) {
				return mid;
			}
			if (sortedArray[mid] < findNumber) {
				begin = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	public static int recursiveBinarySearch(int[] sortedArray, int findNumber,
			int begin, int end) {
		if (begin > end) {
			return -1;
		}
		int mid = (end - begin) / 2 + begin;
		if (sortedArray[mid] == findNumber) {
			return mid;
		}
		if (sortedArray[mid] < findNumber) {
			return recursiveBinarySearch(sortedArray, findNumber, mid + 1, end);
		} else {
			return recursiveBinarySearch(sortedArray, findNumber, begin,
					mid - 1);
		}
	}

	@Test
	public void test1() {
		int[] arr = {34, 78, -3, 0, 45, 78, 0, 11, 45, 89, -6, 445, 12, 45, 11,
				3482};
		Arrays.sort(arr);
		assertEquals(14, binarySearch(arr, 445));
		assertEquals(14, recursiveBinarySearch(arr, 445, 0, arr.length - 1));
	}
}
