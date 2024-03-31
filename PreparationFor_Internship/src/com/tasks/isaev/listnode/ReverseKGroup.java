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
		ListNode temp = head; // TO TRAVERSE THROUGH EACH NODE IN LL
		ListNode kthNode = null; // USED TO MAKE K SIZE NODE TO REVERSE & ALSO
									// HELP IN KEEPING THE TRACK OF UNLINKED LL
									// THROUGTH nextNode
		ListNode preNode = null; // USED TO MAKE LINK BETWEEN 2 REVERSED LL

		while (temp != null) {
			int cnt = 1; // AS WE WILL GONNA TRAVERSE kthNode FOR K NODE SIZE LL
							// TO REVERSE.
							// STARTING OF kthNode IS FROM temp NODE SO cnt = 1.
			kthNode = temp;
			while (kthNode != null && cnt < k) {
				kthNode = kthNode.next;
				cnt++;
			}
			if (kthNode == null) { // when u have no of node less than the k
									// size. Then return the remaining nodes as
									// it is.
				preNode.next = temp; // prenode is at the last node of the
										// reversed ll and temp is at the
										// beginning of the remaining original
										// LL
				return head;
			}

			ListNode nextNode = kthNode.next;// before reversing the kth size
												// node, keep the track of next
												// node.
			kthNode.next = null;

			reverse(temp); // reverse the k size ll. ex: (temp)1->2->3(kthNode)
							// AFTER REVERSE (kthNode)3->2->1(temp)

			if (temp == head) { // when both head and temp points at the same
								// node(in the beginning), so when u reverse the
								// LL,
				// the temp and head both will be at last node of reversed
				// ll.(It is true for only 1 time)

				head = kthNode; // to make head pointer as head node of the LL
			} else {
				preNode.next = kthNode;
			}

			preNode = temp; // keeps the track of the last Node of the reverse
							// LL
			temp = nextNode; // Now again take the temp to the head of the
								// remaining original LL
		}
		return head;
	}
	private ListNode reverse(ListNode head) {
		ListNode preNode = null;
		ListNode curNode = head;
		ListNode nextNode = head;

		while (curNode != null) {
			nextNode = nextNode.next;

			curNode.next = preNode;

			preNode = curNode;
			curNode = nextNode;
		}
		return preNode;
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
