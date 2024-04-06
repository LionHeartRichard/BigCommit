package com.tasks.isaev.listnode;

import java.util.*;

/*
 * Вам дан массив k связанных списков arr, каждый связанный список отсортирован в 
 * порядке возрастания.
 * Объедините все связанные списки в один отсортированный связанный список и верните его.
 */

public class MergeListNode {

	public ListNode mergeKLists(ListNode[] arr) {

		if (arr.length == 0) {
			return null;
		}

		ListNode res = new ListNode();
		List<Integer> carry = new ArrayList<>();

		for (int i = 0; i < arr.length; ++i) {
			carry.addAll(getValueList(arr[i]));
		}

		if (!carry.isEmpty()) {
			Collections.sort(carry);
			res.next = new ListNode(carry.get(0));
			ListNode head = res.next;
			res = res.next;
			for (int i = 1; i < carry.size(); ++i) {
				res.next = new ListNode(carry.get(i));
				res = res.next;
			}
			return head;
		}
		return null;
	}

	private List<Integer> getValueList(ListNode current) {
		List<Integer> values = new ArrayList<Integer>();
		while (current != null) {
			values.add(current.val);
			current = current.next;
		}
		return values;
	}
}
