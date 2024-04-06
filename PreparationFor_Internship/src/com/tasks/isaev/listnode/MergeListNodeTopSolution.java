package com.tasks.isaev.listnode;

public class MergeListNodeTopSolution {

	public ListNode mergeKLists(ListNode[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		return dividing(arr, 0, arr.length - 1);
	}

	private ListNode dividing(ListNode[] arr, int begin, int end) {
		if (begin == end) {
			return arr[begin];
		}
		if (begin + 1 == end) {
			return merge(arr[begin], arr[end]);
		}
		int mid = begin + (end - begin) / 2;
		ListNode left = dividing(arr, begin, mid);
		ListNode right = dividing(arr, mid + 1, end);
		return merge(left, right);
	}

	private ListNode merge(ListNode left, ListNode right) {
		ListNode keep = new ListNode(0);
		ListNode current = keep;

		while (left != null && right != null) {
			if (left.val < right.val) {
				current.next = left;
				left = left.next;
			} else {
				current.next = right;
				right = right.next;
			}
			current = current.next;
		}
		current.next = (left != null) ? left : right;

		return keep.next;
	}
}
