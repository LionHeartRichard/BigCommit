package com.tasks.isaev.listnode;

import java.util.*;

public class SortedNode {

	public ListNode sortList(ListNode head) {
		List<Integer> list = new ArrayList<Integer>();
		ListNode n = head;
		while (head != null) {
			list.add(head.val);
			head = head.next;
		}
		head = n;
		Collections.sort(list);
		for (int i : list) {
			n.val = i;
			n = n.next;
		}
		return head;
	}
}
