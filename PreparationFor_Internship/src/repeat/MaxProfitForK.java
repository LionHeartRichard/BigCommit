package repeat;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

import java.util.*;

public class MaxProfitForK {

	class Broker {
		Integer buy;
		Integer sell;
		Integer profit;
		Integer begin;
		Integer end;

		Broker() {
		}

		Broker(int buy, int sell, int begin, int end) {
			this.buy = buy;
			this.sell = sell;
			this.profit = sell + buy;
			this.begin = begin;
			this.end = end;
		}

		int getProfit() {
			profit = buy + sell;
			return profit;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(begin, end);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Broker other = (Broker) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return begin == other.begin && end == other.end;
		}

		private MaxProfitForK getEnclosingInstance() {
			return MaxProfitForK.this;
		}

		@Override
		public String toString() {
			return "Broker [buy=" + buy + ", sell=" + sell + ", profit="
					+ profit + ", begin=" + begin + ", end=" + end + "]";
		}

	}

	public int[] getIndexes(int[] arr, int k) {

		int n = arr.length;

		int j = 0;
		int temp = 0;
		for (int i = 1; i < n; ++i) {
			if (arr[j] < arr[i]) {
				arr[j] *= -1;
				++j;
			} else {
				j = i;
			}
		}

		j = 0;
		for (int i = 1; i < n; ++i) {
			if (arr[i] > 0 && arr[j] > arr[i]) {
				arr[i] = 0;
				++j;
			} else {
				j = i;
			}
		}

		Set<Broker> set = new HashSet<Broker>();

		Map<Integer, Integer> forward = new HashMap<Integer, Integer>();

		int max = Integer.MIN_VALUE;
		Broker broker = new Broker();
		for (int i = 0; i < n; ++i) {
			if (arr[i] < 0) {
				if (arr[i] > max) {
					broker.begin = i;
					broker.buy = arr[i];
					forward.put(i, arr[i]);
					max = arr[i];
				}
			} else {
				if (arr[i] > 0) {
					broker.end = i;
					broker.sell = arr[i];
				}

				if (broker.begin != null && broker.end != null) {
					broker.getProfit();
					set.add(broker);
					broker = new Broker();
				}
				max = Integer.MIN_VALUE;
			}
		}

		System.out.println("Begin Index:");
		forward.forEach((key, value) -> System.out.println(key + ": " + value));

		Map<Integer, Integer> backward = new HashMap<Integer, Integer>();
		for (int i = 1; i < n; ++i) {
			if (arr[i] > 0) {
				backward.put(i, arr[i]);
			}
		}

		System.out.println("End Index:");
		backward.forEach(
				(key, value) -> System.out.println(key + ": " + value));

		System.out.println("----------Broker--------------");
		set.forEach(b -> System.out.println(b));
		System.out.println(" ");

		if (set.isEmpty())
			return null;
		if (k == 1)
			return maxBroker(set);
		if (set.size() <= k) {
			return transform(set);
		}
		return transformAndSort(set, k);
	}

	private int[] maxBroker(Set<Broker> set) {
		int begin = 0, end = 0;
		int buy = Integer.MIN_VALUE;
		int sell = Integer.MIN_VALUE;
		for (Broker b : set) {
			if (b.buy > buy) {
				begin = b.begin;
				buy = b.buy;
			}
			if (b.sell > sell && b.end > begin) {
				end = b.end;
				sell = b.sell;
			}
		}
		return new int[]{begin, end};
	}

	private int[] transformAndSort(Set<Broker> set, int k) {
		Broker[] arr = new Broker[set.size()];
		int i = 0;
		for (Broker b : set) {
			arr[i++] = b;
		}

		heapSortedBroker(arr, arr.length);

		for (Broker b : arr) {
			System.out.println("sorted - " + b);
		}

		int[] res = new int[k * 2];
		i = arr.length - 1;
		int j = res.length - 1;
		while (i >= 0 && j >= 0) {
			res[j--] = arr[i].begin;
			res[j--] = arr[i--].end;
		}

		Arrays.sort(res);

		return res;
	}

	public void heapSortedBroker(Broker[] arr, int n) {
		for (int i = n / 2 - 1; i >= 0; --i) {
			heapify(arr, n, i);
		}

		for (int i = n - 1; i >= 0; --i) {
			Broker temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			heapify(arr, i, 0);
		}

	}

	private void heapify(Broker[] arr, int n, int index) {
		int i = index;
		int left = i * 2 + 1;
		int right = left + 1;

		if (left < n && arr[left].profit > arr[i].profit)
			i = left;

		if (right < n && arr[right].profit > arr[i].profit)
			i = right;

		if (i != index) {
			Broker temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;

			heapify(arr, n, i);
		}

	}

	private int[] transform(Set<Broker> set) {
		Broker[] arr = new Broker[set.size()];
		int i = 0, j = 0;
		for (Broker b : set) {
			arr[i++] = b;
		}

		i = 0;
		int[] res = new int[set.size() * 2];
		while (i < arr.length && j < res.length) {
			res[j++] = arr[i].begin;
			res[j++] = arr[i++].end;
		}
		Arrays.sort(res);
		return res;
	}

	@Test
	public void test1() {
		System.out.println("                   Test1");
		int[] prices = {3, 2, 6, 5, 1, 3};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {1, 2, 4, 5};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test2() {
		System.out.println("                    Test2");
		int[] prices = {1, 2, 1, 6, 5, 1, 3};
		int[] actual = getIndexes(prices, 3);
		int[] expected = {0, 1, 2, 3, 5, 6};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void positiveTest3() {
		System.out.println("                  Test3");
		int[] prices = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {0, 9};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test4() {
		System.out.println("                 Test4");
		int[] prices = {1, 1, 2, 9, 4, 5, 6, 7};
		int[] actual = getIndexes(prices, 1);
		int[] expected = {1, 3};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test5() {
		System.out.println("                 Test5");
		int[] prices = new int[]{1, 4, 2, 3, 3, 5};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {0, 1, 4, 5};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test6() {
		System.out.println("                    Test6");
		int[] prices = new int[]{3, 2, 2};
		int[] actual = getIndexes(prices, 2);
		assertArrayEquals(null, actual);
	}

	@Test
	public void test7() {
		System.out.println("                       Test7");
		int[] prices = new int[]{3, 5, 9, 10, 2, 3, 29, 1, 2, 4, 5, 1, 22};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {4, 6, 11, 12};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test8() {
		System.out.println("                       Test8");
		int[] prices = new int[]{10, 5, 5, 7, 6};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {2, 3};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test9() {
		System.out.println("                    Test9");
		int[] prices = new int[]{3, 4, 6, 5, 7, 9, 1};
		int[] actual = getIndexes(prices, 2);

		int[] expected = {0, 2, 3, 5};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test10() {
		System.out.println("                   Test10");
		int[] prices = new int[]{5, 6, 7, 9, 8, 9, 12, 11, 13, 14, 13, 11, 15,
				4, 5, 7};
		int[] actual = getIndexes(prices, 3);
		int[] expected = {0, 3, 4, 6, 11, 12};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test11() {
		System.out.println("                           Test11");
		int[] prices = new int[]{6, 7, 5, 8, 10, 8, 9, 12, 9};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {2, 4, 5, 7};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test12() {
		System.out.println("                       Test12");
		int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 1};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {0, 5, 6, 8};
		assertArrayEquals(expected, actual);
	}
}
