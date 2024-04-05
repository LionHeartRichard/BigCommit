package com.tasks.isaev.listnode;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * Учитывая связанный список, поменяйте местами head узлы списка и 
 * верните измененный список. k
 * k - является положительным целым числом и меньше или равно длине связанного списка. 
 * Если количество узлов не кратно количеству k пропущенных узлов, 
 * в конце концов, оно должно остаться таким, какое оно есть.
 * Вы не можете изменять значения в узлах списка, можно изменять только сами узлы.
 * 
 * Example 1:
 * Input: head =[1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * 
 * Example 2:
 * Input: head =[1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 */

public class ReverseKGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode keepHead = head;
		ListNode current = null;
		ListNode previous = null;

		while (keepHead != null) {
			int count = 1;
			current = keepHead;
			while (current != null && count < k) {
				current = current.next;
				++count;
			}
			if (current == null) {
				previous.next = keepHead;
				return head;
			}

			ListNode next = current.next;
			current.next = null;
			reverse(keepHead);
			if (keepHead == head) {
				head = current;
			} else {
				previous.next = current;
			}
			previous = keepHead;
			keepHead = next;
		}
		return head;
	}
	private ListNode reverse(ListNode head) {
		ListNode previous = null;
		ListNode current = head;
		ListNode next = head;

		while (current != null) {
			next = next.next;

			current.next = previous;
			previous = current;
			current = next;
		}
		return previous;
	}

	@Test
	public void test1() {
		ListNode nodeTask = new ListNode();
		nodeTask.next = new ListNode(1);
		ListNode headTask = nodeTask.next;
		nodeTask = nodeTask.next;
		for (int i = 2; i < 6; ++i) {
			nodeTask.next = new ListNode(i);
			nodeTask = nodeTask.next;
		}
		int k = 2;
		ListNode actual = reverseKGroup(headTask, k);

		ListNode expectedNode = new ListNode();
		expectedNode.next = new ListNode(2);
		ListNode expected = expectedNode.next;
		expectedNode = expectedNode.next;
		expectedNode.next = new ListNode(1);
		expectedNode = expectedNode.next;
		expectedNode.next = new ListNode(4);
		expectedNode = expectedNode.next;
		expectedNode.next = new ListNode(3);
		expectedNode = expectedNode.next;
		expectedNode.next = new ListNode(5);
		expectedNode = expectedNode.next;
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		ListNode nodeTask = new ListNode();
		nodeTask.next = new ListNode(1);
		ListNode headTask = nodeTask.next;
		nodeTask = nodeTask.next;
		for (int i = 2; i < 7; ++i) {
			nodeTask.next = new ListNode(i);
			nodeTask = nodeTask.next;
		}
		int k = 3;
		ListNode actual = reverseKGroup(headTask, k);

		ListNode expectedNode = new ListNode();
		expectedNode.next = new ListNode(3);
		ListNode expected = expectedNode.next;
		expectedNode = expectedNode.next;
		expectedNode.next = new ListNode(2);
		expectedNode = expectedNode.next;
		expectedNode.next = new ListNode(1);
		expectedNode = expectedNode.next;
		expectedNode.next = new ListNode(6);
		expectedNode = expectedNode.next;
		expectedNode.next = new ListNode(5);
		expectedNode = expectedNode.next;
		expectedNode.next = new ListNode(4);
		expectedNode = expectedNode.next;
		assertEquals(expected, actual);
	}
}
