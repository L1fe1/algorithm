package linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int v) {
            val = v;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 按头节点 val 的大小构建小根堆
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if (list != null) {
                heap.add(list);
            }
        }
        // 记录最终合并链表的头节点
        ListNode head = heap.peek();
        while (!heap.isEmpty()) {
            // 取出堆顶节点，也就是当前 val 最小的节点
            ListNode cur = heap.poll();
            ListNode next = cur.next;
            // 如果当前节点 next 节点不为空，将它加到堆中
            if (next != null) {
                heap.add(next);
            }
            cur.next = heap.peek();
        }
        return head;
    }

    public static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);
        ListNode[] listNodes = {head1, head2, head3};
        printLinkedList(mergeKLists(listNodes));
    }
}
