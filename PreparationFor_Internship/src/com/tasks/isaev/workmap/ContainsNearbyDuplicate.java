package com.tasks.isaev.workmap;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

/*
 * Найдите ближайшие дубликаты в диапозоне k - числел.
 * Учитывая целочисленный массив nums и целое число k, возврат, true если есть два различных индекса 
 * i и j в массиве такие, что nums[i] == nums[j] и abs(i - j) <= k.
 */

public class ContainsNearbyDuplicate {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; ++i) {
			if (i > k) {
				set.remove(nums[i - k - 1]);
			}
			if (set.contains(nums[i])) {
				return true;
			}
			set.add(nums[i]);
		}
		return false;
	}

	@Test
	public void positiveTest1() {
		int[] nums = {1, 2, 3, 1};
		assertEquals(true, containsNearbyDuplicate(nums, 3));
	}

	@Test
	public void positiveTest2() {
		int[] nums = {1, 0, 1, 1};
		assertEquals(true, containsNearbyDuplicate(nums, 1));
	}

	@Test
	public void positiveTest3() {
		int[] nums = {1, 2, 3, 1, 2, 3};
		assertEquals(false, containsNearbyDuplicate(nums, 2));
	}
}
