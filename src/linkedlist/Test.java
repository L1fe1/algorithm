package linkedlist;

public class Test {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isPalindromeWithReverseList(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node pre = slow;
        Node cur = slow.next;
        slow.next = null;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        Node last = pre;
        cur = head;
        boolean ans = true;
        while (cur != null && pre != null) {
            if (cur.value != pre.value) {
                ans = false;
                break;
            }
            cur = cur.next;
            pre = pre.next;
        }

        pre = last;
        cur = last.next;
        last.next = null;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        System.out.println(isPalindromeWithReverseList(head));;
    }
}
