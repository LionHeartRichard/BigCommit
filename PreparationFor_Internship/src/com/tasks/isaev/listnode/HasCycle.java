package com.tasks.isaev.listnode;

/*
 * Учитывая head-заголовок связанного списка, определите, есть ли в связанном списке цикл.
 * В связанном списке существует цикл, если в списке есть какой-то узел, 
 * к которому можно снова добраться, непрерывно следуя за  next указателем. 
 * Внутри pos используется для обозначения индекса узла,  next к которому подключен 
 * указатель хвоста. Обратите внимание, что это  pos не передается в качестве параметра.
 * Возврат  true, если в связанном списке есть цикл . В противном случае верните false.
 */

public class HasCycle {
	public boolean hasCycle(ListNode head) {

		ListNode slowPointer = head, fastPointer = head;
		while (fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
			if (fastPointer == slowPointer) {
				return true;
			}
		}

		return false;
	}
}
