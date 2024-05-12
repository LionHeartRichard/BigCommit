package com.tasks.isaev.recursive.classicbacktracking;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class SortedNodeTopSolution {
	public ListNode sortedListNode(ListNode head) {
		return backtracking(head, null);
	}

	private ListNode backtracking(ListNode begin, ListNode end) {

		if (begin == null || begin.next == null || begin == end)
			return begin;

		boolean thisSorted = true;

		ListNode left = begin, right = begin, current = begin.next;

		while (current != null && current != end) {
			ListNode temp = current.next;
			if (current.val < begin.val) {
				current.next = left;
				left = current;
				right.next = temp;
				thisSorted = false;
			} else {
				if (current.val < right.val)
					thisSorted = false;
				right = current;
			}
			current = temp;
		}
		if (thisSorted)
			return left;
		left = backtracking(left, begin);
		begin.next = backtracking(begin.next, end);

		return left;
	}

	@Test
	public void test1() {

		ListNode nodeTask = new ListNode();
		nodeTask.next = new ListNode(1);
		ListNode headTask = nodeTask.next;
		nodeTask = nodeTask.next;

		for (int i = 5; i > 1; --i) {
			nodeTask.next = new ListNode(i);
			nodeTask = nodeTask.next;
		}

		ListNode actual = sortedListNode(headTask);

		ListNode expectedNode = new ListNode();
		expectedNode.next = new ListNode(1);
		ListNode expected = expectedNode.next;
		expectedNode = expectedNode.next;

		for (int i = 2; i < 6; ++i) {
			expectedNode.next = new ListNode(i);
			expectedNode = expectedNode.next;
		}

		assertEquals(expected, actual);
	}
}
