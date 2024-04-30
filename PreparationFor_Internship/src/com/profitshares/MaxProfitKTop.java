package com.profitshares;

import static org.junit.Assert.assertEquals;

import java.util.*;

import javax.print.attribute.standard.Copies;

import org.junit.jupiter.api.Test;

public class MaxProfitKTop {

	private int[] carry;
	public int maxProfit(int k, int[] prices) {
		carry = new int[prices.length];
		getCarryValues(prices, k);
		return getProfits(k);

	}

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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(buy, sell);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Broker other = (Broker) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return buy == other.buy && sell == other.sell;
		}

		private MaxProfitKTop getEnclosingInstance() {
			return MaxProfitKTop.this;
		}

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

	private int getProfits(int k) {

		PriorityQueue<Broker> pQue = new PriorityQueue<Broker>(k, new Broker());
		int buy = 0, money = Integer.MIN_VALUE;
		int[] profits = new int[k];

		for (int i = 1; i < carry.length; ++i) {
			if (money < carry[i - 1] && carry[i - 1] <= 0) {
				buy = i - 1;
				money = carry[i - 1];
			}
			if (carry[i - 1] <= 0 && carry[i] > 0) {
				if (profits[0] < carry[i] + money) {
					profits[0] = carry[i] + money;
					pQue.add(new Broker(profits[0], buy, i));
					Arrays.sort(profits);
				}
				money = Integer.MIN_VALUE;
			}
		}

		if (pQue.size() > k) {
			PriorityQueue<Broker> temp = new PriorityQueue<Broker>(k,
					new Broker());
			temp.addAll(pQue);
			PriorityQueue<Broker> newPrioQue = new PriorityQueue<Broker>(k,
					new Broker());

			for (Broker a : pQue) {
				for (Broker b : temp) {
					if (!b.equals(a) && a.sell < b.buy) {
						int newMoney = carry[a.buy] + carry[b.sell];
						Broker newBroker = new Broker(newMoney, a.buy, b.sell);
						newPrioQue.add(newBroker);
					}

				}
			}

			while (pQue.size() != k) {
				pQue.poll();
				temp.poll();
			}

			Broker make = null;
			for (Broker a : newPrioQue) {
				for (Broker b : temp) {
					if (b.buy < a.buy
							|| b.sell <= a.sell && a.money > b.money) {
						if (make == null
								|| (make.buy > a.sell || make.sell < a.buy)) {
							temp.poll();
							pQue.remove(b);
							pQue.add(a);
							make = a;
						}
					}
				}
			}

//			while (pQue.size() != k) {
//				pQue.poll();
//			}

			int newResult = 0;
			while (!pQue.isEmpty()) {
				Broker b = pQue.poll();
				newResult += b.money;
			}

			return newResult;
		}

		int result = 0;
		for (Broker b : pQue) {
			result += b.money;
		}

		return result;
	}

	@Test
	public void test1() {
		int[] prices = {3, 2, 6, 5, 0, 3};
		int actual = maxProfit(2, prices);
		assertEquals(7, actual);
	}

	@Test
	public void test2() {
		int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
		int actual = maxProfit(2, prices);
		assertEquals(13, actual);
	}

	@Test
	public void test3() {
		int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0, 9};
		int actual = maxProfit(2, prices);
		assertEquals(17, actual);
	}

	@Test
	public void test4() {
		int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
		int actual = maxProfit(2, prices);
		assertEquals(6, actual);
	}
}
