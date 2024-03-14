package com.profitshares;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/*
Вам дан целочисленный массив prices, где prices[i]— цена данной акции в день, 
и целое число - k
Найдите максимальную прибыль, которую вы можете получить. 
Вы можете совершать максимальное количество k-транзакций: т.е. вы можете покупать максимальное k-количество раз 
и продавать максимальное количество k-раз.
Примечание. Вы не можете совершать несколько транзакций одновременно.
 */

public class MaxProfitSharesForK {

	public int maxProfit(int k, int[] prices) {
		int n = prices.length;

		int[][] currentArrDifference = new int[2][k + 1];

		for (int index = n - 1; index >= 0; --index) {
			for (int act = 1; act >= 0; --act) {
				for (int limit = 1; limit <= k; ++limit) {
					int profit = 0, buyOrSale = 0, skip = 0;
					if (act == 1) {
						buyOrSale = currentArrDifference[0][limit - 1]
								+ prices[index];
					} else {
						buyOrSale = currentArrDifference[1][limit]
								- prices[index];
					}
					skip = currentArrDifference[act][limit];
					profit = Math.max(buyOrSale, skip);
					currentArrDifference[act][limit] = profit;
				}
			}
		}
		return currentArrDifference[0][k];
	}

	@Test
	public void positiveTest() {
		int[] price = {3, 2, 6, 5, 0, 3};
		int actual = maxProfit(2, price);
		assertEquals(7, actual);
	}
}
