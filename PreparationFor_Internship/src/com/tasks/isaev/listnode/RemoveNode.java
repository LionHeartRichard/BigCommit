package com.tasks.isaev.listnode;

public class RemoveNode {
	public ListNode removeNthFromEnd(ListNode node, int n) {

		int count = 1;

		while (node != null && node.next != null) {
			if (count == n) {
				node.next = node.next.next;
			}
			node = node.next;
		}

		return node;
	}

}
