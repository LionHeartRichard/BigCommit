package classic;

import static org.junit.Assert.assertArrayEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class SortedArrayClass {

	private String fileName = "/home/kein/Doc/data_for_algorithm/BigArray.txt";
	private static final int THRESHOLD = 32;

	public static void timSorted(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i += THRESHOLD) {
			insertionSorted(arr, i, Math.min(i + THRESHOLD, n));
		}

		for (int size = THRESHOLD; size < n; size *= 2) {
			for (int begin = 0; begin < n; begin += size * 2) {

				int mid = begin + size - 1;
				int end = Math.min(begin + size * 2 - 1, n - 1);

				if (mid < end) {
					merge(arr, begin, mid, end);
				}
			}
		}
	}

	private static void merge(int[] arr, int begin, int mid, int end) {
		int i, j, k;

		int n1 = mid - begin + 1;
		int n2 = end - mid;

		int[] left = new int[n1];
		int[] right = new int[n2];

		for (i = 0; i < n1; ++i) {
			left[i] = arr[begin + i];
		}
		for (j = 0; j < n2; ++j) {
			right[j] = arr[mid + 1 + j];
		}

		i = 0;
		j = 0;
		k = begin;

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

	private static void insertionSorted(int[] arr, int begin, int n) {
		int i, j, temp;
		for (i = begin + 1; i < n; ++i) {
			temp = arr[i];
			j = i - 1;
			while (j >= begin && arr[j] > temp) {
				arr[j + 1] = arr[j--];
			}
			arr[++j] = temp;
		}
	}

	public static void heapSorted(int[] arr) {
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

	private static void heapify(int[] arr, int n, int index) {
		int i = index;
		int left = i * 2 + 1;
		int right = left + 1;

		if (left < n && arr[left] > arr[i])
			i = left;

		if (right < n && arr[right] > arr[i])
			i = right;

		if (i != index) {
			int temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;

			heapify(arr, n, i);
		}
	}

	public static void quickSorted(int[] arr, int begin, int end) {
		if (begin < end) {
			int index = partition(arr, begin, end);
			quickSorted(arr, begin, index - 1);
			quickSorted(arr, index + 1, end);
		}
	}

	private static int partition(int[] arr, int begin, int end) {
		int i = begin, pivot = arr[end];
		for (int j = begin; j <= end - 1; ++j) {
			if (arr[j] < pivot) {
				int temp = arr[i];
				arr[i++] = arr[j];
				arr[j] = temp;
			}
		}

		int temp = arr[end];
		arr[end] = arr[i];
		arr[i] = temp;
		return i;
	}

	@Test
	public void testBigArrayTimSorted() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String str = reader.readLine();
		reader.close();
		String[] arrayStr = str.split(",");
		int[] arr = new int[arrayStr.length];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = Integer.parseInt(arrayStr[i]);
		}

		timSorted(arr);

		int[] expected = Arrays.copyOf(arr, arr.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, arr);
	}

	@Test
	public void testBigArrayHeapSorted() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String str = reader.readLine();
		reader.close();
		String[] arrayStr = str.split(",");
		int[] arr = new int[arrayStr.length];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = Integer.parseInt(arrayStr[i]);
		}

		heapSorted(arr);

		int[] expected = Arrays.copyOf(arr, arr.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, arr);
	}

	@Test
	public void testMidleArrayQuickSorted() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String str = reader.readLine();
		reader.close();
		String[] arrayStr = str.split(",");
		int[] arr = new int[arrayStr.length / 5];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = Integer.parseInt(arrayStr[i]);
		}

		quickSorted(arr, 0, arr.length - 1);

		int[] expected = Arrays.copyOf(arr, arr.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, arr);
	}
}
