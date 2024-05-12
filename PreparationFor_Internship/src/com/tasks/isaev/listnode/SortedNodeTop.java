package com.tasks.isaev.listnode;

public class SortedNodeTop {
	public ListNode sortList(ListNode head) {
		return backtracking(head, null);
	}

	private ListNode backtracking(ListNode begin, ListNode end) {

		if (begin == null || begin.next == null || begin == end)
			return begin;

		ListNode left = begin, right = begin, current = begin.next;
		boolean sorted = true;
		while (current != null && current != end) {
			ListNode temp = current.next;
			if (current.val < begin.val) {
				current.next = left;
				left = current;
				right.next = temp;
				sorted = false;
			} else {
				if (current.val < right.val)
					sorted = false;
				right = current;
			}
			current = temp;
		}
		if (sorted)
			return left;
		left = backtracking(left, begin);
		begin.next = backtracking(begin.next, end);
		return left;
	}

}
