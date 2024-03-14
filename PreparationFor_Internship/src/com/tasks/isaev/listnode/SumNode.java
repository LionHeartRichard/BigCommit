package com.tasks.isaev.listnode;

/*
 * Вам даны два непустых связанных списка, представляющих два неотрицательных целых числа. 
 * Цифры хранятся в обратном порядке , и каждый из их узлов содержит одну цифру. 
 * Сложите два числа и верните сумму в виде связанного списка.
 * Вы можете предположить, что эти два числа не содержат ведущих нулей, кроме самого числа 0.
 * Входные данные: l1 = [2,4,3], l2 = [5,6,4]
 * Выходные данные: [7,0,8]
 * Объяснение: 342 + 465 = 807.
 */

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class SumNode {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode head = new ListNode(0);
		ListNode l3 = head;
		while (l1 != null || l2 != null || carry != 0) {
			int sum = carry;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			l3.next = new ListNode(sum % 10);
			carry = sum / 10;
			l3 = l3.next;
		}
		return head.next;
	}

	@Test
	public void positivrTest1() {

		ListNode head1 = new ListNode(0);
		ListNode l1 = head1;
		l1.next = new ListNode(2);
		l1 = l1.next;
		l1.next = new ListNode(4);
		l1 = l1.next;
		l1.next = new ListNode(3);
		l1 = l1.next;

		ListNode head2 = new ListNode(0);
		ListNode l2 = head2;
		l2.next = new ListNode(5);
		l2 = l2.next;
		l2.next = new ListNode(6);
		l2 = l2.next;
		l2.next = new ListNode(4);
		l2 = l2.next;

		ListNode actual = addTwoNumbers(head1.next, head2.next);

		ListNode headExpecteds = new ListNode(7);
		ListNode expecteds = headExpecteds;
		expecteds.next = new ListNode(0);
		expecteds = expecteds.next;
		expecteds.next = new ListNode(8);
		expecteds = expecteds.next;

		assertEquals(headExpecteds.val, actual.val);
		headExpecteds = headExpecteds.next;
		actual = actual.next;
		assertEquals(headExpecteds.val, actual.val);
		headExpecteds = headExpecteds.next;
		actual = actual.next;
		assertEquals(headExpecteds.val, actual.val);
	}
}
