package linkedlist;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 */
public class FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		return null;
	}

	/**
	 * 找到链表第一个入环节点，如果无环，返回null
	 * @param head 头节点
	 * @return 第一个入环节点或 null
	 */
	public static Node getLoopNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node slow = head.next, fast = head.next.next;
		// 如果有环，那么快慢指针必定在环上相遇
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		/**
		 * 设L1为无环长度，L2为环长，L3为两指针相遇时慢速指针在环上走过的距离，此时L3一定小于环总长L2
		 * （这是因为当慢速指针刚进入环时，快速指针已经在环中，且距离慢速指针的距离最长为L2-1，需要追赶的距离为L2-1，
		 * 即刚好在慢速指针的下一个节点，需要几乎一整圈的距离来追赶，赶上时，慢速指针也不能走完一圈）。
		 * 此时设慢速指针走过的节点数为N，则可列出：快速指针走过的节点数为： 2N = L1 + k * L2 + L3；
		 * （这里快速指针走过的节点数一定是慢速指针走过的2倍）。慢速指针走过的节点数为： N = L1 + L3；
		 * 则相减可得， N = k * L2 , 于是得到 k * L2 = L1 + L3;
		 * 即， L1 = (k-1) * L2 + (L2 - L3) （这里k至少是大于等于1的，因为快速指针至少要多走一圈）
		 * 即 L1的长度 = 环长的整数倍 + 相遇点到入口点的距离
		 */
		fast = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	/**
	 * 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
	 * @param head1 链表 1 头节点
	 * @param head2 链表 2 头节点
	 * @return 一个相交节点或 null
	 */
	public static Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node cur1 = head1;
		Node cur2 = head2;
		int l = 0;
		// 链表 1 走到尾部，记录长度
		while (cur1.next != null) {
			cur1 = cur1.next;
			l ++;
		}
		// 链表 2 走到尾部，记录长度差值
		while (cur2.next != null) {
			l --;
			cur2 = cur2.next;
		}
		// 没有相遇说明没有交点
		if (cur1 != cur2) {
			return null;
		}
		cur1 = l > 0 ? head1 : head2;
		cur2 = cur1 == head1 ? head2 : head1;
		l = Math.abs(l);
		// 比较长的链表先走两个链表长度差值的距离
		while (l > 0) {
			l --;
			cur1 = cur1.next;
		}
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	/**
	 * 如果两个链表都有环，返回第一个相交节点，如果不相交，返回null
	 * @param head1 链表 1 的头节点
	 * @param loop1 链表 1 的入环节点
	 * @param head2 链表 2 的头节点
	 * @param loop2 链表 2 的入环节点
	 * @return 第一个相交节点或 null
	 */
	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node cur1, cur2;
		if (loop1 == loop2) {
			// 入环节点相同，相当于以入环节点为尾部的两链表都无环的情况
			cur1 = head1;
			cur2 = head2;
			int l = 0;
			while (cur1.next != loop1) {
				cur1 = cur1.next;
				l ++;
			}
			while (cur2.next != loop2) {
				l --;
				cur2 = cur2.next;
			}
			cur1 = l > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			l = Math.abs(l);
			// 比较长的链表先走两个链表长度差值的距离
			while (l > 0) {
				l --;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else {
			cur1 = loop1.next;
			// 如果链表 1 在环内转了一圈没遇到链表 2 的入环节点，则表明链表不相交，否则交点为 loop1 或 loop2
			while (cur1 != loop1) {
				if (cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}

}
