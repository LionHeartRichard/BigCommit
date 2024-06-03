package classic;

import static org.junit.Assert.assertArrayEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class AllAlgorithmsSorted {

	public final int THRESHOLD = 32;
	public final int RUN = 32;
	private String fileName = "/home/kein/Doc/data_for_algorithm/BigArray.txt";

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
			int temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;

			heapify(arr, n, i);
		}
	}

	public void mergeSorted(int[] arr) {
		int n = arr.length;
		if (n < 2)
			return;

		int mid = n / 2;

		int[] left = new int[mid];
		int[] right = new int[n - mid];

		for (int i = 0; i < mid; ++i) {
			left[i] = arr[i];
		}
		for (int i = mid; i < n; ++i) {
			right[i - mid] = arr[i];
		}

		mergeSorted(left);
		mergeSorted(right);

		merge(arr, left, right, mid, n - mid);
	}

	private void merge(int[] arr, int[] left, int[] right, int n1, int n2) {
		int k = 0, i = 0, j = 0;

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

	public void quickSorted(int[] arr, int begin, int end) {
		if (begin < end) {
			int idx = partition(arr, begin, end);
			quickSorted(arr, begin, idx - 1);
			quickSorted(arr, idx + 1, end);
		}
	}

	private int partition(int[] arr, int begin, int end) {
		int i = begin, pivot = arr[end];

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

	private int partitionJP(int a[], int start, int end) {
		int pivot = a[end];
		int i = (start - 1);

		for (int j = start; j <= end - 1; j++) {
			if (a[j] < pivot) {
				i++;
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
		int t = a[i + 1];
		a[i + 1] = a[end];
		a[end] = t;
		return (i + 1);
	}

	public void quickJP(int a[], int start, int end) {
		if (start < end) {
			int index = partitionJP(a, start, end);
			quickJP(a, start, index - 1);
			quickJP(a, index + 1, end);
		}
	}

	public void timSorted(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i += THRESHOLD) {
			insertionSorted(arr, i, Math.min(i + THRESHOLD, n));
		}

		for (int size = THRESHOLD; size < n; size *= 2) {
			for (int begin = 0; begin < n; begin += size * 2) {

				int mid = begin + size - 1;
				int end = Math.min(begin + size * 2 - 1, n - 1);

				if (mid < end) {
					mergeTimSorted(arr, begin, mid, end);
				}
			}
		}
	}

	private void mergeTimSorted(int[] arr, int begin, int mid, int end) {
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

	public void insertionSorted(int[] arr, int begin, int n) {
		int i, j, temp;
		for (i = begin + 1; i < n; ++i) {
			j = i - 1;
			temp = arr[i];
			while (j >= begin && arr[j] >= temp) {
				arr[j + 1] = arr[j--];
			}
			arr[++j] = temp;
		}
	}

	public void timSortedThreds(int[] arr, int n) {
		for (int i = 0; i < n; i += RUN) {
			insertionSortedThreads(arr, i, Math.min(i + RUN, n));
		}

		for (int size = RUN; size < n; size *= 2) {
			for (int begin = 0; begin < n; begin += size * 2) {

				int mid = begin + size - 1;
				int end = Math.min(begin + size * 2 - 1, n - 1);

				if (mid < end) {
					mergeTimSortedThreads(arr, begin, mid, end);
				}
			}
		}
	}

	private void mergeTimSortedThreads(int[] arr, int begin, int mid, int end) {
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

	private void insertionSortedThreads(int[] arr, int begin, int n) {
		int i, j, temp;
		for (i = begin + 1; i < n; ++i) {
			temp = arr[i];
			j = i - 1;
			while (j >= begin && arr[j] >= temp) {
				arr[j + 1] = arr[j--];
			}
			arr[++j] = temp;
		}
	}

	@Test
	public void testTimeReadAndWriteArray() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String str = reader.readLine();
		reader.close();
		String[] arrayStr = str.split(",");
		int[] arr = new int[arrayStr.length];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = Integer.parseInt(arrayStr[i]);
		}

		int[] expected = Arrays.copyOf(arr, arr.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, expected);

	}

	@Test
	public void testTimSortedThreads() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String str = reader.readLine();
		reader.close();
		String[] arrayStr = str.split(",");
		int[] arr = new int[arrayStr.length];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = Integer.parseInt(arrayStr[i]);
		}

		timSortedThreds(arr, arr.length);

		int[] expected = Arrays.copyOf(arr, arr.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, arr);

	}

	@Test
	public void testTimSorted() throws Exception {
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
	public void testMergeSorted() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String str = reader.readLine();
		reader.close();
		String[] arrayStr = str.split(",");
		int[] arr = new int[arrayStr.length];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = Integer.parseInt(arrayStr[i]);
		}

		mergeSorted(arr);

		int[] expected = Arrays.copyOf(arr, arr.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, arr);

	}

	@Test
	public void testQuickSorted() throws Exception {
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

	@Test
	public void testQuickJP() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String str = reader.readLine();
		reader.close();
		String[] arrayStr = str.split(",");
		int[] arr = new int[arrayStr.length / 5];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = Integer.parseInt(arrayStr[i]);
		}

		quickJP(arr, 0, arr.length - 1);

		int[] expected = Arrays.copyOf(arr, arr.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, arr);
	}

	@Test
	public void testHeapSorted() throws Exception {
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

}
