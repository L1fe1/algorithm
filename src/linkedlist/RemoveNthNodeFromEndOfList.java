package linkedlist;

/**
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">...</a>
 */
public class RemoveNthNodeFromEndOfList {
      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

      public ListNode removeNthFromEnd(ListNode head, int n) {
          ListNode cur = head;
          int len = 0;
          // 计算链表长度
          while (cur != null) {
              cur = cur.next;
              len ++;
          }
          cur = head;
          if (len - n == 0) {
              // 删除头节点
              if (len > 1) {
                  head = cur.next;
                  cur.next = null;
              } else {
                  // 链表只有一个节点
                  head = null;
              }
              cur = null;
          } else {
              // 移动到要删除的节点的前一个节点
              int step = len - n - 1;
              while (step > 0) {
                  cur = cur.next;
                  step--;
              }
              // 删除节点
              cur.next = cur.next.next;
          }
          return head;
      }
}
