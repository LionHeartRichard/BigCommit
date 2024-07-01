package topsolution;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfit {

	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		int[][] f = new int[k + 1][2];
		
		for (int j = 1; j <= k; ++j) {
			f[j][1] = -prices[0];
		}
		
		for (int i = 1; i < n; ++i) {
			for (int j = k; j > 0; --j) {
				f[j][0] = Math.max(f[j][1] + prices[i], f[j][0]);
				f[j][1] = Math.max(f[j - 1][0] - prices[i], f[j][1]);
			}
		}
	
		return f[k][0];
	}

	@Test
	public void test1() {
		int[] prices = {3, 2, 6, 5, 1, 3};
		int actual = maxProfit(2, prices);
		int expected = 6;
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		int[] prices = {1, 2, 1, 6, 5, 1, 3};
		int actual = maxProfit(3, prices);
		int expected = 8;
		assertEquals(expected, actual);
	}

}
