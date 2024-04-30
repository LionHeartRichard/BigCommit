package com.hard;

import static org.junit.Assert.assertArrayEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class ProfitForKSell {

	private class Broker implements Comparator<Broker> {

		int money;
		int buy;
		int sell;

		Broker() {
		}

		Broker(int money, int buy, int sell) {
			this.money = money;
			this.buy = buy;
			this.sell = sell;
		}

		public int compare(Broker b1, Broker b2) {
			if (b1.money < b2.money) {
				return -1;
			}
			if (b1.money > b2.money) {
				return 1;
			}
			return 0;
		}

	}

	private int[] carry;
	public int[] getIndexes(int[] prices, int k) {
		carry = new int[prices.length];
		getCarryValues(prices, k);
		return getProfits(k);
	}

	private void getCarryValues(int[] prices, int k) {
		for (int i = 1; i < prices.length; ++i) {
			if (prices[i - 1] < prices[i]) {
				carry[i - 1] = -prices[i - 1];
				if (i == prices.length - 1)
					carry[i] = prices[i];
			} else {
				carry[i - 1] = prices[i - 1];
			}
		}
	}

	private int[] getProfits(int k) {

		PriorityQueue<Broker> pQue = new PriorityQueue<Broker>(k, new Broker());
		int buy = 0, money = Integer.MIN_VALUE;
		int[] profits = new int[k];

		for (int i = 1; i < carry.length; ++i) {
			if (money < carry[i - 1] && carry[i - 1] < 0) {
				buy = i - 1;
				money = carry[i - 1];
			}
			if (carry[i - 1] < 0 && carry[i] > 0) {
				if (profits[0] < carry[i] + money) {
					profits[0] = carry[i] + money;
					pQue.add(new Broker(profits[0], buy, i));

					Arrays.sort(profits);

					if (pQue.size() > k)
						pQue.poll();
				}
				money = Integer.MIN_VALUE;
			}
		}

		int[] result = new int[k * 2];
		int i = 0;
		for (Broker b : pQue) {
			if (i < result.length) {
				result[i++] = b.buy;
				result[i++] = b.sell;
			} else {
				break;
			}
		}
		if (pQue.size() == k) {
			Arrays.sort(result);
		}
		return result;
	}

	@Test
	public void test1() {
		int[] prices = {3, 2, 6, 5, 1, 3};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {1, 2, 4, 5};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test2() {
		int[] prices = {1, 2, 1, 6, 5, 1, 3};
		int[] actual = getIndexes(prices, 3);
		int[] expected = {0, 1, 2, 3, 5, 6};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void positiveTest3() {
		int[] prices = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {0, 9, 0, 0};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test4() {
		int[] prices = {1, 1, 2, 9, 4, 5, 6, 7};
		int[] actual = getIndexes(prices, 1);
		int[] expected = {1, 3};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test5() {
		int[] prices = new int[]{1, 4, 2, 3, 3, 5};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {0, 1, 4, 5};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test6() {
		int[] prices = new int[]{3, 2, 2};
		int[] actual = getIndexes(prices, 2);
		assertArrayEquals(new int[]{0, 0, 0, 0}, actual);
	}

	@Test
	public void test7() {
		int[] prices = new int[]{3, 5, 9, 10, 2, 3, 29, 1, 2, 4, 5, 1, 22};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {4, 6, 11, 12};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test8() {
		int[] prices = new int[]{10, 5, 5, 7, 6};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {2, 3, 0, 0};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test9() {
		int[] prices = new int[]{3, 4, 6, 5, 7, 9, 1};
		int[] actual = getIndexes(prices, 2);

		int[] expected = {0, 2, 3, 5};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test10() {
		int[] prices = new int[]{5, 6, 7, 9, 8, 9, 12, 11, 13, 14, 13, 11, 15,
				4, 5, 7};
		int[] actual = getIndexes(prices, 3);
		int[] expected = {0, 3, 4, 6, 11, 12};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void test11() {
		int[] prices = new int[]{6, 7, 5, 8, 10, 8, 9, 12, 9};
		int[] actual = getIndexes(prices, 2);
		int[] expected = {2, 4, 5, 7};
		assertArrayEquals(expected, actual);
	}
}
