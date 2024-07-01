package repeat;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfit {

	public int maxProfit(int k, int[] prices) {
		int n = prices.length;

		int[][] arr = new int[k + 1][2];

		for (int j = k; j > 0; --j) {
			arr[j][1] = -prices[0];
		}

		for (int i = 1; i < n; ++i) {
			for (int j = k; j > 0; --j) {
				arr[j][0] = Math.max(arr[j][1] + prices[i], arr[j][0]);
				arr[j][1] = Math.max(arr[j - 1][0] - prices[i], arr[j][1]);
			}
		}

		return arr[k][0];
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
