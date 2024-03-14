package com.profitshares;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
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

	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	public int maxProfitShares(int[] prices, int k) {

		int n = prices.length;
		int[][] actions = new int[2][k + 1];
		int[][] indexesArray = new int[2][k];
		int[] arrProfites = new int[k];

		for (int i = n - 1; i >= 0; --i) {
			for (int act = 1; act >= 0; --act) {
				for (int limit = 1; limit <= k; ++limit) {
					int profit = 0, skip = 0;
					if (act == 1) {
						profit = actions[0][limit - 1] + prices[i];
					} else {
						profit = actions[1][limit] - prices[i];
					}
					skip = actions[act][limit];
					if (act == 0 && profit > skip) {
						arrProfites[limit - 1] = profit;
						indexesArray[0][limit - 1] = i;
						indexesArray[1][limit - 1] = i;
					}
					actions[act][limit] = Math.max(profit, skip);
				}
			}
		}

		return actions[0][k];

	}

	@Test
	public void positiveTest1() {
		int[] prices = {3, 2, 6, 5, 0, 3};
		int actual = maxProfitShares(prices, 2);
		assertEquals(7, actual);
	}

	@Test
	public void positiveTest2() {
		int[] prices = {0, 2, 0, 6, 5, 0, 3};
		int actual = maxProfitShares(prices, 3);
		assertEquals(11, actual);
	}

	@Test
	public void positiveTest3() {
		int[] prices = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int actual = maxProfitShares(prices, 1);
		assertEquals(9, actual);
	}

	@Test
	public void positiveTest4() {
		int[] prices = {0, 1, 2, 9, 4, 5, 6, 7};
		int actual = maxProfitShares(prices, 2);
		assertEquals(12, actual);
	}

	@Test
	public void positiveTest5() {
		int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
		int actual = maxProfitShares(prices, 2);
		assertEquals(6, actual);
	}
}
