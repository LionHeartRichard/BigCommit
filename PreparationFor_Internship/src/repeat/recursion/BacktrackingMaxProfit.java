package repeat.recursion;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class BacktrackingMaxProfit {

	public int[][] maxProfit(int[] prices, int i) {

		return null;
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
