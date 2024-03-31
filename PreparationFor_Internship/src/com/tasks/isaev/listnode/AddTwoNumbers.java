package com.tasks.isaev.listnode;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/* Вам даны два непустых связанных списка, представляющих два неотрицательных 
 * целых числа. Цифры хранятся в обратном порядке , и каждый из их узлов 
 * содержит одну цифру. Сложите два числа и верните сумму в виде связанного списка.
 * Вы можете предположить, что эти два числа не содержат ведущих нулей, 
 * кроме самого числа 0.
 * 
 * Пример 1:
 * Входные данные: l1 = [2,4,3], l2 = [5,6,4]
 * Выходные данные: [7,0,8]
 * Объяснение: 342 + 465 = 807.
 */

public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode head = new ListNode(0);
		ListNode tail = head;
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
			tail.next = new ListNode(sum % 10);
			tail = tail.next;
			carry = sum / 10;
		}
		return head.next;
	}

	@Test
	public void test1() {
		ListNode l1 = new ListNode();
		l1.next = new ListNode(2);
		ListNode headL1 = l1.next;
		l1 = l1.next;
		l1.next = new ListNode(4);
		l1 = l1.next;
		l1.next = new ListNode(3);
		l1 = l1.next;

		ListNode l2 = new ListNode();
		l2.next = new ListNode(5);
		ListNode headL2 = l2.next;
		l2 = l2.next;
		l2.next = new ListNode(6);
		l2 = l2.next;
		l2.next = new ListNode(4);
		l2 = l2.next;

		ListNode expected = new ListNode();
		expected.next = new ListNode(7);
		ListNode expectedHead = expected.next;
		expected = expected.next;
		expected.next = new ListNode(0);
		expected = expected.next;
		expected.next = new ListNode(8);
		expected = expected.next;

		ListNode actual = addTwoNumbers(headL1, headL2);
		assertEquals(expectedHead.val, actual.val);
		expectedHead = expectedHead.next;
		actual = actual.next;
		assertEquals(expectedHead.val, actual.val);
		expectedHead = expectedHead.next;
		actual = actual.next;
		assertEquals(expectedHead.val, actual.val);
	}
}
