package com.profitshares;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * Вам дан массив prices, в котором prices[i] указана цена данной акции 
 * на текущий день. Найдите максимальную прибыль, которую вы можете получить. 
 * Вы можете совершить не более двух транзакций.
 * Примечание. Вы не можете совершать несколько транзакций одновременно.
 */

public class MaxProfitFor2ByuAndSell {
	public int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		int byu1 = -prices[0], byu2 = -prices[0], sell1 = 0, sell2 = 0;
		for (int i = 0; i < prices.length; ++i) {
			byu1 = Math.max(byu1, -prices[i]);
			sell1 = Math.max(sell1, byu1 + prices[i]);
			byu2 = Math.max(byu2, sell1 - prices[i]);
			sell2 = Math.max(sell2, byu2 + prices[i]);
		}
		return sell2;
	}

	@Test
	public void positiveTest1() {
		int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
		int actual = maxProfit(prices);
		assertEquals(6, actual);
	}
}
