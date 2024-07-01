package repeat;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;
import java.util.*;

public class DecomposeMaxProfit {

	public int[][] maxProfit(int[] arr, int k) {

		int n = arr.length;
		changeArrBuyPrices(arr, n);

		if (!checkBasicCaseOne(arr, n))
			return null;

		changeArrSellPrices(arr, n);

		List<Integer> buy = getBuy(arr, n);
		List<Integer> sell = getSell(arr, n);

		if (buy.size() == k) {
			return getArrayListSizeK(buy, sell, k);
		}

		if (buy.size() > k) {
			return mergeInterval(buy, sell, sell.size(), arr, k);
		} else {
			return mergeBuyAndSell(buy, sell);
		}
	}

	private int[][] mergeBuyAndSell(List<Integer> buy, List<Integer> sell) {
		int[][] res = new int[buy.size()][2];
		for (int i = 0; i < res.length; ++i) {
			res[i][0] = buy.get(i);
			res[i][1] = sell.get(i);
		}
		return res;
	}

	private int[][] mergeInterval(List<Integer> buy, List<Integer> sell,
			int size, int[] arr, int k) {

		int[][] forward = new int[size][2];
		int[][] backward = new int[size][2];
		int primal = size;
		int count = size;
		int temp = 0;
		for (int i = 0; i < size; ++i) {
			temp = buy.get(i);
			forward[i][0] = arr[temp] * -1;
			forward[i][1] = temp;
			temp = sell.get(i);
			backward[i][0] = arr[temp];
			backward[i][1] = temp;
		}

		int previous = 0, current = 1;

		while (count > k) {
			if (previous < size && current < size) {
				if (checkInterval(forward[previous][0], backward[previous][0],
						forward[current][0], backward[current][0],
						forward[current][1])) {
					forward[current][1] = -1;
					backward[previous][1] = -1;
					--count;
					++previous;
					++current;
					continue;
				}
				previous = current;
				++current;
			} else {
				previous = 0;
				current = 1;
				if (primal == count) {
					--count;
					primal = count;
				}
			}
		}

		int[][] res = new int[k][2];

		int j = 0;
		for (int i = 0; i < size; ++i) {
			if (forward[i][1] != -1)
				res[j++][0] = forward[i][1];
		}
		j = 0;
		for (int i = 0; i < size; ++i) {
			if (backward[i][1] != -1)
				res[j++][1] = backward[i][1];
		}

		return res;
	}

	private boolean checkInterval(int buy1, int sell1, int buy2, int sell2,
			int checkMain) {
		if (checkMain == -1)
			return false;
		if (buy1 <= buy2 && sell1 <= sell2)
			return true;
		return false;
	}

	private int[][] getArrayListSizeK(List<Integer> buy, List<Integer> sell,
			int k) {
		int[][] arr = new int[k][2];
		for (int i = 0; i < k; ++i) {
			arr[i][0] = buy.get(i);
			arr[i][1] = sell.get(i);
		}
		return arr;
	}

	private List<Integer> getBuy(int[] arr, int n) {
		int max = Integer.MIN_VALUE;
		List<Integer> buy = new ArrayList<Integer>();

		for (int i = 0; i < n - 1; ++i) {
			if (arr[i] < 0 && arr[i] > max) {
				buy.add(i);
				max = arr[i];
			} else {
				if (arr[i] > 0)
					max = Integer.MIN_VALUE;
			}
		}

		return buy;
	}

	private List<Integer> getSell(int[] arr, int n) {
		List<Integer> sell = new ArrayList<Integer>();
		for (int i = 1; i < n; ++i) {
			if (arr[i] > 0)
				sell.add(i);
		}
		return sell;
	}

	private void changeArrSellPrices(int[] arr, int n) {
		int j = 0;
		for (int i = 1; i < n; ++i) {
			if (arr[i] > 0 && arr[j] > arr[i]) {
				arr[i] = 0;
				++j;
			} else {
				j = i;
			}
		}

	}

	private boolean checkBasicCaseOne(int[] arr, int n) {
		for (int i = 0; i < n; ++i) {
			if (arr[i] < 0)
				return true;
		}
		return false;
	}

	private void changeArrBuyPrices(int[] arr, int n) {
		int j = 0;
		for (int i = 1; i < n; ++i) {
			if (j < n - 1 && arr[j] < arr[i]) {
				arr[j] *= -1;
				++j;
			} else {
				j = i;
			}
		}
	}

	@Test
	public void test1() {
		System.out.println("                   Test1");
		int[] prices = {3, 2, 6, 5, 1, 3};
		int[][] actual = maxProfit(prices, 2);
		int[][] expected = {{1, 2}, {4, 5}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test2() {
		System.out.println("                    Test2");
		int[] prices = {1, 2, 1, 6, 5, 1, 3};
		int[][] actual = maxProfit(prices, 3);
		int[][] expected = {{0, 1}, {2, 3}, {5, 6}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void positiveTest3() {
		System.out.println("                  Test3");
		int[] prices = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[][] actual = maxProfit(prices, 2);
		int[][] expected = {{0, 9}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test4() {
		System.out.println("                 Test4");
		int[] prices = {1, 1, 2, 9, 4, 5, 6, 7};
		int[][] actual = maxProfit(prices, 1);
		int[][] expected = {{1, 3}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test5() {
		System.out.println("                 Test5");
		int[] prices = new int[]{1, 4, 2, 3, 3, 5};
		int[][] actual = maxProfit(prices, 2);
		int[][] expected = {{0, 1}, {4, 5}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test6() {
		System.out.println("                    Test6");
		int[] prices = new int[]{3, 2, 2};
		int[][] actual = maxProfit(prices, 2);
		assertArrayEquals(null, actual);
	}

	@Test
	public void test7() {
		System.out.println("                       Test7");
		int[] prices = new int[]{3, 5, 9, 10, 2, 3, 29, 1, 2, 4, 5, 1, 22};
		int[][] actual = maxProfit(prices, 2);
		int[][] expected = {{4, 6}, {11, 12}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test8() {
		System.out.println("                       Test8");
		int[] prices = new int[]{10, 5, 5, 7, 6};
		int[][] actual = maxProfit(prices, 2);
		int[][] expected = {{2, 3}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test9() {
		System.out.println("                    Test9");
		int[] prices = new int[]{3, 4, 6, 5, 7, 9, 1};
		int[][] actual = maxProfit(prices, 2);

		int[][] expected = {{0, 2}, {3, 5}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test10() {
		System.out.println("                   Test10");
		int[] prices = new int[]{5, 6, 7, 9, 8, 9, 12, 11, 13, 14, 13, 11, 15,
				4, 5, 7};
		int[][] actual = maxProfit(prices, 3);
		int[][] expected = {{0, 9}, {11, 12}, {13, 15}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test11() {
		System.out.println("                           Test11");
		int[] prices = new int[]{6, 7, 5, 8, 10, 8, 9, 12, 9};
		int[][] actual = maxProfit(prices, 2);
		int[][] expected = {{2, 4}, {5, 7}};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test12() {
		System.out.println("                       Test12");
		int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 1};
		int[][] actual = maxProfit(prices, 2);
		int[][] expected = {{0, 5}, {6, 8}};
		assertArrayEquals(expected, actual);
	}
}
