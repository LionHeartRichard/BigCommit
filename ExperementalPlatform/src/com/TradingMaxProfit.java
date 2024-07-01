package com;

import org.junit.jupiter.api.Test;

public class TradingMaxProfit {

	public static int maxProfit(int[] prices) {

		int n = prices.length;
		if (n <= 1)
			return 0;

		int[] changes = new int[n - 1];
		for (int i = 0; i < n - 1; ++i) {
			changes[i] = prices[i + 1] - prices[i];
		}

		int maxEndingHere = changes[0];
		int maxSoFar = changes[0];

		for (int i = 1; i < n - 1; ++i) {
			maxEndingHere = Math.max(changes[i], maxEndingHere + changes[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}

		return Math.max(maxSoFar, 0);
	}

	
	@Test
	public void test1() {
		
	}
}
