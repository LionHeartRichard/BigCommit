package com.profitshares;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

import java.util.*;

/*
Вам дан целочисленный массив prices, где prices[i]— цена данной акции в день, 
и целое число - k
Найдите дни-индексы массива где вы сможете получить максимальную прибыль.
Т.е. день покупки и день продажи акции. 
Вы можете совершать максимальное количество k-транзакций: т.е. вы можете покупать максимальное k-количество раз 
и продавать максимальное количество k-раз.
Примечание. Вы не можете совершать несколько транзакций одновременно.
 */

public class MaxProfitSharesForKReturnDayBuyAndSale {

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
		return getProfit(k);
	}

	private int[] getProfit(int k) {
		PriorityQueue<Broker> pque = new PriorityQueue<Broker>(k, new Broker());
		int[] profits = new int[k];
		int buy = 0, buyMoney = Integer.MIN_VALUE;
		for (int i = 1; i < carry.length; ++i) {
			if (buyMoney < carry[i - 1] && carry[i - 1] < 0) {
				buy = i - 1;
				buyMoney = carry[i - 1];
			}
			if (carry[i - 1] < 0 && carry[i] > 0) {
				if (profits[0] < (carry[i] + buyMoney)) {
					profits[0] = carry[i] + buyMoney;
					pque.add(new Broker(carry[i] + buyMoney, buy, i));
					Arrays.sort(profits);
					if (pque.size() > k) {
						pque.poll();
					}
				}
				buyMoney = Integer.MIN_VALUE;
			}
		}
		int[] result = new int[k * 2];
		int i = 0;
		for (Broker b : pque) {
			if (i < result.length) {
				result[i++] = b.buy;
				result[i++] = b.sell;
			} else {
				break;
			}
		}
		if (pque.size() == k) {
			Arrays.sort(result);
		}
		return result;
	}

	private void getCarryValues(int[] prices, int k) {
		for (int i = 1; i < prices.length; ++i) {
			if (prices[i - 1] < prices[i]) {
				carry[i - 1] = -prices[i - 1];
				if (i == prices.length - 1) {
					carry[i] = prices[i];
				}
			} else {
				carry[i - 1] = prices[i - 1];
			}
		}
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
